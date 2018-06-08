package semanalysis;

public class SemanticException extends Exception{
	    /**
	     * importante caso a exceção seja serializada
	     */
	    private static final long serialVersionUID = 1149241039409861914L;

	    // constrói um objeto SemanticException com a mensagem passada por parâmetro
	    public SemanticException(String msg){
	        super(msg);
	    }

	    // contrói um objeto SemanticException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
	    public SemanticException(String msg, Throwable cause){
	        super(msg, cause);
	    }
}
