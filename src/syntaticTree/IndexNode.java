package syntaticTree;

import parser.Token;

public class IndexNode extends ExpreNode {
	public ExpreNode expre1, expre2;
	
	public IndexNode(Token t, ExpreNode e1, ExpreNode e2)
	{
		super(t);
		expre1 = e1;
		expre2 = e2;
	}

}
