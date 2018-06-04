package symtable;

//lista de EntryClass (TA ENTRYCLASS NO LIVRO, PG 196 IMPRESSA) usada para representar os tipos de uma lista de parâmetros
public class EntryRec extends EntryTable{

	public EntryTable type;		//tipo de um objeto
	public int dim;				//dimensão
	public EntryRec next;		//apontador para o resto da linha
	public int cont;			//número de elementos a partir daquele elemento;
	
	//pedido para o T4
	boolean opcional; //identifica se o parâmetro é opcional ou não.
	
	//cria elemento
	public EntryRec (EntryTable p, int d, int c, boolean o) {
		type = p;
		cont = c;
		dim = d;
		next = null;
		opcional = o;
	}
	//cria elemento e põe no início da lista
	public EntryRec (EntryTable p, int d, int c, EntryRec t) {
		type = p;
		cont = c;
		dim = d;
		next = t;
	}
	
}
