package symtable;

/**
 * Classe geral para as possíveis entradas na tabela de símbolo
 * 
 * @author jean
 */
public abstract class EntryTable {

	/**
	 * Nome do símbolo (variável, método ou classe)
	 */
	public String name;
	
	/**
	 * Apontador para próximo dentro da tabela
	 */
	public EntryTable next;
	
	/**
	 * Número do aninhamento corrente
	 */
	public int scope;
	
	/**
	 * Aponta para tabela da qual ela faz parte
	 */
	public Symtable mytable;
	
	abstract public String dscJava();
	
    static public String strDim(int n) {
        String p = "";

        for (int i = 0; i < n; i++)
            p += "[";

        return p;
    }
}
