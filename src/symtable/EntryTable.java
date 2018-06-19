package symtable;


// classe geral para as poss�veis entradas na tabela de s�mbolos
abstract public class EntryTable {
    public String name; // nome do s�mbolo (var., m�todo ou classe)
    public EntryTable next; // apontador para pr�ximo dentro da tabela 
    public int scope; // n�mero do aninhamento corrente
    public Symtable mytable; // entrada aponta para a tabela da qual ela � parte

    abstract public String dscJava();

    static public String strDim(int n) {
        String p = "";

        for (int i = 0; i < n; i++)
            p += "[";

        return p;
    }
}
