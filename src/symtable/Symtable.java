package symtable;

public class Symtable {
	//apontador para o topo da tabela (mais recente) (aponta para a cabeça da lista)
	public EntryTable top;
	
	// número que controla o escopo (aninhamento) corrente
	public int scptr;
	
	//número para a entrada EntryClass de nível superior (identifica a qual classe a tabela pertence )
	public EntryClass levelup;
	
	public Symtable() { //Cria uma tabela vazia
		top = null;
		scptr = 0;
		levelup = null;
	}
	//cria tabela vazia apontando para nível superior
	public Symtable(EntryClass up) {
		top = null;
		scptr = 0;
		levelup = up;
	}
	
	public void add(EntryTable x) { //adiciona uma entrada na tabela
		x.next = top; // inclui nova entrada no topo
		top = x;
		x.scope = scptr; //atribui para a entrada o número do escopo
		x.mytable = this; // faz a entrada apontar para a própria tabela
	}
	
	public void beginScope() {
		scptr++; //inicia novo alinhamento de variáveis
	}
	
	public void endScope() {
		while(top != null && top.scope == scptr) {
			top = top.next; // retira todas vars do aninhamento corrente
			scptr--; 		//finaliza aninhamento corrente
		}
	}
	
}
