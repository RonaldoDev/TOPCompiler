package semanalysis;

import symtable.EntryClass;
import symtable.EntrySimple;
import symtable.Symtable;
import syntaticTree.*;

public class ClassCheck {

	/**
	 * Tabela de mais alto n�vel
	 */
	Symtable mainTable;
	
	/**
	 * Apontador para a tebela corrente
	 */
	protected Symtable curTable;
	
	/**
	 * Contador de erros sem�nticos
	 */
	int foundSemanticError;
	
	/**
	 * Construtor da classe
	 */
	public ClassCheck() {
		EntrySimple k;
		
		foundSemanticError = 0;
		mainTable = new Symtable();
		k = new EntrySimple("int");
		mainTable.add(k);
		k = new EntrySimple("string");
		mainTable.add(k);
		k = new EntrySimple("float");
		mainTable.add(k);
		k = new EntrySimple("boolean");
		mainTable.add(k);
		k = new EntrySimple("char");
		mainTable.add(k);
	}
	
	/**
	 * 
	 */
	public void classCheckRoot(ListNode x) throws SemanticException {
		System.out.println();
		curTable = mainTable;				// tabela corrente = principal
		classCheckClassDeclListNode(x);		// Chama analisa para ra�z da �rvore
		if (foundSemanticError != 0) {		// Se houver erro lan�a exec��o
			throw new SemanticException(foundSemanticError + " Semantic errors found (phase 1)");
		}
	}
	
	/**
	 * 
	 */
	public void classCheckClassDeclListNode(ListNode x) {
		if (x == null) return;
		try {
			classCheckClassDeclNode((ClassDeclNode) x.node);
		} catch (SemanticException e) {
			/**
			 * Se erro ocorreu na an�lise da classe, 
			 * da a mensagem, mas faz a an�lise para 
			 * a pr�xima classe
			 */
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
		classCheckClassDeclListNode(x.next);
	}
	
	/**
	 * 
	 */
	public void classCheckClassDeclNode(ClassDeclNode x) throws SemanticException {
		Symtable temphold = curTable;		// Salva apontador para tabela corrente
		EntryClass nc;
		if (x == null) return;
		// Procura classe na tabela
		nc = (EntryClass) curTable.classFindUp(x.name.image);
		if (nc != null)	{ // J� declarada, ERRO
			
			// Requisito do trbalho. Imprimir o nome da classe encontrada
			System.out.println("Classe encontrada " + nc.name);
			
			throw new SemanticException(x.name, "Class " + x.name.image + " already declared");
		}
		
		// Inclui classe na tabela corrente
		curTable.add(nc = new EntryClass(x.name.image, curTable));
		
		// Requisito do trbalho. Imprimir o nome da classe inserida na tabela
		System.out.println("Classe adicionada na tabela de s�mbolos: " + nc.name);
		
		curTable = nc.nested;		// Tabela corrente = tabela da classe
		classCheckClassBodyNode(x.body);
		curTable = temphold;			// Recupera apontador para tabela corrente
	}
	
	/**
	 * 
	 */
	public void classCheckClassBodyNode(ClassBodyNode x) {
		if (x == null) return;
		classCheckClassDeclListNode(x.clist);
	}
}
