package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.batallaespacial.Monitor;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

@RunWith(Theories.class)
public class  MonitorNaveDeCombateTest {

    @DataPoints
    public static final Sustancia[] sustancias = Sustancia.values();
    
    @DataPoints
    public static final int[] cantidadesDeTorpedos = new int[] { 1, 5, 10, 15 };
    
    @DataPoints
    public static final long[] cantidadesDeSustancias = new long[] { 10, 20, 25, 50 };
    
    /**
     * Nave de Combate monitoreada.
     */
    private NaveDeCombate nave;
    
    /**
     * Monitor de la nave a probar.
     */
    private Monitor monitor;
    
    @Before
    public void inicializar() {
        
        this.nave = new NaveDeCombate(100);
        this.monitor = nave.getMonitor();
    }
    
    @Test
    public void getNivelEscudos() {
        
        Assert.assertThat("nivel de escudos", 
                          this.monitor.getNivelDeEscudos(), 
                          Matchers.equalTo(this.nave.getNivelDeEscudos()));
        
    }
    
    @Test
    @Theory
    public void getCantidadDeTorpedos(int cantidad) {
     
        this.nave.setCantidadDeTorpedos(cantidad);
        
        Assert.assertThat("cantidad de torpedos", 
                          this.monitor.getCantidadDeTorpedos(),
                          Matchers.equalTo(this.nave.getCantidadDeTorpedos()));
        
    }
    
    @Test
    @Theory
    public void getCarga(Sustancia sustancia, long cantidad) {
        
        this.comprobarCarga(sustancia);
        
        this.nave.getBodega().agregarSustancia(sustancia, cantidad);
        
        this.comprobarCarga(sustancia);
        
        this.nave.getBodega().agregarSustancia(sustancia, cantidad);
        
        this.comprobarCarga(sustancia);
        
        this.nave.getBodega().extraerSustancia(sustancia, cantidad);
        
        this.comprobarCarga(sustancia);
        
        this.nave.getBodega().extraerSustancia(sustancia, cantidad);
        
    }

    private void comprobarCarga(Sustancia sustancia) {
        
        Assert.assertThat("carga " + sustancia,
                          monitor.getCarga(sustancia),
                          Matchers.equalTo(nave.getBodega().getCantidad(sustancia)));
    }
    
    @Test
    @Theory
    public void getNivelDeCarga(Sustancia sustancia, long cantidad) {
        
        this.comprobarNivelDeCarga();
        
        this.nave.getBodega().agregarSustancia(sustancia, cantidad);
        
        this.comprobarNivelDeCarga();
        
        this.nave.getBodega().agregarSustancia(sustancia, cantidad);
        
        this.comprobarNivelDeCarga();
        
        this.nave.getBodega().extraerSustancia(sustancia, cantidad);
        
        this.comprobarNivelDeCarga();
        
        this.nave.getBodega().extraerSustancia(sustancia, cantidad);
        
        this.comprobarNivelDeCarga();
    }
    
    private void comprobarNivelDeCarga() {
        
        Assert.assertThat("nivel de carga", 
                          monitor.getNivelDeCarga(),
                          Matchers.equalTo(nave.getBodega().getNivelDeCarga()));
    }
    
}
