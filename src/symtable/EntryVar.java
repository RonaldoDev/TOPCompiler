package symtable;

//classe que abriga uma declara��o de vari�vel na tabela de s�mbolos
public class EntryVar extends EntryTable {
	
	public EntryTable type; //apontador para o tipo da vari�vel
	public int dim; 		//n�mero de dimens�es da vari�vel
	public int localcount; 	//n�mera��o sequencial para as vari�veis locais.
	
	//cria uma entrada para a vari�vel de classe
	public EntryVar(String n, EntryTable p, int d) {
		name = n; 	//nome da vari�vel
		type = p; 	//apontador para a classe;
		dim = d;	//n�mero de dimens�es;
		localcount = -1; //n�mero sequencial � sempre -1 (n�o local)
	}
	
	//cria uma entrada para a var�avel local
	public EntryVar(String n, EntryTable p, int d, int k) {
		name = n; 	//nome da vari�vel
		type = p; 	//apontador para a classe;
		dim = d;	//n�mero de dimens�es;
		localcount = k; //inclui tamb�m o n�mero sequencial
	}
	
}
