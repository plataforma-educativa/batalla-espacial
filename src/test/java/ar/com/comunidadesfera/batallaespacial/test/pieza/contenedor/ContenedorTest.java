package ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ReporteDePieza;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClon;
import ar.com.comunidadesfera.batallaespacial.test.pieza.PiezaTest;

@RunWith(Theories.class)
public class ContenedorTest extends PiezaTest<Contenedor> {

    @DataPoints
    public static final Sustancia[] sustancias = Sustancia.values();

    @Override
    protected int cambiarParaObservar(Contenedor contenedor) {

        contenedor.agregarSustancia(Sustancia.ANTIMATERIA, 10);
        contenedor.agregarSustancia(Sustancia.ANTIMATERIA, 20);
        contenedor.extraerSustancia(Sustancia.ANTIMATERIA, 5);
        
        return 3;
    }

    @Override
    protected Contenedor instanciar() {

        return new Contenedor(100, 1000);
    }

    @Override
    protected ComprobarClon<Contenedor> comprobarClon() {

        return new ComprobarClonContenedor<Contenedor>("contenedor");
    }
    
    @Test
    @Theory
    public void getNivelDeCarga(Sustancia sustancia) {
        
        Contenedor contenedor = this.getPieza();
        
        long capacidad = contenedor.getCapacidadDisponible(sustancia);
        
        Assert.assertThat("nivelDeCarga", contenedor.getNivelDeCarga(),
                          Matchers.equalTo((byte) 0));
        
        long cantidad = capacidad / 2;
        
        contenedor.agregarSustancia(sustancia, cantidad);
        Assert.assertThat("nivelDeCarga", contenedor.getNivelDeCarga(),
                          Matchers.equalTo((byte) 50));
        
        contenedor.agregarSustancia(sustancia, contenedor.getCapacidadDisponible(sustancia));
        Assert.assertThat("nivelDeCarga", contenedor.getNivelDeCarga(),
                          Matchers.equalTo((byte) 100));
    }
    
    @Override
    protected ReporteDePieza<Contenedor> reporteDePieza() {
    
        // TODO verificar cantidad de sustancia con valores
        
        return super.reporteDePieza()
                    .setEspectro(Matchers.is(Espectro.CONTENEDOR))
                    .setCivilizacion(Matchers.nullValue())
                    .setCantidadDeSustancia(Matchers.is(0L), Sustancia.values());
        
    }
    
}