package parser;

public class ParseEOFException extends Exception{
	    /**
	     * importante caso a exce��o seja serializada
	     */
	    private static final long serialVersionUID = 1149241039409861914L;

	    // constr�i um objeto ParseEOFException com a mensagem passada por par�metro
	    public ParseEOFException(String msg){
	        super(msg);
	    }

	    // contr�i um objeto ParseEOFException com mensagem e a causa dessa exce��o, utilizado para encadear exceptions
	    public ParseEOFException(String msg, Throwable cause){
	        super(msg, cause);
	    }
}
