package symtable;

public class Symtable {
	//apontador para o topo da tabela (mais recente) (aponta para a cabe�a da lista)
	public EntryTable top;
	
	// n�mero que controla o escopo (aninhamento) corrente
	public int scptr;
	
	//n�mero para a entrada EntryClass de n�vel superior (identifica a qual classe a tabela pertence )
	public EntryClass levelup;
	
	public Symtable() { //Cria uma tabela vazia
		top = null;
		scptr = 0;
		levelup = null;
	}
	//cria tabela vazia apontando para n�vel superior
	public Symtable(EntryClass up) {
		top = null;
		scptr = 0;
		levelup = up;
	}
	
	public void add(EntryTable x) { //adiciona uma entrada na tabela
		x.next = top; // inclui nova entrada no topo
		top = x;
		x.scope = scptr; //atribui para a entrada o n�mero do escopo
		x.mytable = this; // faz a entrada apontar para a pr�pria tabela
	}
	
	public void beginScope() {
		scptr++; //inicia novo alinhamento de vari�veis
	}
	
	public void endScope() {
		while(top != null && top.scope == scptr) {
			top = top.next; // retira todas vars do aninhamento corrente
			scptr--; 		//finaliza aninhamento corrente
		}
	}
	
}
