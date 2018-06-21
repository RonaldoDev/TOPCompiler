package semanalysis;

import symtable.EntryClass;
import symtable.EntryMethod;
import symtable.EntryRec;
import symtable.EntryTable;
import symtable.EntryVar;
import symtable.Symtable;
import syntaticTree.*;

public class VarCheck extends ClassCheck {

	public VarCheck() {
		super();
	}

	public void varCheckRoot(ListNode x) throws SemanticException {
		classCheckRoot(x);
		varCheckClassDeclListNode(x);
		if (foundSemanticError != 0) {
			throw new SemanticException(foundSemanticError + " Semantic Erros found (phase 2)");
		}
	}

	public void varCheckClassDeclListNode(ListNode x) {
		if (x == null)
			return;

		try {
			varCheckClassDeclNode((ClassDeclNode) x.node);
		} catch (SemanticException e) {
			// se um erro ocorreu na analise da classe,
			// lanca erro, mas faz a analise para a proxima classe
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
		varCheckClassDeclListNode(x.next);
	}

	public void varCheckClassDeclNode(ClassDeclNode x) throws SemanticException {
		Symtable temphold = curTable; // salva tabela corrente
		EntryClass c = null;
		EntryClass nc = null;

		if (x == null)
			return;

		if (x.supername != null) {
			c = (EntryClass) curTable.classFindUp(x.supername.image);
			if (c == null) { // se nao achou superclasse, ERRO
				throw new SemanticException(x.position, "Superclass " + x.supername.image + " not found");
			}
		}

		nc = (EntryClass) curTable.classFindUp(x.name.image);
		nc.parent = c; // coloca na tabela o apontador p/ super classe
		curTable = nc.nested; // tabela corrente = tabela da classe
		varCheckClassBodyNode(x.body);
		curTable = temphold; // recupera tabela corrente
	}

	public void varCheckClassBodyNode(ClassBodyNode x) {
		if (x == null)
			return;

		varCheckClassDeclListNode(x.clist);
		varCheckVarDeclListNode(x.vlist);
		varCheckConstructDeclListNode(x.ctlist);

		// se nao existe construtor, insere um falso
		if (curTable.methodFindInclass("constructor", null) == null) {
			curTable.add(new EntryMethod("constructor", curTable.levelup, true));
		}
		varCheckMethodDeclListNode(x.mlist);
	}

	public void varCheckVarDeclListNode(ListNode x) {
		if (x == null)
			return;

		try {
			varCheckVarDeclNode((VarDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
		varCheckVarDeclListNode(x.next);
	}

	public void varCheckVarDeclNode(VarDeclNode x) throws SemanticException {
		EntryTable c;
		ListNode p;

		if (x == null)
			return;

		// acha entrada do tipo da variavel
		c = curTable.classFindUp(x.position.image);
		// se nao achou, ERRO
		if (c == null) {
			throw new SemanticException(x.position, "Class " + x.position.image + " not found");
		}

		// para cada variavel da declara��o, cria uma entrada na tabela
		for (p = x.vars; p != null; p = p.next) {
			VarNode q = (VarNode) p.node;
			curTable.add(new EntryVar(q.position.image, c, q.dim));
		}
	}

	public void varCheckConstructDeclListNode(ListNode x) {
		if (x == null)
			return;

		try {
			varCheckConstructDeclNode((ConstructDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
		varCheckConstructDeclListNode(x.next);
	}

	public void varCheckConstructDeclNode(ConstructDeclNode x) throws SemanticException {
		EntryMethod c;
		EntryRec r = null;
		EntryTable e;
		ListNode p;
		VarDeclNode q;
		VarNode u;
		int n;

		if (x == null) {
			return;
		}

		p = x.body.param;
		n = 0;

		while (p != null) { // para cada par�metro do construtor
			if (p.node instanceof AssignConstNode) {
				p = p.next;
				continue;
			}

			q = (VarDeclNode) p.node; // q = n� com a declarac�o do par�metro
			u = (VarNode) q.vars.node; // u = n� com o nome e dimens�o
			n++;

			// acha a entrada do tipo na tabela
			e = curTable.classFindUp(q.position.image);

			// se n�o achou: ERRO
			if (e == null) {
				throw new SemanticException(q.position, "Class " + q.position.image + " not found");
			}

			// constr�i a lista com os par�metros
			r = new EntryRec(e, u.dim, n, r, true);
			p = p.next;
		}

		if (r != null) {
			r = r.inverte(); // inverte a lista
		}

		// procura construtor com essa assinatura dentro da mesma classe
		c = curTable.methodFindInclass("constructor", r);

		if (c == null) { // se n�o achou, insere
			c = new EntryMethod("constructor", curTable.levelup, 0, r);
			curTable.add(c);
		} else { // construtor j� definido na mesma classe: ERRO
			throw new SemanticException(x.position, "Constructor " + curTable.levelup.name + "("
					+ ((r == null) ? "" : r.toStr()) + ")" + " already declared");
		}
	}

	public void varCheckMethodDeclListNode(ListNode x) {
		if (x == null)
			return;

		try {
			varCheckMethodDeclNode((MethodDeclNode) x.node);
		} catch (SemanticException e) {
			System.out.println(e.getMessage());
			foundSemanticError++;
		}

		varCheckMethodDeclListNode(x.next);
	}

	public void varCheckMethodDeclNode(MethodDeclNode x) throws SemanticException {
		EntryMethod c = null;
		EntryRec r = null;
		EntryTable e = null;
		ListNode p = null;
		VarDeclNode q = null;
		VarNode u = null;
		int n;

		if (x == null)
			return;

		p = x.body.param;
		n = 0;

		while (p != null) { // para cada parametro do construtor
			if (p.node instanceof AssignConstNode) {
				p = p.next;
				continue;
			}

			q = (VarDeclNode) p.node; // q = no com a declaracao do parametro
			u = (VarNode) q.vars.node; // u = no com o nome e dimensao
			n++;
			// acha a entrada do tipo na tabela
			e = curTable.classFindUp(q.position.image);

			// se nao achou, ERRO
			if (e == null) {
				throw new SemanticException(q.position, "Class " + q.position.image + " not found");
			}

			// constroi a lista de nos com os parametros
			r = new EntryRec(e, u.dim, n, r, true);
			p = p.next;
		}

		if (r != null) {
			r = r.inverte(); // inverte a lista
		}

		e = curTable.classFindUp(x.position.image);

		if (e == null) {
			throw new SemanticException(x.position, "Class " + x.position.image + " not found");
		}

		// procura metodo na tabela, dentro da mesma classe
		c = curTable.methodFindInclass(x.name.image, r);

		if (c == null) {
			c = new EntryMethod(x.name.image, e, x.dim, r);
			curTable.add(c);
		} else { // metodo ja definido na mesma classe, ERRO
			String msg = "Method " + x.name.image + "(" + ((r == null) ? "" : r.toStr()) + ")" + " already declared";
			throw new SemanticException(x.position, msg);
		}
	}
}
