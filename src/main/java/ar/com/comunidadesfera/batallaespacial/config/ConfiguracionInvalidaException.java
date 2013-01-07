package ar.com.comunidadesfera.batallaespacial.config;

public class ConfiguracionInvalidaException extends Exception {

    private static final long serialVersionUID = 4654046702857536212L;

    public ConfiguracionInvalidaException(String message, Throwable cause) {

        super(message, cause);
    }

    public ConfiguracionInvalidaException(String message) {
        
        super(message);

    }
    
}
