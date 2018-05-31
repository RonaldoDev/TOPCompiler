package symtable;

//classe correspondente a uma declaração de classe na tabela de símbolos
public class EntryClass extends EntryTable {
	public Symtable nested; //tabela para declaração de elementos aninhados
	public EntryClass parent; //entrada correspondete a superclasse
	
	public EntryClass(String n, Symtable t) {
		name = n; // nome da classe declarada
		nested = new Symtable(this); // tabela onde inserir variáveis (métodos ou classes);
		parent = null; //sua superclasse
	}
}
