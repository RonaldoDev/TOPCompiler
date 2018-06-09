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
	
  public void printClassDeclListNode(ListNode x) {
    if (x == null) return;

    System.out.println(x.number + ": ListNode (ClassDeclNode)  ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));

    printClassDeclNode((ClassDeclNode) x.node);
    printClassDeclListNode(x.next);
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
	
  public void numberVarDeclListNode(ListNode x) {
    if (x == null) return;

    x.number = kk++;
    
    if (x.node instanceof VarDeclNode) {
    	numberVarDeclNode((VarDeclNode) x.node);
    } else if (x.node instanceof AssignConstNode) {
    	numberAssignConstNode((AssignConstNode) x.node);
    }
    numberVarDeclListNode(x.next);
  }
  
  public void numberConstructDeclListNode(ListNode x) {
    if (x == null) return;

    x.number = kk++;
    numberConstructDeclNode((ConstructDeclNode) x.node);
    numberConstructDeclListNode(x.next);
  }
  
  public void numberConstructDeclNode(ConstructDeclNode x) {
    if (x == null) return;

    x.number = kk++;
    numberMethodBodyNode(x.body);
  }
  
  public void numberMethodDeclListNode(ListNode x) {
    if(x == null) return;

    x.number = kk++;
    numberMethodDeclNode((MethodDeclNode) x.node);
    numberMethodDeclListNode(x.next);
  }
  
  public void numberMethodDeclNode(MethodDeclNode x) {
    if(x == null) return;

    x.number = kk++;
    numberMethodBodyNode(x.body);
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
	
  public void numberClassDeclNode(ClassDeclNode x) {
    if (x == null) return;

    x.number = kk++;
    numberClassBodyNode(x.body);
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
	
	public void numberAssignConstNode(AssignConstNode x) {
		if (x == null) {
			return;
		}
		x.number = kk++;
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
	
  public void printVarDeclListNode(ListNode x) {
    if (x == null) return;

    
    System.out.println(x.number + ": ListNode (VarDeclNode) ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));

    if (x.node instanceof VarDeclNode) {
    	printVarDeclNode((VarDeclNode) x.node);
    } else if (x.node instanceof AssignConstNode) {
    	printAssignConstNode((AssignConstNode) x.node);
    }
    printVarDeclListNode(x.next);
  }
  public void printConstructDeclListNode(ListNode x) {
    if (x == null) return;

    System.out.println(x.number + ": ListNode (ConstructDeclNode) ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));

    printConstructDeclNode((ConstructDeclNode) x.node);
    printConstructDeclListNode(x.next);
  }
  
  public void printVarDeclNode(VarDeclNode x) {
    if (x == null) return; 

    System.out.println(x.number + ": VarDeclNode ===> " + x.position.image +
        " " + x.vars.number);
    printVarListNode(x.vars);
  }
  public void printConstructDeclNode(ConstructDeclNode x) {
    if(x == null) return;

    System.out.println(x.number + ": ConstructDeclNode ===> " +
        x.body.number);
    printMethodBodyNode(x.body);
  }
  
  public void printVarListNode(ListNode x) {
    if (x == null) return;

    
    System.out.println(x.number + ": ListNode (VarNode) ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));
//    if(x.node instanceof VarNode)
//    	printVarNode((VarNode) x.node);
//    else if (x.node instanceof UnaryNode)
    	printUnaryNode((UnaryNode) x.node);
    printVarListNode(x.next);
  }
  
  public void printMethodBodyNode(MethodBodyNode x) {
    if(x == null) return;

    System.out.println(x.number + ": MethodBodyNode ===> " +
        ((x.param == null) ? "null" : String.valueOf(x.param.number)) +
        " " + x.stat.number);
    printVarDeclListNode(x.param);
    printStatementNode(x.stat);
  }
  
  public void printAssignConstNode(AssignConstNode x) {
    if(x == null) return;

    System.out.println(x.number + ": AssignConstNode ===> " +
        x.position.image);
  }
  public void printVarNode(VarNode x) {
    if(x == null) return;

    System.out.println(x.number + ": VarNode ===> " + x.position.image + " " +
        ((x.dim == 0) ? "" : ("[" + x.dim + "]")));
  }
  
  public void printMethodDeclListNode(ListNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": ListNode (MethodDeclNode) ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));
    printMethodDeclNode((MethodDeclNode) x.node);
    printMethodDeclListNode(x.next);
  }
  public void printStatementNode(StatementNode x) {
    if (x instanceof BlockNode) {
        printBlockNode((BlockNode) x);
    } else if (x instanceof VarDeclNode) {
        printVarDeclNode((VarDeclNode) x);
    } else if (x instanceof AtribNode) {
        printAtribNode((AtribNode) x);
    } else if (x instanceof IfNode) {
        printIfNode((IfNode) x);
    } else if (x instanceof ForNode) {
        printForNode((ForNode) x);
    } else if (x instanceof PrintNode) {
        printPrintNode((PrintNode) x);
    } else if (x instanceof NopNode) {
        printNopNode((NopNode) x);
    } else if (x instanceof ReadNode) {
        printReadNode((ReadNode) x);
    } else if (x instanceof ReturnNode) {
        printReturnNode((ReturnNode) x);
    } else if (x instanceof SuperNode) {
        printSuperNode((SuperNode) x);
    } else if (x instanceof BreakNode) {
        printBreakNode((BreakNode) x);
    }
  }
  public void printMethodDeclNode(MethodDeclNode x) {
    if(x == null) return;

    System.out.println(x.number + ": MethodDeclNode ===> " +
        x.position.image + " " + ((x.dim == 0) ? "" : ("[" + x.dim + "] ")) +
        x.name.image + " " + x.body.number);
    printMethodBodyNode(x.body);
  }
  
  public void printBlockNode(BlockNode x) {
    if(x == null) return;

    System.out.println(x.number + ": BlockNode ===> " + x.stats.number);
    printStatementListNode(x.stats);
  }
  
  public void printAtribNode(AtribNode x) {
    if(x == null) return;

    System.out.println(x.number + ": AtribNode ===> " + x.expr1.number + " " +
        x.expr2.number);
    printExpreNode(x.expr1);
    printExpreNode(x.expr2);
  }
  
  public void printIfNode(IfNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": IfNode ===> " + x.expr.number + " " +
        x.stat1.number + " " +
        ((x.stat2 == null) ? "null" : String.valueOf(x.stat2.number)));

    printExpreNode(x.expr);
    printStatementNode(x.stat1);
    printStatementNode(x.stat2);
  }
  
  public void printForNode(ForNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": ForNode ===> " +
        ((x.init == null) ? "null" : String.valueOf(x.init.number)) + " " +
        ((x.expr == null) ? "null" : String.valueOf(x.expr.number)) + " " +
        ((x.incr == null) ? "null" : String.valueOf(x.incr.number)) + " " +
        x.stat.number);

    printAtribNode(x.init);
    printExpreNode(x.expr);
    printAtribNode(x.incr);
    printStatementNode(x.stat);
  }
  
  public void printPrintNode(PrintNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": PrintNode ===> " + x.expr.number);
    printExpreNode(x.expr);
  }
  public void printNopNode(NopNode x) {
    if(x == null) return;

    System.out.println(x.number + ": NopNode");
  }
  
  public void printReadNode(ReadNode x) {
    if(x == null) return;

    System.out.println(x.number + ": ReadNode ===> " + x.expr.number);
    printExpreNode(x.expr);
  }
  
  public void printReturnNode(ReturnNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": ReturnNode ===> " +
        ((x.expr == null) ? "null" : String.valueOf(x.expr.number)));
    printExpreNode(x.expr);
  }
  
  public void printSuperNode(SuperNode x) {
    if(x == null) return;

    System.out.println(x.number + ": SuperNode ===> " +
        ((x.args == null) ? "null" : String.valueOf(x.args.number)));
    printExpreListNode(x.args);
  }
  
  public void printBreakNode(BreakNode x) {
    if(x == null) return;

    System.out.println(x.number + ": BreakNode");
  }

	public void printExpreNode(ExpreNode x) {
	  if (x instanceof NewObjectNode) {
	      printNewObjectNode((NewObjectNode) x);
	  } else if (x instanceof NewArrayNode) {
	      printNewArrayNode((NewArrayNode) x);
	  } else if (x instanceof RelationalNode) {
	      printRelationalNode((RelationalNode) x);
	  } else if (x instanceof AddNode) {
	      printAddNode((AddNode) x);
	  } else if (x instanceof MultNode) {
	      printMultNode((MultNode) x);
	  } else if (x instanceof UnaryNode) {
	      printUnaryNode((UnaryNode) x);
	  } else if (x instanceof CallNode) {
	      printCallNode((CallNode) x);
	  } else if (x instanceof IntConstNode) {
	      printIntConstNode((IntConstNode) x);
	  } else if (x instanceof AssignConstNode) {
	      printAssignConstNode((AssignConstNode) x);
	  } else if (x instanceof StringConstNode) {
	      printStringConstNode((StringConstNode) x);
	  } else if (x instanceof NullConstNode) {
	      printNullConstNode((NullConstNode) x);
	  } else if (x instanceof CharConstNode) {
	      printCharConstNode((CharConstNode) x);
	  } else if (x instanceof FloatConstNode) {
	      printFloatConstNode((FloatConstNode) x);
	  } else if (x instanceof BoolConstNode) {
	      printBoolConstNode((BoolConstNode) x);
	  } else if (x instanceof DotNode) {
	      printDotNode((DotNode) x);
	  } else if (x instanceof VarNode) {
	      printVarNode((VarNode) x);
	  }
	}
	
  public void printStatementListNode(ListNode x) {
    if(x == null) return;

    System.out.println(x.number + ": ListNode (StatementNode) ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));

    printStatementNode((StatementNode) x.node);
    printStatementListNode(x.next);
  }
  
  public void printNewObjectNode(NewObjectNode x) {
    if(x == null) return;

    System.out.println(x.number + ": NewObjectNode ===> " + x.name.image +
        " " + ((x.args == null) ? "null" : String.valueOf(x.args.number)));

    printExpreListNode(x.args);
  }
  public void printNewArrayNode(NewArrayNode x) {
    if(x == null) return;
    
    System.out.println(x.number + ": NewArrayNode ===> " + x.name.image +
        " " + ((x.dims == null) ? "null" : String.valueOf(x.dims.number)));

    printExpreListNode(x.dims);
  }
  
  public void printRelationalNode(RelationalNode x) {
    if(x == null) return;
    
    boolean hasExpr2 = x.expr2 != null;
    
    String relation = x.number + ": RelationalNode ===>";
    String expr1 = String.valueOf(x.expr1.number);
    String expr2 = hasExpr2 ? String.valueOf(x.expr2.number) : "null"; 
    
    String output = String.join(" ", relation, expr1, x.position.image, expr2);
    
    System.out.println(output);
    
    printExpreNode(x.expr1);
    
    if (hasExpr2) {
    	printExpreNode(x.expr2);
    }
  }
  
  public void printAddNode(AddNode x) {
    if(x == null) return;

    System.out.println(x.number + ": AddNode ===> " + x.expr1.number + " " +
        x.position.image + " " + x.expr2.number);
    printExpreNode(x.expr1);
    printExpreNode(x.expr2);
  }
  
  public void printMultNode(MultNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": MultNode ===> " + x.expr1.number + " " +
        x.position.image + " " + x.expr2.number);
    printExpreNode(x.expr1);
    printExpreNode(x.expr2);
  }
  
  public void printExpreListNode(ListNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": ListNode (ExpreNode) ===> " +
        x.node.number + " " +
        ((x.next == null) ? "null" : String.valueOf(x.next.number)));
    printExpreNode((ExpreNode) x.node);
    printExpreListNode(x.next);
  }
  
  public void printUnaryNode(UnaryNode x) {
    if(x == null) return;

    
    System.out.println(x.number + ": UnaryNode ===> " + x.position.image +
        " " + x.expr.number);
    
    printExpreNode(x.expr);
  }
  
  public void printCallNode(CallNode x) {
    if(x == null) return;

    System.out.println(x.number + ": CallNode ===> " + x.expr.number + " " +
        x.meth.image + " " +
        ((x.args == null) ? "null" : String.valueOf(x.args.number)));
    printExpreNode(x.expr);
    printExpreListNode(x.args);
  }
  
  public void printIntConstNode(IntConstNode x) {
    if(x == null) return;

    System.out.println(x.number + ": IntConstNode ===> " + x.position.image);
  }
  
  public void printStringConstNode(StringConstNode x) {
    if(x == null) return;

    System.out.println(x.number + ": StringConstNode ===> " +
        x.position.image);
  }
  
  public void printNullConstNode(NullConstNode x) {
    if(x == null) return;

    System.out.println(x.number + ": NullConstNode ===> " + x.position.image);
  }
  
  public void printCharConstNode(CharConstNode x) {
    if(x == null) return;

    System.out.println(x.number + ": CharConstNode ===> " + x.position.image);
  }
  
  public void printFloatConstNode(FloatConstNode x) {
    if(x == null) return;
    
    System.out.println(x.number + ": FloatConstNode ===> " + x.position.image);
  }
  
  public void printBoolConstNode(BoolConstNode x) {
    if(x == null) return;

    System.out.println(x.number + ": BooleanConstNode ===> " + x.position.image);
  }
  
  public void printDotNode(DotNode x) {
    if(x == null) return;

    System.out.println(x.number + ": DotNode ===> " + x.expr.number + " " +
        x.field.image);
    printExpreNode(x.expr);
  }
}
