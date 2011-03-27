package ar.com.comunidadesfera.eficiencia.test;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.contextos.ContextoBasico;


public class ContextoPersistenteTest extends PersistenciaTest {

    protected Contexto contexto;
    
    @Before
    public void inicializarContexto() {
        
        ContextoBasico contextoBasico = new ContextoBasico(); 
        
        contextoBasico.setEntityManagerFactory(this.getEntityManagerFactory());
        
        this.contexto = contextoBasico;
    }
    
    @Test
    public void ejecucionPersistente() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(Datos.Ejecucion.SIMPLE_10.modulo.nombre, 
                                                             Datos.Ejecucion.SIMPLE_10.tamaño);
        
//        this.em.refresh(ejecucion);
        //  TODO

    }
}
