package syntaticTree;

import parser.Token;

public class IfNode extends StatementNode {
	public StatementNode stat1, stat2;
	public ExpreNode expr;
	
	public IfNode(Token t, ExpreNode e, StatementNode s1, StatementNode s2)
	{
		super(t);
		expr = e;
		stat1 = s1;
		stat2 = s2;
	}
}