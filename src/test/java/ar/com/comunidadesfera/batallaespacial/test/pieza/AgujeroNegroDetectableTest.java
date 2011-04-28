package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.piezas.AgujeroNegro;
import ar.com.comunidadesfera.batallaespacial.test.juego.DetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ConfrontadorDeReporte;

public class AgujeroNegroDetectableTest extends DetectableTest<AgujeroNegro>{

    @Override
    protected AgujeroNegro instanciar() {

        return new AgujeroNegro();
    }

    @Override
    protected ConfrontadorDeReporte<AgujeroNegro> getConfrontador() {

        return super.getConfrontador()
                    .setEspectro(Matchers.is(Espectro.DESCONOCIDO))
                    .setCivilizacion(Matchers.nullValue())
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
    }
}
