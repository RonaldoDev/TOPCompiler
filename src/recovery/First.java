package recovery;

import java.util.*;
import parser.FunConstants;

//implementa alguns conjuntos first para alguns não terminais
public class First {

	static public final RecoverySet methoddecl = new RecoverySet();
	static public final RecoverySet vardecl = new RecoverySet();
	static public final RecoverySet classlist = new RecoverySet();
	static public final RecoverySet constructdecl = new RecoverySet();
	static public final RecoverySet statlist = new RecoverySet();
	static public final RecoverySet program = new RecoverySet();
	
	static {
		methoddecl.add(new Integer(parser.FunConstants.INT));
		methoddecl.add(new Integer(parser.FunConstants.STRING));
		methoddecl.add(new Integer(parser.FunConstants.tipo_Identificador));
		
		vardecl.add(new Integer(parser.FunConstants.INT));
		vardecl.add(new Integer(parser.FunConstants.STRING));
		vardecl.add(new Integer(parser.FunConstants.tipo_Identificador));
		
		classlist.add(new Integer(parser.FunConstants.CLASS));
		
		constructdecl.add(new Integer(parser.FunConstants.CONSTRUCTOR));
		
		statlist.addAll(vardecl);
		statlist.add(new Integer(parser.FunConstants.tipo_Identificador));
		statlist.add(new Integer(parser.FunConstants.PRINT));
		statlist.add(new Integer(parser.FunConstants.READ));
		statlist.add(new Integer(parser.FunConstants.RETURN));
		statlist.add(new Integer(parser.FunConstants.SUPER));
		statlist.add(new Integer(parser.FunConstants.IF));
		statlist.add(new Integer(parser.FunConstants.FOR));
		statlist.add(new Integer(parser.FunConstants.LBRACE));
		statlist.add(new Integer(parser.FunConstants.BREAK));
		statlist.add(new Integer(parser.FunConstants.SEMICOLON));
		
	}
	
	
}
