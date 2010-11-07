package ar.com.comunidadesfera.batallaespacial.test.pieza.base;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.base.PistaDeAterrizaje;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

@RunWith(Theories.class)
public class PistaDeAterrizajeTest {

    private PistaDeAterrizaje pista;

    private List<Nave> naves;

    @DataPoints
    public static final PistaDeAterrizaje[] PISTAS;

    static {

        PISTAS = new PistaDeAterrizaje[] { new BaseEspacial(10), };
    }

    public PistaDeAterrizajeTest(PistaDeAterrizaje pista) {

        /*
         * la pista es clonada para garantizar que no se utiliza el mismo
         * parámetro en la ejecución de distintas pruebas
         */
        this.pista = pista.clone();
    }

    @Before
    public void crearNaves() {

        naves = new ArrayList<Nave>();
        naves.add(new NaveDeCombate(10));
        naves.add(new NaveDeCombate(10));
        naves.add(new NaveDeCombate(10));
        naves.add(new NaveDeCombate(10));
        naves.add(new NaveDeCombate(10));
    }

    @Test
    public void agregarNave() {
        
        Assert.assertThat(true, Matchers.equalTo(pista.getNaves().isEmpty()));
        
        pista.agregarNave(naves.get(0));
        pista.agregarNave(naves.get(1));
        pista.agregarNave(naves.get(2));
        
        Assert.assertThat("naves", pista.getNaves(), 
                          Matchers.hasItems(naves.get(0), 
                                            naves.get(1), 
                                            naves.get(2)));
        
        pista.agregarNave((Nave)naves.get(3));
        
        Assert.assertThat("naves", pista.getNaves(), 
                          Matchers.hasItems(naves.get(0), 
                                            naves.get(1), 
                                            naves.get(2), 
                                            naves.get(3)));
    }
}