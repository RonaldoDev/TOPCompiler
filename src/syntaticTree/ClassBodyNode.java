package syntaticTree;

import parser.Token;

public class ClassBodyNode extends GeneralNode {
	public ListNode clist; // lista de classes aninhadas
	public ListNode vlist; // lista de variaver de classes
	public ListNode ctlist; // lista de construtores
	public ListNode mlist; // lista de métodos
	
	public ClassBodyNode(Token t1, ListNode c, ListNode v, ListNode ct, ListNode m)
	{
		super(t1); // passa token de referencia para construtor da superclasse
		clist = c;
		vlist = v;
		ctlist = ct;
		mlist = m;
	}
}
