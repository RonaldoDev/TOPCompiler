package symtable;

// classe geral para as poss�veis entradas na tabela de s�mbolos
public abstract class EntryTable {
	public String name; //nome do s�mbolo (var. m�todo ou classe);
	public EntryTable next; //apontador para o pr�ximo dentro da tabela.
	public int scope; //n�mero do aninhamento corrente (o m�todo add da Symtable atualiza o valor dessa vari�vel quando a entrada � inserida na tabela)
	public Symtable mytable; //aponta para a tabela da qual ela � parte

}
