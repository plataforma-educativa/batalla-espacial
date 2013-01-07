package ar.com.comunidadesfera.batallaespacial.test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
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
        
        Configuracion<Participante> configuracion = new Configuracion<Participante>();
        configuracion.setRondas(2);
        configuracion.setTimeout(0);
        configuracion.setTablero(new Tablero(1, 1));
        
        Partida<Participante> partida = this.batallaEspacial.jugar(configuracion);
        
        assertThat("partida a jugar", partida, notNullValue());
        assertThat("configuracion de la partida", partida.getConfiguracion(), is(configuracion));
    }
    
    @Test
    public void observarJugando() {
        
        BatallaEspacial.Observador observador = mock(BatallaEspacial.Observador.class);
        
        this.batallaEspacial.agregarObservador(observador);
        
        Configuracion<Participante> configuracion = new Configuracion<Participante>();
        configuracion.setRondas(2);
        configuracion.setTimeout(0);
        configuracion.setTablero(new Tablero(1, 1));
        
        Partida<Participante> partida = this.batallaEspacial.jugar(configuracion);
        
        verify(observador).jugando(this.batallaEspacial, partida);
    }
}
