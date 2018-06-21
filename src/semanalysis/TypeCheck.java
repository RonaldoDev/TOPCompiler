package semanalysis;

import java.util.Arrays;

import parser.Fun;
import symtable.*;
import syntaticTree.*;

public class TypeCheck extends VarCheck {
	// controla o nivel de aninhamento em comandos repetitivos
	int nesting;
	// conta número de variáveis locais num método
	protected int nLocals;
	// tipo de retorno de um método
	type returnType; 
	protected final EntrySimple STRING_TYPE;
	protected final EntrySimple INT_TYPE;
	protected final EntrySimple NULL_TYPE;

	protected final EntrySimple FLOAT_TYPE;
	protected final EntrySimple BOOLEAN_TYPE;
	protected final EntrySimple CHAR_TYPE;
	// método sendo analisado
	protected EntryMethod curMethod; 
	// indica se chamada super é permitida
	boolean cansuper; 

	/**
	 * Construtor da classe
	 * 
	 */
	public TypeCheck() {
		super();
		nesting = 0;
		nLocals = 0;
		STRING_TYPE = (EntrySimple) mainTable.classFindUp("string");
		INT_TYPE = (EntrySimple) mainTable.classFindUp("int");
		NULL_TYPE = new EntrySimple("$NULL$");

		CHAR_TYPE = (EntrySimple) mainTable.classFindUp("char");
		BOOLEAN_TYPE = (EntrySimple) mainTable.classFindUp("boolean");
		FLOAT_TYPE = (EntrySimple) mainTable.classFindUp("float");

		mainTable.add(NULL_TYPE);
	}

	public void TypeCheckRoot(ListNode x) throws SemanticException {
		// faz análise das variáveis e métodos
		varCheckRoot(x); 
		
		// faz análise do corpo dos métodos
		typeCheckClassDeclListNode(x); 

		// se houve erro, lança exceção
		if (foundSemanticError != 0) { 
			throw new SemanticException(foundSemanticError + " Semantic Errors found (phase 3)");
		}
	}

