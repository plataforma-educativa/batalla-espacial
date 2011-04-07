package ar.com.comunidadesfera.eficiencia.excepciones;

import ar.com.comunidadesfera.eficiencia.Ejecucion;

public class EjecucionTerminadaException extends RuntimeException {

    private static final long serialVersionUID = 8035288138105401344L;

    private Ejecucion ejecucion;
    
    public EjecucionTerminadaException(Ejecucion ejecucion) {
        super("Ejecución previamente terminada: " + ejecucion);
        this.ejecucion = ejecucion;
    }

    public EjecucionTerminadaException(Ejecucion ejecucion, String mensaje) {
        super(mensaje + ".Ejecución previamente terminada: " + ejecucion);
        this.ejecucion = ejecucion;
    }
    
    public Ejecucion getEjecucion() {
        return ejecucion;
    }
    
}
