package parser;

public class ParseEOFException extends Exception{
	    /**
	     * importante caso a exceção seja serializada
	     */
	    private static final long serialVersionUID = 1149241039409861914L;

	    // constrói um objeto ParseEOFException com a mensagem passada por parâmetro
	    public ParseEOFException(String msg){
	        super(msg);
	    }

	    // contrói um objeto ParseEOFException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
	    public ParseEOFException(String msg, Throwable cause){
	        super(msg, cause);
	    }
}
