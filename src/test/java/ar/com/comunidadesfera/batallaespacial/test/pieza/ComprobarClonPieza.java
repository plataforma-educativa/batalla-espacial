package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class ComprobarClonPieza<E extends Pieza> extends ComprobarClonBasico<E> {
    
    public ComprobarClonPieza(String descripcion) {
        super(descripcion);
    }

    public void ejecutar(E pieza, E clon) {

        this.procesar("puntos",clon.getPuntos(), 
                      Matchers.equalTo(pieza.getPuntos()));
        this.procesar("casillero", clon.getCasillero(), 
                      Matchers.equalTo(pieza.getCasillero()));
    }
}
