package semanalysis;

import symtable.*;
import syntaticTree.*;

public class ClassCheck {
	Symtable Maintable; 			//tabela de mais alto n�vel
	protected Symtable Curtable;	//apontador para a tabela corrente
	int foundSemanticError;
	
	public ClassCheck() {
		EntrySimple k;
		
			foundSemanticError = 0;
			Maintable = new Symtable();		//cria tabela principal
			k = new EntrySimple("int");		//insere tipos b�sicos da linguagem
			Maintable.add(k);
			k = new EntrySimple("string");
			Maintable.add(k);
	}
	
	public void ClassCheckRoot(ListNode x) throws SemanticException{
		Curtable = Maintable; 			//tabela corrente = principal
		ClassCheckClassDeclListNode(x);	//chama an�lise para raiz da �rvore
		if(foundSemanticError != 0) { 	//se houver erros lan�a excess�o
			throw new SemanticException(foundSemanticError + "Semantic Error found (phase 1)");
		}
	}

	public void ClassCheckClassDeclListNode(ListNode x) {
		
		if( x == null) {
			return;
		}
		
		try {
			ClassCheckClassDeclNode((ClassDeclNode) x.node);
		} catch (SemanticException e) {
			//se um erro ocorreu na an�lise da classe,
			//d� a mensagem, mas faz a an�lise para pr�xima classe
			System.out.println(e.getMessage());
			foundSemanticError++;
		}
		ClassCheckClassDeclListNode(x.next);
	}

	public void ClassCheckClassDeclNode(ClassDeclNode x) throws SemanticException {
		Symtable temphold = Curtable;			// Salva apontador p/ tabela corrente
		EntryClass nc;
		
		if(x == null) return;
		//procura classe na tabela
		
		nc = (EntryClass) Curtable.classFindUp(x.name.image);
		
		if(nc != null) { // j� declarada, ERRO. 206 #218
			throw new SemanticException(x.name + " Classe " + x.name.image + " j� declarada");
		}else {
			System.out.println("Classe Encontrada ==> " + x.name.image + " classe adicionada na tabela de s�mbolos");
		}
		
		//inclui classe na tabela corrente
		Curtable.add(nc = new EntryClass(x.name.image, Curtable));
		Curtable = nc.nested; //tabela corrente = tabela da classe
		ClassCheckClassBodyNode(x.body);
		Curtable = temphold;	//recupera apontador p/ tabela corrente
	}

	public void ClassCheckClassBodyNode(ClassBodyNode x) {

		if(x == null) return;
		ClassCheckClassDeclListNode(x.clist);
	}
}
