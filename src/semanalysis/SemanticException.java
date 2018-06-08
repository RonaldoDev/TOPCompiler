package semanalysis;

public class SemanticException extends Exception{
	    /**
	     * importante caso a exce��o seja serializada
	     */
	    private static final long serialVersionUID = 1149241039409861914L;

	    // constr�i um objeto SemanticException com a mensagem passada por par�metro
	    public SemanticException(String msg){
	        super(msg);
	    }

	    // contr�i um objeto SemanticException com mensagem e a causa dessa exce��o, utilizado para encadear exceptions
	    public SemanticException(String msg, Throwable cause){
	        super(msg, cause);
	    }
}
