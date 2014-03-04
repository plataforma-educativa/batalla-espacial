package ar.com.comunidadesfera.batallaespacial.piezas;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public interface VisitanteDePiezas {

    void visitar(Pieza pieza);
    
    void visitar(Nave nave);

    void visitar(BaseEspacial base);

    void visitar(Asteroide asteroide);

    void visitar(Contenedor contenedor);

}
