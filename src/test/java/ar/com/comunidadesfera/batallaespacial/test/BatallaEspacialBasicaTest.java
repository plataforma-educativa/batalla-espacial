package ar.com.comunidadesfera.batallaespacial.test;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.galaxias.BatallaEspacialBasica;

public class BatallaEspacialBasicaTest extends BatallaEspacialTest {

    @Override
    protected BatallaEspacial crear() {
        
        return new BatallaEspacialBasica() {};
    }
}
