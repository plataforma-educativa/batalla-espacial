package ar.com.comunidadesfera.batallaespacial.test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionBasica;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

public abstract class BatallaEspacialTest {

    private BatallaEspacial batallaEspacial;
    
    protected abstract BatallaEspacial crear();
    
    @Before
    public void inializar() {
        
        this.batallaEspacial = this.crear();
    }
    
    @Test
    public void observarIniciada() {
        
        BatallaEspacial.Observador observador = mock(BatallaEspacial.Observador.class);
        
        this.batallaEspacial.agregarObservador(observador);
        
        this.batallaEspacial.iniciar();
        
        verify(observador).iniciada(this.batallaEspacial);
        verifyNoMoreInteractions(observador);
    }
    
    @Test
    public void jugar() {
        
        Configuracion configuracion = this.crearConfiguracion(2 , 0, new Tablero(1, 1));
        
        Partida partida = this.batallaEspacial.jugar(configuracion);
        
        assertThat("partida a jugar", partida, notNullValue());
        assertThat("configuracion de la partida", partida.getConfiguracion(), is(configuracion));
    }
    
    @Test
    public void observarJugando() {
        
        BatallaEspacial.Observador observador = mock(BatallaEspacial.Observador.class);
        
        this.batallaEspacial.agregarObservador(observador);
        
        Configuracion configuracion = this.crearConfiguracion(2, 0, new Tablero(1, 1));
        
        Partida partida = this.batallaEspacial.jugar(configuracion);
        
        verify(observador).jugando(this.batallaEspacial, partida);
    }
    
    protected Configuracion crearConfiguracion(int rondas, long timeout, Tablero tablero) {
        
        ConfiguracionBasica configuracion = new ConfiguracionBasica();
        configuracion.setRondas(rondas);
        configuracion.setTimeout(timeout);
        configuracion.setTablero(tablero);
        
        return configuracion;
    }
}
