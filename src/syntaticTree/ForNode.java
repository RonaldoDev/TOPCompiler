package syntaticTree;

import parser.Token;

public class ForNode extends StatementNode {
	public AtribNode init, incr;
	public StatementNode stat;
	public ExpreNode expr;
	
	public ForNode(Token t, ExpreNode e, AtribNode s1, AtribNode s2, StatementNode s3)
	{
		super(t);
		expr = e;
		init = s1;
		incr = s2;
		stat = s3;
	}
}