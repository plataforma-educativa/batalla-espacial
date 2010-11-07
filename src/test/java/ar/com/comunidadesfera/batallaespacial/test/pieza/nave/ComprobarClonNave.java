package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClonPieza;

public class ComprobarClonNave<T extends Nave> extends ComprobarClonPieza<T> {

    public ComprobarClonNave(String descripcion) {
        super(descripcion);
    }

    public void ejecutar(T pieza, T clon) {

        super.ejecutar(pieza, clon);

        this.procesar("pista", clon.getPista(), 
                      Matchers.equalTo(pieza.getPista()));
        this.procesar("nivel de escudos", clon.getNivelDeEscudos(), 
                      Matchers.equalTo(pieza.getNivelDeEscudos()));

    }
}
