package syntaticTree;

import parser.Token;

public class SuperNode extends StatementNode {
	public ListNode args;
	
	public SuperNode(Token t, ListNode l)
	{
		super(t);
		args = l;
	}
}