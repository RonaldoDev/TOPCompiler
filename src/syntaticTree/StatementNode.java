package syntaticTree;

import parser.Token;

abstract public class StatementNode extends GeneralNode {
	public StatementNode(Token t)
	{
		super(t);
	}
}
