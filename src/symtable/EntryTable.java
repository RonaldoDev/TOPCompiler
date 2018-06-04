package symtable;

// classe geral para as possíveis entradas na tabela de símbolos
public abstract class EntryTable {
	public String name; //nome do símbolo (var. método ou classe);
	public EntryTable next; //apontador para o próximo dentro da tabela.
	public int scope; //número do aninhamento corrente (o método add da Symtable atualiza o valor dessa variável quando a entrada é inserida na tabela)
	public Symtable mytable; //aponta para a tabela da qual ela é parte

}
