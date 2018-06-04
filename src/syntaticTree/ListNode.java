package syntaticTree;

public class ListNode extends GeneralNode {
	public GeneralNode node;
	public ListNode next;
	
	public ListNode(GeneralNode t2)
	{
		super(t2.position); // passa token de ref para const da superclasse, mesmo que o filho
		node = t2;
		next = null; // primeiro elemento da lista
	}
	
	public ListNode(GeneralNode t2, ListNode l)
	{
		super(t2.position);
		node = t2;
		next = l;
	}
	
	public void add(GeneralNode t2)
	{
		if (next == null) // verifica se é o ultimo da lsita
			next = new ListNode(t2);
		else
			next.add(t2);
	}
}