	/*
	 * Faz análise da lista de classes
	 * 
	 * @param ListNode
	 */
	public void typeCheckClassDeclListNode(ListNode x) {
		if (x == null) {
			return;
		}

		try {
			typeCheckClassDeclNode((ClassDeclNode) x.node);
		} catch (SemanticException e) { // se um erro ocorreu na classe, da a msg mas faz a análise p/ pr�xima
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		typeCheckClassDeclListNode(x.next);
	}

	/*
	 * Verifica se existe referência circular de superclasses
	 * 
	 * @param EntryClass
	 * @param EntryClass
	 */
	private boolean circularSuperclass(EntryClass orig, EntryClass e) {
		if (e == null) {
			return false;
		}

		if (orig == e) {
			return true;
		}

		return circularSuperclass(orig, e.parent);
	}

	/*
	 * Faz análise da declaração de classe
	 * 
	 * @param ClassDeclNode
	 * @throws SemanticException
	 */
	public void typeCheckClassDeclNode(ClassDeclNode x) throws SemanticException {
		// salva tabela corrente
		Symtable temphold = curTable; 
		EntryClass nc;

		if (x == null) {
			return;
		}

		nc = (EntryClass) curTable.classFindUp(x.name.image);
		
		// Gera erro se existe declaração circular
		if (circularSuperclass(nc, nc.parent)) {
			nc.parent = null;
			throw new SemanticException(x.position, "Circular inheritance");
		}
		
		// tabela corrente = tabela da classe
		curTable = nc.nested; 
		typeCheckClassBodyNode(x.body);
		// recupera tabela corrente
		curTable = temphold; 
	}

	/*
	 * Faz análise do corpo da classe
	 * 
	 * @param ClassBodyNode
	 */
	public void typeCheckClassBodyNode(ClassBodyNode x) {
		if (x == null) {
			return;
		}

		typeCheckClassDeclListNode(x.clist);
		typeCheckVarDeclListNode(x.vlist);
		typeCheckConstructDeclListNode(x.ctlist);
		typeCheckMethodDeclListNode(x.mlist);
	}

	/*
	 *  Faz análise da lista de declarações de variáveis
	 *  
	 *  @param ListNode
	 */
	public void typeCheckVarDeclListNode(ListNode x) {
		if (x == null) {
			return;
		}

		try {
			typeCheckVarDeclNode((VarDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		typeCheckVarDeclListNode(x.next);
	}

	/*
	 * Faz análise da declaração de variável
	 * 
	 * @param VarDeclNode
	 */
	public void typeCheckVarDeclNode(VarDeclNode x) throws SemanticException {
		ListNode p;
		EntryVar l;

		if (x == null) {
			return;
		}

		for (p = x.vars; p != null; p = p.next) {
			VarNode q = (VarNode) p.node;

			// tenta pegar 2a. ocorrência da variável na tabela
			l = curTable.varFind(q.position.image, 2);

			// se conseguiu a variável foi definida 2 vezes, ERRO
			if (l != null) {
				throw new SemanticException(q.position, "Variable " + q.position.image + " already declared");
			}
		}
	}

	/*
	 * Faz análise da  lista de construtores
	 * 
	 * @param ListNode
	 */
	public void typeCheckConstructDeclListNode(ListNode x) {
		if (x == null) {
			return;
		}

		try {
			typeCheckConstructDeclNode((ConstructDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		typeCheckConstructDeclListNode(x.next);
	}

	/*
	 * Faz análise da declaração de construtor
	 * 
	 * @param ConstructDeclNode
	 */
	public void typeCheckConstructDeclNode(ConstructDeclNode x) throws SemanticException {
		EntryMethod t;
		EntryRec r = null;
		EntryTable e;
		EntryClass thisclass;
		EntryVar thisvar;
		ListNode p;
		VarDeclNode q;
		VarNode u;
		int n;

		if (x == null) {
			return;
		}

		p = x.body.param;
		n = 0;

		// monta a lista com os tipos dos parâmetros
		while (p != null) {
			if (p.next != null) {
				if (p.next.node instanceof AssignConstNode) {
					p.next = p.next.next;
				}
			}

			// q = ná com a declaração do parâmetro
			q = (VarDeclNode) p.node; 
			// u = nó com o nome e dimensão
			u = (VarNode) q.vars.node;
			n++;

			// acha a entrada do tipo na tabela
			e = curTable.classFindUp(q.position.image);

			// constrói a lista com os tipos dos parâmetros
			r = new EntryRec(e, u.dim, n, r);
			p = p.next;
		}
		
		if (r != null) {
			// inverte a lista
			r = r.inverte(); 
		}

		// acha a entrada do construtor na tabela
		t = curTable.methodFind("constructor", r);
		
		// guarda método corrente
		curMethod = t; 

		// inicia um novo escopo na tabela corrente
		curTable.beginScope();

		// pega a entrada da classe corrente na tabela
		thisclass = (EntryClass) curTable.levelup;

		thisvar = new EntryVar("this", thisclass, 0, 0);
		// inclui variável local "this" com número 0
		curTable.add(thisvar);
		// tipo de retorno do método = nenhum
		returnType = null; 
		// nível de aninhamento de comandos for
		nesting = 0; 
		// inicializa numero de variáveis locais
		nLocals = 1; 
		typeCheckMethodBodyNode(x.body);
		// número de variáveis locais do método
		t.totallocals = nLocals; 
		// retira variáveis locais da tabela
		curTable.endScope(); 
	}

	/*
	 * Faz análise da lista de métodos 
	 * 
	 * @param ListNode
	 */
	public void typeCheckMethodDeclListNode(ListNode x) {
		if (x == null) {
			return;
		}

		try {
			typeCheckMethodDeclNode((MethodDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		typeCheckMethodDeclListNode(x.next);
	}

	/*
	 * Faz análise da declaração de método
	 * 
	 * @param MethodDeclNode
	 * @throws SemanticException
	 */
	public void typeCheckMethodDeclNode(MethodDeclNode x) throws SemanticException {
		EntryMethod t;
		EntryRec r = null;
		EntryTable e;
		EntryClass thisClass;
		EntryVar thisVar;
		ListNode p;
		VarDeclNode q;
		VarNode u;
		int n;

		if (x == null) {
			return;
		}

		p = x.body.param;
		n = 0;

		// monta a lista com os tipos dos parâmetros
		while (p != null) {
			if (p.next != null) {
				if (p.next.node instanceof AssignConstNode) {
					p.next = p.next.next;
				}
			}

			// q = nó com a declaração do parâmetro
			q = (VarDeclNode) p.node; 
			// u = nó com o nome e dimensão
			u = (VarNode) q.vars.node;
			n++;

			// acha a entrada do tipo na tabela
			e = curTable.classFindUp(q.position.image);

			// constrói a lista com os tipos dos parâmetros
			r = new EntryRec(e, u.dim, n, r);

			p = p.next;
		}

		if (r != null) {
			r = r.inverte();
		}

		// acha a entrada do método na tabela
		t = curTable.methodFind(x.name.image, r);
		curMethod = t; // guarda método corrente

		// Returntype = tipo de retorno do método
		returnType = new type(t.type, t.dim);

		// inicia um novo escopo na tabela corrente
		curTable.beginScope();

		// pega a entrada da classe corrente na tabela
		thisClass = (EntryClass) curTable.levelup;

		thisVar = new EntryVar("this", thisClass, 0, 0);
		// inclui variável local "this" na tabela
		curTable.add(thisVar); 

		 // nível de aninhamento de comandos for
		nesting = 0;
		// inicializa número de variáveis locais
		nLocals = 1; 
		typeCheckMethodBodyNode(x.body);
		// número de variáveis locais declaradas
		t.totallocals = nLocals;
		// retira variáveis locais da tabela corrente
		curTable.endScope(); 
	}

	/*
	 * Faz análise do corpo de método
	 * 
	 * @param MethodBodyNode
	 */
	public void typeCheckMethodBodyNode(MethodBodyNode x) {
		if (x == null) {
			return;
		}
		// trata parâmetro como var. local
		typeCheckLocalVarDeclListNode(x.param); 

		cansuper = false;
		// existe uma superclasse para a classe corrente ?
		if (curTable.levelup.parent != null) { 
			// acha primeiro comando do método
			StatementNode p = x.stat;

			while (p instanceof BlockNode)
				p = (StatementNode) ((BlockNode) p).stats.node;
			// verifica se é chamada super
			cansuper = p instanceof SuperNode; 
		}

		try {
			typeCheckStatementNode(x.stat);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
	}

	/*
	 * Faz análise da lista de variáveis locais
	 * 
	 * @param ListNode
	 */
	public void typeCheckLocalVarDeclListNode(ListNode x) {
		if (x == null) {
			return;
		}

		try {
			typeCheckLocalVarDeclNode((VarDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		typeCheckLocalVarDeclListNode(x.next);
	}

	/*
	 * Faz análise da declaração de variáveis locais 
	 * 
	 * @param VarDeclNode
	 * @throws SemanticException
	 */
	public void typeCheckLocalVarDeclNode(VarDeclNode x) throws SemanticException {
		ListNode p;
		VarNode q;
		EntryVar l;
		EntryVar u;
		EntryTable c;

		if (x == null) {
			return;
		}

		// procura tipo da declaração na tabela de símbolos
		c = curTable.classFindUp(x.position.image);

		// se não achou lança exceção
		if (c == null) {
			throw new SemanticException(x.position, "Class " + x.position.image + " not found.");
		}

		for (p = x.vars; p != null; p = p.next) {
			q = (VarNode) p.node;
			l = curTable.varFind(q.position.image);

			// se variável já existe é preciso saber que tipo de variável é
			if (l != null) {
				// primeiro verifica se é local, definida no escopo corrente
				if (l.scope == curTable.scptr) { 
					// se for lança exceção
					throw new SemanticException(q.position, "Variable " + p.position.image + " already declared");
				}

				// verifica se á uma variável de classe
				if (l.localcount < 0) { 
					// se for dá uma advertência
					System.out.println("Line " + q.position.beginLine + " Column " + q.position.beginColumn
							+ " Warning: Variable " + q.position.image + " hides a class variable");
				// senão, é uma variável local em outro escopo
				} else { 
					System.out.println("Line " + q.position.beginLine + " Column " + q.position.beginColumn
							+ " Warning: Variable " + q.position.image + " hides a parameter or a local variable");
				}
			}

			// insere a variável local na tabela corrente
			curTable.add(new EntryVar(q.position.image, c, q.dim, nLocals++));
		}
	}

	/*
	 * Faz análise de comando composto
	 * 
	 * @param BlockNode
	 */
	public void typeCheckBlockNode(BlockNode x) {
		// início de um escopo
		curTable.beginScope(); 
		typeCheckStatementListNode(x.stats);
		// final do escopo, libera vars locais
		curTable.endScope();
	}

	/*
	 * Faz análise da lista de comandos
	 * 
	 * @param ListNode
	 */
	public void typeCheckStatementListNode(ListNode x) {
		if (x == null) {
			return;
		}

		try {
			typeCheckStatementNode((StatementNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		typeCheckStatementListNode(x.next);
	}

	/*
	 * Faz análise do comando print
	 * 
	 * @param PrintNode
	 * @throws SemanticException
	 */
	public void typeCheckPrintNode(PrintNode x) throws SemanticException {
		type t;

		if (x == null) {
			return;
		}

		// t = tipo e dimensão do resultado da expressão
		t = typeCheckExpreNode(x.expr);

		// tipo tem que ser string e dimensão tem que ser 0
		if ((t.ty != STRING_TYPE) || (t.dim != 0)) {
			throw new SemanticException(x.position, "string expression required");
		}
	}

	/*
	 * Faz análise do comando read
	 * 
	 * @param ReadNode
	 * @throws SemanticException
	 */
	public void typeCheckReadNode(ReadNode x) throws SemanticException {
		type t;

		if (x == null) {
			return;
		}

		// verifica se o nó filho tem um tipo válido
		if (!(x.expr instanceof DotNode || x.expr instanceof IndexNode || x.expr instanceof VarNode)) {
			throw new SemanticException(x.position, "Invalid expression in read statement");
		}

		// verifica se e uma atribuição para "this"
		if (x.expr instanceof VarNode) {
			EntryVar v = curTable.varFind(x.expr.position.image);

			// é a variável local e tem valor 0?
			if ((v != null) && (v.localcount == 0)) {
				throw new SemanticException(x.position, "Reading into variable " + " \"this\" is not legal");
			}
		}

		// verifica se o tipo é string ou int
		t = typeCheckExpreNode(x.expr);

		if ((t.ty != STRING_TYPE) && (t.ty != INT_TYPE)) {
			throw new SemanticException(x.position, "Invalid type. Must be int or string");
		}

		// verifica se não é array
		if (t.dim != 0) {
			throw new SemanticException(x.position, "Cannot read array");
		}
	}

	/*
	 * Faz análise do comando return
	 * 
	 * @param ReturnNode
	 * @throws SemanticException
	 */
	public void typeCheckReturnNode(ReturnNode x) throws SemanticException {
		type t;

		if (x == null) {
			return;
		}

		// t = tipo e dimensão do resultado da expressão
		t = typeCheckExpreNode(x.expr);

		// verifica se é igual ao tipo do método corrente
		if (t == null) { // t == null não tem expressão no return

			if (returnType == null) {
				return;
			// se Returntype for diferente de null e é um método lança exceção
			} else { 
				throw new SemanticException(x.position, "Return expression required");
			}
		} else {
			// retorno num construtor lanç exceção
			if (returnType == null) {
				throw new SemanticException(x.position, "Constructor cannot return a value");
			}
		}

		// compara tipo e dimensão
		if ((t.ty != returnType.ty) || (t.dim != returnType.dim)) {
			throw new SemanticException(x.position, "Invalid return type");
		}
	}

	/*
	 * Faz análise do comando super
	 * 
	 * @param SuperNode
	 * @throws SemanticException
	 */
	public void typeCheckSuperNode(SuperNode x) throws SemanticException {
		type t;

		if (x == null) {
			return;
		}

		if (returnType != null) {
			throw new SemanticException(x.position, "super is only allowed in constructors");
		}

		if (!cansuper) {
			throw new SemanticException(x.position, "super must be first statement in the constructor");
		}

		// não permite mais de um super
		cansuper = false;

		// p aponta para a entrada da superclasse da classe corrente
		EntryClass p = curTable.levelup.parent;

		if (p == null) {
			throw new SemanticException(x.position, "No superclass for this class");
		}

		// t.ty possui um EntryRec com os tipos dos parâmetros
		t = typeCheckExpreListNode(x.args);

		// procura o construtor na tabela da superclasse
		EntryMethod m = p.nested.methodFindInclass("constructor", (EntryRec) t.ty);

		// se não achou, ERRO
		if (m == null) {
			throw new SemanticException(x.position,
					"Constructor " + p.name + "(" + ((t.ty == null) ? "" : ((EntryRec) t.ty).toStr()) + ") not found");
		}
		// indica que existe chamada a super no método
		curMethod.hassuper = true; 
	}

	/*
	 * Faz análise do comando de atribuição
	 * 
	 * @param AtribNode
	 * @throws SemanticException
	 */
	public void typeCheckAtribNode(AtribNode x) throws SemanticException {
		type t1;
		type t2;
		EntryVar v;

		if (x == null) {
			return;
		}

		// verifica se o nó filho tem um tipo válido
		if (!(x.expr1 instanceof DotNode || x.expr1 instanceof IndexNode || x.expr1 instanceof VarNode)) {
			throw new SemanticException(x.position, "Invalid left side of assignment");
		}

		// verifica se é uma atribuição para "this"
		if (x.expr1 instanceof VarNode) {
			v = curTable.varFind(x.expr1.position.image);
			// é a variável local 0?
			if ((v != null) && (v.localcount == 0)) {
				throw new SemanticException(x.position, "Assigning to variable " + " \"this\" is not legal");
			}
		}

		t1 = typeCheckExpreNode(x.expr1);
		t2 = typeCheckExpreNode(x.expr2);

		// verifica tipos das expressões
		// verifica dimensões

		if (t1.dim != t2.dim) {
			throw new SemanticException(x.position, "Invalid dimensions in assignment");
		}

		// verifica se lado esquerdo é uma classe e direito é null, OK
		if (t1.ty instanceof EntryClass && (t2.ty == NULL_TYPE)) {
			return;
		}

		// verifica se t2 e subclasse de t1

		if (!(isSubClass(t2.ty, t1.ty) || isSubClass(t1.ty, t2.ty))) {
			throw new SemanticException(x.position, "Incompatible types for assignment ");
		}

	}

	/**
	 * Verifica se é subclasse
	 * 
	 * @param EntryTable t1
	 * @param EntryTable t2
	 * @return
	 */
	protected boolean isSubClass(EntryTable t1, EntryTable t2) {
		// verifica se são o mesmo tipo (vale para tipos simples)
		if (t1 == t2) {
			return true;
		}

		// verifica se são classes
		if (!(t1 instanceof EntryClass && t2 instanceof EntryClass)) {
			return false;
		}

		// procura t2 nas superclasses de t1
		for (EntryClass p = ((EntryClass) t1).parent; p != null; p = p.parent)
			if (p == t2) {
				return true;
			}

		return false;
	}

	/*
	 * Faz análise do comando if
	 * 
	 * @param IfNode
	 */
	public void typeCheckIfNode(IfNode x) {
		type t;

		if (x == null) {
			return;
		}

		try {
			t = typeCheckExpreNode(x.expr);

			if ((t.ty == INT_TYPE) || (t.dim == 0) || (t.ty == BOOLEAN_TYPE) || (t.ty == FLOAT_TYPE)
					|| (t.ty == CHAR_TYPE)) {
			} else {
				throw new SemanticException(x.expr.position, "Integer, boolean, double or char expression expected");
			}
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		try {
			typeCheckStatementNode(x.stat1);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		try {
			typeCheckStatementNode(x.stat2);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
	}

	/*
	 * Faz análise do comando for
	 * 
	 * @param ForNode
	 */
	public void typeCheckForNode(ForNode x) {
		type t;

		if (x == null) {
			return;
		}

		// analisa inicialização
		try {
			typeCheckStatementNode(x.init);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		// analisa expressão de controle
		try {
			t = typeCheckExpreNode(x.expr);

			if ((t.ty != BOOLEAN_TYPE) || (t.dim != 0)) {
				throw new SemanticException(x.expr.position, "Boolean expression expected");
			}
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		// analisa expressão de incremento
		try {
			typeCheckStatementNode(x.incr);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		// analisa comando a ser repetido
		try {
			// incrementa o aninhamento
			nesting++; 
			typeCheckStatementNode(x.stat);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
		// decrementa o aninhamento
		nesting--; 
	}

	/*
	 * Faz análise do comando break
	 * 
	 * @param BreakNode
	 * @throws SemanticException
	 */
	public void typeCheckBreakNode(BreakNode x) throws SemanticException {
		if (x == null) {
			return;
		}

		// verifica se está dentro de um for. Se não, ERRO
		if (nesting <= 0) {
			throw new SemanticException(x.position, "break not in a for statement");
		}
	}

	/*
	 * Faz análise do comando vazio
	 * 
	 * @param NopNode
	 */
	public void typeCheckNopNode(NopNode x) {
		// nothing to be done!
	}

	// ########################################################################################################
	// ############################################## EXPRESSÕES ##############################################
	// ########################################################################################################
	
	/*
	 * Faz análise da alocação de objeto
	 * 
	 * @param NewObjectNode
	 * @throws SemanticException
	 */
	public type typeCheckNewObjectNode(NewObjectNode x) throws SemanticException {
		type t;
		EntryMethod p;
		EntryTable c;

		if (x == null) {
			return null;
		}

		// procura a classe da qual se deseja criar um objeto
		c = curTable.classFindUp(x.name.image);

		// se não achou, ERRO
		if (c == null) {
			throw new SemanticException(x.position, "Class " + x.name.image + " not found");
		}

		// t.ty recebe a lista de tipos dos argumentos
		t = typeCheckExpreListNode(x.args);

		// procura um construtor com essa assinatura
		Symtable s = ((EntryClass) c).nested;
		p = s.methodFindInclass("constructor", (EntryRec) t.ty);

		// se não achou, ERRO
		if (p == null) {
			throw new SemanticException(x.position, "Constructor " + x.name.image + "("
					+ ((t.ty == null) ? "" : ((EntryRec) t.ty).toStr()) + ") not found");
		}

		// retorna c como tipo, dimensão = 0, local = -1 (não local)
		t = new type(c, 0);

		return t;
	}

	/*
	 * Faz a análise da alocação de array
	 * 
	 * @param NewArrayNode
	 * @throws SemanticException
	 */
	public type typeCheckNewArrayNode(NewArrayNode x) throws SemanticException {
		type t;
		EntryTable c;
		ListNode p;
		ExpreNode q;
		int k;

		if (x == null) {
			return null;
		}

		// procura o tipo da qual se deseja criar um array
		c = curTable.classFindUp(x.name.image);

		// se não achou, ERRO
		if (c == null) {
			throw new SemanticException(x.position, "Type " + x.name.image + " not found");
		}

		// para cada expressão das dimensões, verifica se tipo e int
		for (k = 0, p = x.dims; p != null; p = p.next) {
			t = typeCheckExpreNode((ExpreNode) p.node);

			if ((t.ty != INT_TYPE) || (t.dim != 0)) {
				throw new SemanticException(p.position, "Invalid expression for an array dimension");
			}

			k++;
		}

		return new type(c, k);
	}

	/*
	 * Faz análise lista de expressões
	 * 
	 * @param ListNode
	 */
	public type typeCheckExpreListNode(ListNode x) {
		type t;
		type t1;
		EntryRec r;
		int n;

		if (x == null) {
			return new type(null, 0);
		}

		try {
			// pega tipo do primeiro nó da lista
			t = typeCheckExpreNode((ExpreNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
			t = new type(NULL_TYPE, 0);
		}

		// pega tipo do restante da lista. t1.ty contém um EntryRec
		t1 = typeCheckExpreListNode(x.next);

		// n = tamanho da lista em t1
		n = (t1.ty == null) ? 0 : ((EntryRec) t1.ty).count;

		// cria novo EntryRec com t.ty como 1.o elemento
		r = new EntryRec(t.ty, t.dim, n + 1, (EntryRec) t1.ty);

		// cria type com r como variável ty
		t = new type(r, 0);

		return t;
	}

	/*
	 * Faz análise da expressão relacional
	 * 
	 * @param RelationalNode
	 * @throws SemanticException
	 */
	public type typeCheckRelationalNode(RelationalNode x) throws SemanticException {
		type t1;
		type t2;
		// operação
		int op; 

		if (x == null) {
			return null;
		}

		op = x.position.kind;
		t1 = typeCheckExpreNode(x.expr1);
		t2 = typeCheckExpreNode(x.expr2);

		if (op == Fun.NOT) {
			if (t2 != null) {
				throw new SemanticException(x.position, "Invalid types for " + x.position.image);
			}

			return new type(BOOLEAN_TYPE, 0);
		}

		java.util.List<Integer> logicalOperators = Arrays.asList(Fun.AND, Fun.OR, Fun.XOR);
		if (logicalOperators.contains(op)) {
			if (t1 != null && t2 != null) {
				return new type(BOOLEAN_TYPE, 0);
			}
		}

		java.util.List<Integer> relationalOperators = Arrays.asList(Fun.GE, Fun.LE, Fun.GT, Fun.LT);
		java.util.List<Integer> equalityOperators = Arrays.asList(Fun.EQ, Fun.NEQ);

		if (relationalOperators.contains(op)) {
			java.util.List<EntrySimple> numericalTypes = Arrays.asList(INT_TYPE, FLOAT_TYPE);
			boolean validRelation = numericalTypes.contains(t1.ty) && numericalTypes.contains(t2.ty);

			if (validRelation) {
				return new type(BOOLEAN_TYPE, 0);
			} else {
				throw new SemanticException(x.position, "Invalid types for a relational operator");
			}
		}

		if (equalityOperators.contains(op)) {
			boolean validEquality = t1.ty.equals(t2.ty);

			if (validEquality) {
				return new type(BOOLEAN_TYPE, 0);
			} else {
				throw new SemanticException(x.position, "Invalid types for a equality operator");
			}
		}

		// se a dimensão é diferente, ERRO
		if (t1.dim != t2.dim) {
			throw new SemanticException(x.position, "Can not compare objects with different dimensions");
		}

		// se dimensão > 0 só pode comparar igualdade
		if ((op != Fun.EQ) && (op != Fun.NEQ) && (t1.dim > 0)) {
			throw new SemanticException(x.position, "Can not use " + x.position.image + " for arrays");
		}

		// se um é objeto e outro null, pode comparar igualdade
		if (((t1.ty instanceof EntryClass && (t2.ty == NULL_TYPE))
				|| (t2.ty instanceof EntryClass && (t1.ty == NULL_TYPE))) && ((op == Fun.NEQ) || (op == Fun.EQ))) {
			return new type(INT_TYPE, 0);
		}

		throw new SemanticException(x.position, "Invalid types for " + x.position.image);
	}

	/*
	 * Faz a análise da soma ou subtração
	 * 
	 * @param AddNode
	 * @throws SemanticException
	 */
	public type typeCheckAddNode(AddNode x) throws SemanticException {
		type t1;
		type t2;
		// operação
		int op; 
		int i;
		int j;

		if (x == null) {
			return null;
		}

		op = x.position.kind;
		t1 = typeCheckExpreNode(x.expr1);
		t2 = typeCheckExpreNode(x.expr2);

		// se dimensão > 0, ERRO
		if ((t1.dim > 0) || (t2.dim > 0)) {
			throw new SemanticException(x.position, "Can not use " + x.position.image + " for arrays");
		}

		if ((t1.ty == t2.ty) && t1.ty == INT_TYPE) {
			return new type(INT_TYPE, 0);
		}

		if ((t1.ty == t2.ty) && t1.ty == FLOAT_TYPE) {
			return new type(FLOAT_TYPE, 0);
		}

		if ((t1.ty == INT_TYPE && t2.ty == FLOAT_TYPE) || (t1.ty == FLOAT_TYPE && t2.ty == INT_TYPE)) {
			return new type(FLOAT_TYPE, 0);
		}

		java.util.List<EntrySimple> validTypes = Arrays.asList(STRING_TYPE, INT_TYPE, FLOAT_TYPE, BOOLEAN_TYPE,
				CHAR_TYPE);

		if (t1.ty == STRING_TYPE && validTypes.contains(t2.ty) || t2.ty == STRING_TYPE && validTypes.contains(t1.ty)) {
			return new type(STRING_TYPE, 0);
		}

		throw new SemanticException(x.position, "Invalid types for " + x.position.image);
	}

	/*
	 * Faz análise da operação de multiplicação ou divisão
	 * 
	 * @param MultNode
	 * @throws SemanticException
	 */
	public type typeCheckMultNode(MultNode x) throws SemanticException {
		type t1;
		type t2;
		// operação
		int op; 
		int i;
		int j;

		if (x == null) {
			return null;
		}

		op = x.position.kind;
		t1 = typeCheckExpreNode(x.expr1);
		t2 = typeCheckExpreNode(x.expr2);

		// se dimensão > 0, ERRO
		if ((t1.dim > 0) || (t2.dim > 0)) {
			throw new SemanticException(x.position, "Can not use " + x.position.image + " for arrays");
		}

		// aceitar numericos
		java.util.List<EntrySimple> validTypes = Arrays.asList(INT_TYPE, FLOAT_TYPE);
		if (!validTypes.contains(t1.ty) || !validTypes.contains(t2.ty)) {
			throw new SemanticException(x.position, "Invalid types for " + x.position.image);
		}

		return new type(INT_TYPE, 0);
	}

	/*
	 * Faz análise da expressão 
	 * 
	 * @param UnaryNode
	 * @throws SemanticException
	 */
	public type typeCheckUnaryNode(UnaryNode x) throws SemanticException {
		type t;

		if (x == null) {
			return null;
		}

		t = typeCheckExpreNode(x.expr);

		// se dimensão > 0, ERRO
		if (t.dim > 0) {
			throw new SemanticException(x.position, "Can not use unary " + x.position.image + " for arrays");
		}

		// só int é aceito
		if (t.ty != INT_TYPE) {
			throw new SemanticException(x.position, "Incompatible type for unary " + x.position.image);
		}

		return new type(INT_TYPE, 0);
	}

	/*
	 * Faz análise da constante inteira
	 * 
	 * @param IntConstNode
	 * @throws SemanticException
	 */
	public type typeCheckIntConstNode(IntConstNode x) throws SemanticException {
		int k;

		if (x == null) {
			return null;
		}

		// tenta transformar imagem em número inteiro
		try {
			k = Integer.parseInt(x.position.image);
		// se deu erro, formato e inválido (possivelmente fora dos limites)
		} catch (NumberFormatException e) { 
			throw new SemanticException(x.position, "Invalid int constant");
		}

		return new type(INT_TYPE, 0);
	}

	/*
	 * Faz análise da constante string
	 * 
	 * @param StringConstNode
	 */
	public type typeCheckStringConstNode(StringConstNode x) {
		if (x == null) {
			return null;
		}

		return new type(STRING_TYPE, 0);
	}

	/*
	 * Faz a análise da constante null
	 * 
	 * @param NullConstNode
	 */
	public type typeCheckNullConstNode(NullConstNode x) {
		if (x == null) {
			return null;
		}

		return new type(NULL_TYPE, 0);
	}

	/*
	 * Faz a análise da constante boolean
	 * 
	 * @param BoolConstNode
	 */
	public type typeCheckBoolConstNode(BoolConstNode x) {
		if (x == null) {
			return null;
		}

		return new type(BOOLEAN_TYPE, 0);
	}

	/*
	 * Faz a análise da constante float
	 * 
	 * @param FloatConstNode
	 */
	public type typeCheckFloatConstNode(FloatConstNode x) {
		if (x == null) {
			return null;
		}

		return new type(FLOAT_TYPE, 0);
	}

	/*
	 * Faz a análise da constante char
	 * 
	 * @param CharConstNode
	 */
	public type typeCheckCharConstNode(CharConstNode x) {
		if (x == null) {
			return null;
		}

		return new type(CHAR_TYPE, 0);
	}

	/*
	 * Faz análise do nome de variável
	 * 
	 * @param VarNode
	 * @throws SemanticException
	 */
	public type typeCheckVarNode(VarNode x) throws SemanticException {
		EntryVar p;

		if (x == null) {
			return null;
		}

		// procura variável na tabela
		p = curTable.varFind(x.position.image);

		// se não achou, ERRO
		if (p == null) {
			throw new SemanticException(x.position, "Variable " + x.position.image + " not found");
		}

		return new type(p.type, p.dim);
	}

	/*
	 * Faz análise da chamada de método
	 * 
	 * @param CallNode
	 * @throws SemanticException
	 */
	public type typeCheckCallNode(CallNode x) throws SemanticException {
		EntryClass c;
		EntryMethod m;
		type t1;
		type t2;

		if (x == null) {
			return null;
		}

		// calcula tipo do primeiro filho
		t1 = typeCheckExpreNode(x.expr);

		// se for array, ERRO
		if (t1.dim > 0) {
			throw new SemanticException(x.position, "Arrays do not have methods");
		}

		// se n�o for uma classe, ERRO
		if (!(t1.ty instanceof EntryClass)) {
			throw new SemanticException(x.position, "Type " + t1.ty.name + " does not have methods");
		}

		// pega tipos dos argumentos
		t2 = typeCheckExpreListNode(x.args);

		// procura o método desejado na classe t1.ty
		c = (EntryClass) t1.ty;
		m = c.nested.methodFind(x.meth.image, (EntryRec) t2.ty);

		// se não achou, ERRO
		if (m == null) {
			throw new SemanticException(x.position, "Method " + x.meth.image + "("
					+ ((t2.ty == null) ? "" : ((EntryRec) t2.ty).toStr()) + ") not found in class " + c.name);
		}

		return new type(m.type, m.dim);
	}

	/*
	 * Faz análise da indexação de variável
	 * 
	 * @param IndexNode
	 * @throws SemanticException
	 */
	public type typeCheckIndexNode(IndexNode x) throws SemanticException {
		EntryClass c;
		type t1;
		type t2;

		if (x == null) {
			return null;
		}

		// calcula tipo do primeiro filho
		t1 = typeCheckExpreNode(x.expre1);

		// se não for array, ERRO
		if (t1.dim <= 0) {
			throw new SemanticException(x.position, "Can not index non array variables");
		}

		// pega tipo do índice
		t2 = typeCheckExpreNode(x.expre2);

		// se não for int, ERRO
		if ((t2.ty != INT_TYPE) || (t2.dim > 0)) {
			throw new SemanticException(x.position, "Invalid type. Index must be int");
		}

		return new type(t1.ty, t1.dim - 1);
	}

	/*
	 * Faz análise do acesso a campo de variável
	 * 
	 * @param DotNode
	 * @throws SemanticException
	 */
	public type typeCheckDotNode(DotNode x) throws SemanticException {
		EntryClass c;
		EntryVar v;
		type t;

		if (x == null) {
			return null;
		}

		// calcula tipo do primeiro filho
		t = typeCheckExpreNode(x.expr);

		// se for array, ERRO
		if (t.dim > 0) {
			throw new SemanticException(x.position, "Arrays do not have fields");
		}

		// se não for uma classe, ERRO
		if (!(t.ty instanceof EntryClass)) {
			throw new SemanticException(x.position, "Type " + t.ty.name + " does not have fields");
		}

		// procura a variável desejada na classe t.ty
		c = (EntryClass) t.ty;
		v = c.nested.varFind(x.field.image);

		// se não achou, ERRO
		if (v == null) {
			throw new SemanticException(x.position, "Variable " + x.field.image + " not found in class " + c.name);
		}

		return new type(v.type, v.dim);
	}

	/*
	 * Faz análise de expressão em geral
	 * 
	 * @param ExpreNode
	 * @throws SemanticException
	 */
	public type typeCheckExpreNode(ExpreNode x) throws SemanticException {
		if (x instanceof NewObjectNode) {
			return typeCheckNewObjectNode((NewObjectNode) x);
		} else if (x instanceof NewArrayNode) {
			return typeCheckNewArrayNode((NewArrayNode) x);
		} else if (x instanceof RelationalNode) {
			return typeCheckRelationalNode((RelationalNode) x);
		} else if (x instanceof AddNode) {
			return typeCheckAddNode((AddNode) x);
		} else if (x instanceof MultNode) {
			return typeCheckMultNode((MultNode) x);
		} else if (x instanceof UnaryNode) {
			return typeCheckUnaryNode((UnaryNode) x);
		} else if (x instanceof CallNode) {
			return typeCheckCallNode((CallNode) x);
		} else if (x instanceof IntConstNode) {
			return typeCheckIntConstNode((IntConstNode) x);
		} else if (x instanceof StringConstNode) {
			return typeCheckStringConstNode((StringConstNode) x);
		} else if (x instanceof NullConstNode) {
			return typeCheckNullConstNode((NullConstNode) x);
		} else if (x instanceof IndexNode) {
			return typeCheckIndexNode((IndexNode) x);
		} else if (x instanceof DotNode) {
			return typeCheckDotNode((DotNode) x);
		} else if (x instanceof VarNode) {
			return typeCheckVarNode((VarNode) x);
		} else if (x instanceof BoolConstNode) {
			return typeCheckBoolConstNode((BoolConstNode) x);
		} else if (x instanceof CharConstNode) {
			return typeCheckCharConstNode((CharConstNode) x);
		} else if (x instanceof FloatConstNode) {
			return typeCheckFloatConstNode((FloatConstNode) x);
		} else {
			return null;
		}
	}

	/*
	 * Faz análise de comando em geral
	 * 
	 * @param ExpreNode
	 * @throws SemanticException
	 */
	public void typeCheckStatementNode(StatementNode x) throws SemanticException {
		if (x instanceof BlockNode) {
			typeCheckBlockNode((BlockNode) x);
		} else if (x instanceof VarDeclNode) {
			typeCheckLocalVarDeclNode((VarDeclNode) x);
		} else if (x instanceof AtribNode) {
			typeCheckAtribNode((AtribNode) x);
		} else if (x instanceof IfNode) {
			typeCheckIfNode((IfNode) x);
		} else if (x instanceof ForNode) {
			typeCheckForNode((ForNode) x);
		} else if (x instanceof PrintNode) {
			typeCheckPrintNode((PrintNode) x);
		} else if (x instanceof NopNode) {
			typeCheckNopNode((NopNode) x);
		} else if (x instanceof ReadNode) {
			typeCheckReadNode((ReadNode) x);
		} else if (x instanceof ReturnNode) {
			typeCheckReturnNode((ReturnNode) x);
		} else if (x instanceof SuperNode) {
			typeCheckSuperNode((SuperNode) x);
		} else if (x instanceof BreakNode) {
			typeCheckBreakNode((BreakNode) x);
		}
	}
}
