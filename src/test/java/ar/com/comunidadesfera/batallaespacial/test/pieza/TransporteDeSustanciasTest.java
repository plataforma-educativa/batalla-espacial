package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

@RunWith(Theories.class)
public class TransporteDeSustanciasTest {

    private TransporteDeSustancias transporte;
    
    @DataPoints
    public static final Sustancia[] SUSTANCIAS = Sustancia.values();
    
    @DataPoints
    public static final TransporteDeSustancias[] TRANSPORTES;

    static {
        
        TRANSPORTES = new TransporteDeSustancias[] {        
                                new Contenedor(10, 100L),
                                new Contenedor(10, 1L),
                                new Contenedor(10, 0L),
                                new Contenedor(10, 15L),
                                new Contenedor(10, 50L),
                                new Contenedor(10, 1500L),
                                new Contenedor(10, Long.MAX_VALUE - 1),
                                new NaveDeCombate(10),
                                new BaseEspacial(10),
        };
    }
    
    public TransporteDeSustanciasTest(TransporteDeSustancias transporte) {

        /*
         * el transporte es clonado para garantizar que no se utiliza el mismo
         * parámetro en la ejecución en múltiples casos de prueba
         */
        this.transporte = transporte.clone();
    }

    @Test
    @Theory
    public void agregarSustancias(Sustancia sustancia) {
        
        long cantidadInicial = transporte.getCantidad(sustancia);
        long capacidad = transporte.getCapacidadDisponible(sustancia);
        
        transporte.agregarSustancia(sustancia, 0L);
        Assert.assertThat("cantidad", transporte.getCantidad(sustancia),
                          Matchers.equalTo(cantidadInicial));
        
        long delta = capacidad / 2L;
        transporte.agregarSustancia(sustancia, delta);
        Assert.assertThat("cantidad", transporte.getCantidad(sustancia), 
                          Matchers.equalTo(cantidadInicial + delta));
        Assert.assertThat("capacidadDisponible", 
                          transporte.getCapacidadDisponible(sustancia), 
                          Matchers.equalTo(capacidad - delta));

        transporte.agregarSustancia(sustancia,
                                    transporte.getCapacidadDisponible(sustancia));
        Assert.assertThat("cantidad", 
                          transporte.getCantidad(sustancia), 
                          Matchers.equalTo(cantidadInicial + capacidad));
        Assert.assertThat("capacidadDisponible", 
                          transporte.getCapacidadDisponible(sustancia), 
                          Matchers.equalTo(0L));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void agregarSustanciasEnExceso(Sustancia sustancia) {
        
        long capacidad = transporte.getCapacidadDisponible(sustancia);
        transporte.agregarSustancia(sustancia, capacidad + 1L);
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void agregarSustanciasNegativas(Sustancia sustancia) {
        
        transporte.agregarSustancia(sustancia, -100L);
    }

    @Test
    @Theory
    public void extraerSustancias(Sustancia sustancia) {
        
        long capacidadInicial = transporte.getCapacidadDisponible(sustancia);
        long cantidad = capacidadInicial / 2L;
        
        transporte.agregarSustancia(sustancia, cantidad);
        
        long capacidad = transporte.getCapacidadDisponible(sustancia);
        
        transporte.extraerSustancia(sustancia, 0L);
        Assert.assertThat("cantidad", 
                          transporte.getCantidad(sustancia), 
                          Matchers.equalTo(cantidad));
        
        long delta = capacidad / 2L;
        
        transporte.extraerSustancia(sustancia, delta);
        
        Assert.assertThat("cantidad", 
                          transporte.getCantidad(sustancia), 
                          Matchers.equalTo(cantidad - delta));
        
        Assert.assertThat("capacidadDisponible", 
                          transporte.getCapacidadDisponible(sustancia), 
                          Matchers.equalTo(capacidad + delta));
        
        transporte.extraerSustancia(sustancia, 
                                    transporte.getCantidad(sustancia));
        
        Assert.assertThat("cantidad", 
                          transporte.getCantidad(sustancia), 
                          Matchers.equalTo(0L));
        
        Assert.assertThat("capacidadDisponible", 
                          transporte.getCapacidadDisponible(sustancia), 
                          Matchers.equalTo(cantidad + capacidad));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void extraerSustanciasEnExceso(Sustancia sustancia) {
        
        long cantidadInicial = transporte.getCantidad(sustancia);
        transporte.extraerSustancia(sustancia, cantidadInicial + 1L);
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void extraerSustanciasNegativas(Sustancia sustancia) {
        
        long cantidadInicial = transporte.getCapacidadDisponible(sustancia) / 2L;
        transporte.agregarSustancia(sustancia, cantidadInicial);
        transporte.extraerSustancia(sustancia, -100L);
    }
}        