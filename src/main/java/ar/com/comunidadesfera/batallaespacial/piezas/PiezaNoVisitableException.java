package ar.com.comunidadesfera.batallaespacial.piezas;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class PiezaNoVisitableException extends RuntimeException {

    private static final long serialVersionUID = -8820149212554015972L;

    private Pieza pieza;
    
    public PiezaNoVisitableException(Pieza pieza) {

        super("La Pieza no acepta Visitantes" + pieza + "[" + pieza.getClass().getName() + "]");
        this.pieza = pieza;
    }
    
    public Pieza getPiezaVisitada() {
        return this.pieza;
    }
}
