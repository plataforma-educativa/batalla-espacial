package ar.com.comunidadesfera.batallaespacial.test.pieza.asteroide;

import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClon;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClonPieza;
import ar.com.comunidadesfera.batallaespacial.test.pieza.EscenarioSimulado;
import ar.com.comunidadesfera.batallaespacial.test.pieza.PiezaTest;

public class AsteroideTest extends PiezaTest<Asteroide> {

    @Override
    protected int cambiarParaObservar(Asteroide asteroide) {

        asteroide.modificarPuntos(10, new EscenarioSimulado());
        
        return 1;
    }

    @Override
    protected Asteroide instanciar() {

        return new Asteroide(100);
    }

    @Override
    protected ComprobarClon<Asteroide> comprobarClon() {

        return new ComprobarClonPieza<Asteroide>("Asteroide");
    }
    
}