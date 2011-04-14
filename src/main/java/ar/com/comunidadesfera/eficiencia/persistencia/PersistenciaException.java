package ar.com.comunidadesfera.eficiencia.persistencia;

public class PersistenciaException extends Exception {

    private static final long serialVersionUID = -6340808057289108729L;

    public PersistenciaException() {
        super();
    }

    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenciaException(String message) {
        super(message);
    }

    public PersistenciaException(Throwable cause) {
        super(cause);
    }

    
}
