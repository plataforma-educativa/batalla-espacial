package ar.com.comunidadesfera.batallaespacial.test.pieza.asteroide;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.test.juego.DetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ConfrontadorDeReporte;

public class AsteroideDetectableTest extends DetectableTest<Asteroide> {

    @Override
    protected Asteroide instanciar() {

        return new Asteroide(100);
    }
    
    @Override
    protected ConfrontadorDeReporte<Asteroide> getConfrontador() {

        return super.getConfrontador()
                    .setEspectro(Matchers.is(Espectro.ASTEROIDE))
                    .setCivilizacion(Matchers.nullValue())
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
    }

}
