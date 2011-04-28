package ar.com.comunidadesfera.batallaespacial.test.pieza;

import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;
import ar.com.comunidadesfera.batallaespacial.piezas.AgujeroNegro;

public class AgujeroNegroTest extends PiezaTest<AgujeroNegro> {

    @Override
    protected AgujeroNegro instanciar() {

        return new AgujeroNegro();
    }

    @Override
    protected int cambiarParaObservar(AgujeroNegro pieza) {
        
        pieza.modificarPuntos(100, new EscenarioSimulado());
        pieza.modificarPuntos(5, new EscenarioSimulado());
        
        return 0;
    }

    /**
     * Redefinido porque la Pieza Agujero Negro nunca sufre modificaciones.
     */
    @Override
    protected int getPuntosEsperados(int iniciales, int delta, Escenario escenario) {
   
        return iniciales;
    }

}
