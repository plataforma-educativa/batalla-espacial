package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_20;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;

@RunWith(JMock.class)
public class EjecucionObservadaTest extends TestBasico {
 
    private Mockery simulador = new JUnit4Mockery();
    
    private Ejecucion ejecucion;
    
    private Ejecucion.Observador observador;
    
    @Before
    public void crearEjecucion() {

        /* prepara el mock */
        this.observador = this.simulador.mock(Ejecucion.Observador.class);
        
        /* prepara el componente a probar */
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.tamaño);
        this.ejecucion.agregarObservador(this.observador);
    }
    
    @Test
    public void notificarEjecucionTerminada() {
   
        /* expectativas */
        this.simulador.checking(new Expectations(){{
            
            oneOf(observador).ejecucionTerminada(with(ejecucion)); 
            
        }});
        
        /* ejecución */
        this.ejecucion.getModulo();
        this.ejecucion.getProblema();        
        this.ejecucion.terminar();
    }
    
    @Test
    public void notificarInstrumentoDeMedicionCreado() {
        
        /* expectativas */
        this.simulador.checking(new Expectations() {{
            
            oneOf(observador).instrumentoDeMedicionCreado(with(ejecucion), 
                                                          with(any(InstrumentoDeMedicion.class)));
        }});
        
        /* ejecución */
        this.ejecucion.getModulo();
        this.ejecucion.contarInstrucciones().incrementar();
        this.ejecucion.contarInstrucciones().incrementar();
    }
    
    @Test
    public void notificarEnSecuencia() {
        
        /* expectativas */
        final Sequence notificaciones = this.simulador.sequence("notificaciones");
        this.simulador.checking(new Expectations(){{
            
            oneOf(observador).instrumentoDeMedicionCreado(with(ejecucion),
                                                          with(any(InstrumentoDeMedicion.class)));
            inSequence(notificaciones);
            
            oneOf(observador).ejecucionTerminada(with(ejecucion));
            inSequence(notificaciones);
            
        }});
        
        /* ejecución */
        this.ejecucion.getModulo();
        Contador contador = this.ejecucion.contarInstrucciones();
        contador.incrementar();
        contador.incrementar();
        this.ejecucion.getProblema();
        this.ejecucion.terminar();
    }
}
