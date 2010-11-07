package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.junit.Assert;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza.Observador;

public class ObservadorSimulado implements Observador {

    private Pieza pieza;

    private int cambios;
    
    private boolean observando;
    
    public ObservadorSimulado() {
        
        this.cambios = 0;
        this.pieza = null;
        this.iniciar();
    }
    
    public void cambio(Pieza pieza) {

        if (! this.observando) {
            
            Assert.fail("El observador no está observando");
        }
        
        if (this.pieza != null && ! this.pieza.equals(pieza)) {
            
            Assert.fail("Piezas diferentes notificando cambios");
            
        }
        
        this.pieza = pieza;
        this.cambios++;
    }
    
    public int getCambios() {
        
        return this.cambios;
    }
    
    public Pieza getPieza() {
        
        return this.pieza;
    }
    
    public void detener() {
        
        this.observando = false;
    }
    
    public void iniciar() {
        
        this.observando = true;
    }
}
