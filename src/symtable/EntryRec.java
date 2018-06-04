package symtable;

//lista de EntryClass (TA ENTRYCLASS NO LIVRO, PG 196 IMPRESSA) usada para representar os tipos de uma lista de par�metros
public class EntryRec extends EntryTable{

	public EntryTable type;		//tipo de um objeto
	public int dim;				//dimens�o
	public EntryRec next;		//apontador para o resto da linha
	public int cont;			//n�mero de elementos a partir daquele elemento;
	
	//pedido para o T4
	boolean opcional; //identifica se o par�metro � opcional ou n�o.
	
	//cria elemento
	public EntryRec (EntryTable p, int d, int c, boolean o) {
		type = p;
		cont = c;
		dim = d;
		next = null;
		opcional = o;
	}
	//cria elemento e p�e no in�cio da lista
	public EntryRec (EntryTable p, int d, int c, EntryRec t) {
		type = p;
		cont = c;
		dim = d;
		next = t;
	}
	
}
