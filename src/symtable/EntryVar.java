package symtable;

/**
 * Abriga umade claração de vari;avel na tabela de símbolos 
 * 
 * @author jean
 */
public class EntryVar extends EntryTable {

	/**
	 * Apontador para o tipo de variável
	 */
	public EntryTable type;
	
	/**
	 * Número de dimenões da variável
	 */
	public int dim;
	
	/**
	 * Numeração sequencial para as vari;aveis locais
	 */
	public int localcount;
	
	/**
	 * Cria uma entrada para variável de classe
	 */
	public EntryVar(String n, EntryTable p, int d) {
		name 		= n;
		type 		= p;
		dim 			= d;
		localcount 	= -1;
	}
	
	/**
	 * Cria uma entrada para variável local
	 */
	public EntryVar(String n, EntryTable p, int d, int k) {
		name 		= n;
		type 		= p;
		dim 			= d;
		localcount 	= k;
	}
	
    public String dscJava() {
        String s = strDim(dim);
        s += type.dscJava();

        return s;
    }
}
