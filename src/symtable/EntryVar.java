package symtable;

//classe que abriga uma declaração de variável na tabela de símbolos
public class EntryVar extends EntryTable {
	
	public EntryTable type; //apontador para o tipo da variável
	public int dim; 		//número de dimensões da variável
	public int localcount; 	//númeração sequencial para as variáveis locais.
	
	//cria uma entrada para a variável de classe
	public EntryVar(String n, EntryTable p, int d) {
		name = n; 	//nome da variável
		type = p; 	//apontador para a classe;
		dim = d;	//número de dimensões;
		localcount = -1; //número sequencial é sempre -1 (não local)
	}
	
	//cria uma entrada para a varíavel local
	public EntryVar(String n, EntryTable p, int d, int k) {
		name = n; 	//nome da variável
		type = p; 	//apontador para a classe;
		dim = d;	//número de dimensões;
		localcount = k; //inclui também o número sequencial
	}
	
}
