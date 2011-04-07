package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_10;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.contextos.ContextoBasico;


public class ContextoPersistenteTest extends TestDePersistencia {

    protected Contexto contexto;
    
    @Before
    public void inicializarContexto() {
        
        ContextoBasico contextoBasico = new ContextoBasico(); 
        
        contextoBasico.setEntityManagerFactory(this.getEntityManagerFactory());
        
        this.contexto = contextoBasico;
    }
    
    @Test
    public void ejecucionPersistente() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                             SIMPLE_10.tamaño);
        
//        this.em.refresh(ejecucion);
        //  TODO
        
        ejecucion.terminar();
    }
}
