package ar.com.comunidadesfera.batallaespacial.test.interfaz;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Answers;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionBasica;
import ar.com.comunidadesfera.batallaespacial.interfaz.DibujanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

@RunWith(Theories.class)
public class DibujanteDePiezasTest {

    @DataPoint
    public static final Class<Pieza> PIEZA_NO_VISITABLE = Pieza.class; 
    
    @DataPoint
    public static final Class<Asteroide> ASTEROIDE = Asteroide.class; 

    @DataPoint
    public static final Class<Nave> NAVE = Nave.class; 

    @DataPoint
    public static final Class<BaseEspacial> BASE = BaseEspacial.class;
    
    private DibujanteDePiezas dibujante;

    private Configuracion configuracion;
    
    @Before
    public void crear() {
        
        this.configuracion = spy(new ConfiguracionBasica());
        
        Participante participante = mock(Participante.class);
        Civilizacion civilizacion = anyObject();

        when(this.configuracion.getParticipante(civilizacion))
            .thenReturn(participante);mock(Pieza.class, Answers.CALLS_REAL_METHODS.get()); 
        when(participante.getPintura())
            .thenReturn(Color.GREEN);
        
        
        this.dibujante = new DibujanteDePiezas();
        this.dibujante.setConfiguracion(this.configuracion);
    }
    
    @Theory
    public void dibujar(Class<? extends Pieza> clasePieza) {
        
        Pieza pieza = mock(clasePieza, Answers.CALLS_REAL_METHODS.get());

        assertThat("dibujo", this.dibujante.dibujar(pieza), notNullValue());
        
        verify(pieza).recibir(this.dibujante);
    }
}
