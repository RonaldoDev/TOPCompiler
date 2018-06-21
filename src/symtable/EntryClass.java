package symtable;

/**
 * Corresponde a uma declaraÃ§Ã£o de classe na tabela de sÃ­mbolos
 * 
 * @author jean
 */
public class EntryClass extends EntryTable {

	/**
	 * Tabela para declaraÃ§Ã£o de elemtentos aninhados
	 */
	public Symtable nested;
	
	/**
	 * Entrada correspondenta Ã  superclasse
	 */
	public EntryClass parent;
	
	/**
	 * Construtor da classe
	 * 
	 * @param n
	 * @param t
	 */
	public EntryClass(String n, Symtable t) {
		name 	= n;
		nested 	= new Symtable(this);
		parent 	= null;
	}

	@Override
	public String dscJava() {
		// TODO Auto-generated method stub
		return null;
	}
}