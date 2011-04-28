package ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.test.juego.DetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ConfrontadorDeReporte;

public class ContenedorDetectableTest extends DetectableTest<Contenedor> {

    @Override
    protected Contenedor instanciar() {

        return new Contenedor(100, 500);
    }

    @Override
    protected ConfrontadorDeReporte<Contenedor> getConfrontador() {

        return super.getConfrontador()
                    .setEspectro(Matchers.is(Espectro.CONTENEDOR))
                    .setCivilizacion(Matchers.nullValue())
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
    }
}
