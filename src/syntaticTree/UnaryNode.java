package syntaticTree;

import parser.Token;

public class UnaryNode extends ExpreNode {
	public ExpreNode expr;
	public UnaryNode(Token t, ExpreNode e)
	{
		super(t);
		expr = e;
	}
}
