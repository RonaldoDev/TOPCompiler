package syntaticTree;

import parser.Token;

public class PrintNode extends StatementNode {
	public ExpreNode expr;
	
	public PrintNode(Token t, ExpreNode e)
	{
		super(t);
		expr = e;
	}
}
