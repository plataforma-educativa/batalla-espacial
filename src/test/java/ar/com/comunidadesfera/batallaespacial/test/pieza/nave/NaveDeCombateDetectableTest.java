package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada.CivilizacionSimulada;
import ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada.PilotoSimulado;
import ar.com.comunidadesfera.batallaespacial.test.juego.DetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ConfrontadorDeReporte;

public class NaveDeCombateDetectableTest extends DetectableTest<NaveDeCombate> {

    @Override
    protected NaveDeCombate instanciar() {

        NaveDeCombate nave = new NaveDeCombate(100);
        
        CivilizacionSimulada civilizacion = new CivilizacionSimulada();
        PilotoSimulado piloto = new PilotoSimulado(civilizacion);
        nave.setPiloto(piloto);
        
        return nave;
    }

    @Override
    protected ConfrontadorDeReporte<NaveDeCombate> getConfrontador() {

        ConfrontadorDeReporte<NaveDeCombate> confrontador = super.getConfrontador();
        
        confrontador.setEspectro(Matchers.is(Espectro.NAVE))
                    .setCivilizacion(Matchers.is(confrontador.getDetectable().getCivilizacion()))
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
        
        return confrontador;
    }
}

