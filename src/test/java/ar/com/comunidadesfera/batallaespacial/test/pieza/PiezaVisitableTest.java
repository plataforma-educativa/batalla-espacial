package ar.com.comunidadesfera.batallaespacial.test.pieza;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.PiezaNoVisitableException;
import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class PiezaVisitableTest {

    private VisitanteDePiezas visitante;
    
    @Before
    public void crearVisitante() {
        
        this.visitante = mock(VisitanteDePiezas.class);
    }
    
    @Test(expected = PiezaNoVisitableException.class)
    public void recibirEnPiezaNoVisitable() {
        
        Pieza pieza = mock(Pieza.class, Answers.CALLS_REAL_METHODS.get());
        
        pieza.recibir(visitante);
    }
    
    @Test
    public void visitarNave() {
        
        Nave nave = mock(Nave.class, Answers.CALLS_REAL_METHODS.get());
        
        nave.recibir(visitante);
        
        verify(visitante).visitar(nave);
    }
    
    @Test
    public void visitarBase() {
        
        BaseEspacial base = mock(BaseEspacial.class, Answers.CALLS_REAL_METHODS.get());

        base.recibir(visitante);
        
        verify(visitante).visitar(base);
    }
    
    @Test
    public void visitarAsteroide() {
        
        Asteroide asteroide = mock(Asteroide.class, Answers.CALLS_REAL_METHODS.get());
        
        asteroide.recibir(visitante);
        
        verify(visitante).visitar(asteroide);
    }
    
    @Test
    public void visitarContenedor() {
        
        Contenedor contenedor = mock(Contenedor.class, Answers.CALLS_REAL_METHODS.get());
        
        contenedor.recibir(visitante);
        
        verify(visitante).visitar(contenedor);
    }
    
}
