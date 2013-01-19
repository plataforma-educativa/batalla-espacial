package ar.com.comunidadesfera.batallaespacial.piezas;

public class PiezaNoVisitableException extends RuntimeException {

    private static final long serialVersionUID = -8820149212554015972L;

    public PiezaNoVisitableException() {

        super("La Pieza no acepta Visitantes");
    }
    
    @Override
    public String getMessage() {
        
        return super.getMessage() + ": " + this.getClass().getName();
    }
}
