package symtable;

//classe correspondente a uma declara��o de classe na tabela de s�mbolos
public class EntryClass extends EntryTable {
	public Symtable nested; //tabela para declara��o de elementos aninhados
	public EntryClass parent; //entrada correspondete a superclasse
	
	public EntryClass(String n, Symtable t) {
		name = n; // nome da classe declarada
		nested = new Symtable(this); // tabela onde inserir vari�veis (m�todos ou classes);
		parent = null; //sua superclasse
	}
}
