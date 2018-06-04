package syntaticTree;

import parser.Token;

abstract public class GeneralNode {
	public Token position;
	
	public GeneralNode(Token x)
	{
		position = x;
	}
}
