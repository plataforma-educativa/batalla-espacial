package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClon;
import ar.com.comunidadesfera.batallaespacial.test.pieza.PiezaTest;

public abstract class NaveTest<T extends Nave> extends PiezaTest<T> {

    @Override
    protected void comprobarEstadoInicial(T nave) {
     
        super.comprobarEstadoInicial(nave);
        
        assertThat("Pista", nave.getPista(), nullValue());
        assertThat("Nivel de escudos", nave.getNivelDeEscudos(), 
                   equalTo((byte) 100));
    }

    @Override
    protected ComprobarClon<T> comprobarClon() {

        return new ComprobarClonNave<T>("nave");
    }
}
