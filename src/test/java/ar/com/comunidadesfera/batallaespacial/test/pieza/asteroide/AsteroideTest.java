package ar.com.comunidadesfera.batallaespacial.test.pieza.asteroide;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ReporteDePieza;
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
    
    @Override
    protected ReporteDePieza<Asteroide> reporteDePieza() {

        return super.reporteDePieza()
                    .setEspectro(Matchers.is(Espectro.ASTEROIDE))
                    .setCivilizacion(Matchers.nullValue())
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
    }
}