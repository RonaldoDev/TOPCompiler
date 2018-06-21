package symtable;

/**
 * Corresponde a uma declaração de método na tabela de símbolos
 * 
 * @author jean
 */
public class EntryMethod extends EntryTable {

	/**
	 * Tipo de retorno do mátodo
	 */
	public EntryTable type;

	/**
	 * Número de dimensões do retorno
	 */
	public int dim;

	/**
	 * Tipos dos parâmetros
	 */
	public EntryRec param;

	/**
	 * Número de variáveis locais
	 */
	public int totallocals;

	/**
	 * Tamanho da pilha necessária
	 */
	public int totalStack;

	/**
	 * Define se o construtor é false
	 */
	public boolean fake;

	/**
	 * Define se método possui chamada super
	 */
	public boolean hassuper;

	/**
	 * Cria elemento para inserir na tabela
	 */
	public EntryMethod(String n, EntryTable p, int d, EntryRec r) {
		name = n;
		type = p;
		dim = d;
		param = r;
		totallocals = 0;
		totalStack = 0;
		fake = false;
		hassuper = false;
	}

	/**
	 * Cria elemento para inserir na tabela
	 */
	public EntryMethod(String n, EntryTable p, boolean b) {
		name = n;
		type = p;
		dim = 0;
		param = null;
		totallocals = 0;
		totalStack = 0;
		fake = b;
		hassuper = false;
	}

	public String dscJava() {
		String s = strDim(dim);
		s += type.dscJava();

		return s;
	}
}
