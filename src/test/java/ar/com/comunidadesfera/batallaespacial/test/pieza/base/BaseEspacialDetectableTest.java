package ar.com.comunidadesfera.batallaespacial.test.pieza.base;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.test.juego.DetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ConfrontadorDeReporte;

public class BaseEspacialDetectableTest extends DetectableTest<BaseEspacial> {

    @Override
    protected BaseEspacial instanciar() {

        return new BaseEspacial(200);
    }
    
    @Override
    protected ConfrontadorDeReporte<BaseEspacial> getConfrontador() {

        return super.getConfrontador()
                    .setEspectro(Matchers.is(Espectro.BASE))
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
    }

}
