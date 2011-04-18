package ar.com.comunidadesfera.persistencia;

public class NoExisteEntityManagerException extends RuntimeException {

    private static final long serialVersionUID = -867601163425386562L;

    public NoExisteEntityManagerException(String mensaje) {

        super(mensaje);
    }


}
