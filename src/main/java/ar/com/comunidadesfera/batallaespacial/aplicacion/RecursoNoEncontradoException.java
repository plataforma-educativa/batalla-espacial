package ar.com.comunidadesfera.batallaespacial.aplicacion;

public class RecursoNoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 5378558978751544939L;
    
    private static final String DETALLE = "Recurso no encontrado: ";

    public RecursoNoEncontradoException(String recurso, Throwable causa) {
        super(DETALLE + recurso, causa);
    
    }

    public RecursoNoEncontradoException(String recurso) {
        super(DETALLE + recurso);
    }

}
