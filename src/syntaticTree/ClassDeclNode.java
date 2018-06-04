package syntaticTree;

import parser.Token;

public class ClassDeclNode extends GeneralNode {
	public Token name, supername;
	public ClassBodyNode body;
	
	public ClassDeclNode(Token t1, Token t2, Token t3, ClassBodyNode c)
	{
		super(t1); // passa token de referencia para construtor da superclass
		name = t2;
		supername =t3;
		body = c;
	}

}
