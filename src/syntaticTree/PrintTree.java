package syntaticTree;

import parser.Token;

public class PrintTree {
	int kk;

	public PrintTree() {
		kk = 1; // inicializa contador de nos
	}

	public void printRoot(ListNode x) {
		if (x == null) {
			System.out.println("Empty syntatic tree. Nothing to be printed");
		} else {
			numberClassDeclListNode(x);
			printClassDeclListNode(x);
		}
		System.out.println();
	}

	public void numberClassBodyNode(ClassBodyNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberClassDeclListNode(x.clist);
		numberVarDeclListNode(x.vlist);
		numberConstructDeclListNode(x.ctlist);
		numberMethodDeclListNode(x.mlist);
	}

	public void numberListNode(ListNode x) {
		if (x == null) {
			return;
		}
		numberListNode(x.next); // numera x.node
	}

	public void numberClassDeclListNode(ListNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberClassDeclNode((ClassDeclNode) x.node);
		numberClassDeclListNode(x.next);
	}

	public void numberMethodBodyNode(MethodBodyNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberVarDeclListNode(x.param);
		numberStatementNode(x.stat);
	}

	public void numberStatementNode(StatementNode x) {
		if (x instanceof BlockNode) {
			numberBlockNode((BlockNode) x);
		} else if (x instanceof VarDeclNode) {
			numberVarDeclNode((VarDeclNode) x);
		} else if (x instanceof AtribNode) {
			numberAtribNode((AtribNode) x);
		} else if (x instanceof IfNode) {
			numberIfNode((IfNode) x);
		} else if (x instanceof ForNode) {
			numberForNode((ForNode) x);
		} else if (x instanceof PrintNode) {
			numberPrintNode((PrintNode) x);
		} else if (x instanceof NopNode) {
			numberNopNode((NopNode) x);
		} else if (x instanceof ReadNode) {
			numberReadNode((ReadNode) x);
		} else if (x instanceof ReturnNode) {
			numberReturnNode((ReturnNode) x);
		} else if (x instanceof SuperNode) {
			numberSuperNode((SuperNode) x);
		} else if (x instanceof BreakNode) {
			numberBreakNode((BreakNode) x);
		}
	}

	public void numberBlockNode(BlockNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberListNode(x.stats);
	}

	public void numberVarDeclNode(VarDeclNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberListNode(x.vars);
	}

	public void numberAtribNode(AtribNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberStatementNode(x.expr1);
		numberStatementNode(x.expr2);
	}

	public void numberIfNode(IfNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
		numberStatementNode(x.stat1);
		numberStatementNode(x.stat2);
	}

	public void numberForNode(ForNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
		numberAtribNode(x.init);
		numberAtribNode(x.incr);
		numberStatementNode(x.stat);
	}

	public void numberPrintNode(PrintNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
	}

	public void numberNopNode(NopNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
	}

	public void numberReadNode(ReadNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
	}

	public void numberReturnNode(ReturnNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
	}

	public void numberSuperNode(SuperNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberListNode(x.args);
	}

	public void numberBreakNode(BreakNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
	}

	public void numberExpreNode(ExpreNode x) {
		if (x instanceof NewObjectNode) {
			numberNewObjectNode((NewObjectNode) x);
		} else if (x instanceof NewArrayNode) {
			numberNewArrayNode((NewArrayNode) x);
		} else if (x instanceof RelationalNode) {
			numberRelationalNode((RelationalNode) x);
		} else if (x instanceof AddNode) {
			numberAddNode((AddNode) x);
		} else if (x instanceof MultNode) {
			numberMultNode((MultNode) x);
		} else if (x instanceof UnaryNode) {
			numberUnaryNode((UnaryNode) x);
		} else if (x instanceof CallNode) {
			numberCallNode((CallNode) x);
		} else if (x instanceof IntConstNode) {
			numberIntConstNode((IntConstNode) x);
		} else if (x instanceof StringConstNode) {
			numberStringConstNode((StringConstNode) x);
		} else if (x instanceof NullConstNode) {
			numberNullConstNode((NullConstNode) x);
		} else if (x instanceof IndexNode) {
			numberIndexNode((IndexNode) x);
		} else if (x instanceof DotNode) {
			numberDotNode((DotNode) x);
		} else if (x instanceof VarNode) {
			numberVarNode((VarNode) x);
		}
	}

	public void numberNewObjectNode(NewObjectNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberListNode(x.args); // NewObjectNode tem um Token t2 como parâmetro. Adiciona ele também?
	}

	public void numberNewArrayNode(NewArrayNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberListNode(x.dims);
	}

	public void numberRelationalNode(RelationalNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr1);
		numberExpreNode(x.expr2);
	}

	public void numberAddNode(AddNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr1);
		numberExpreNode(x.expr2);
	}

	public void numberMultNode(MultNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr1);
		numberExpreNode(x.expr2);
	}

	public void numberUnaryNode(UnaryNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
	}

	public void numberCallNode(CallNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
		numberListNode(x.args);
	}

	public void numberIntConstNode(IntConstNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
	}

	public void numberStringConstNode(StringConstNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
	}

	public void numberNullConstNode(NullConstNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
	}

	public void numberIndexNode(IndexNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expre1);
		numberExpreNode(x.expre2);
	}

	public void numberDotNode(DotNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
		numberExpreNode(x.expr);
	}

	public void numberVarNode(VarNode x) { // verificar o parametro int no VarNode.java
		if (x == null) {
			return;
		}
		x.number = kk++;
	}
	
	public void printClassBodyNode(ClassBodyNode x) {
		if (x == null) {
			return;
		}
		System.out.println();
		System.out.println(x.number + ": ClassBodyNode ===> " + (x.clist == null ? "null" : String.valueOf(x.clist.number)) 
														+ " " + (x.vlist == null ? "null" : String.valueOf(x.vlist.number))
														+ " " + (x.ctlist == null ? "null" : String.valueOf(x.ctlist.number))
														+ " " + (x.mlist == null ? "null" : String.valueOf(x.mlist.number)));
		
		printClassDeclListNode(x.clist);
		printVarDeclListNode(x.vlist);
		printConstructDeclListNode(x.ctlist);
		printMethodDeclListNode(x.mlist);
	}
	
	public void printClassDeclNode(ClassDeclNode x)
	{
		if (x == null) {
			return;
		}
		System.out.println();
		System.out.println(x.number + ": ClassDeclNode ===> " + x.name.image + " " 
															  + (x.supername == null ? "null": x.supername.image) + " "
															  + (x.body == null ? "null": String.valueOf(x.body.number)));
		printClassBodyNode(x.body);
	}

}
