package ar.com.comunidadesfera.persistencia.itest.transacciones;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.After;

import ar.com.comunidadesfera.persistencia.ContextoDePersistencia;

/**
 * Test de Integración (IT, Integration Test) básico.
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class ITBasico {

    @Inject
    private ContextoDePersistencia contexto;
    
    private EntityManager em = null;

    protected EntityManager getEntityManager() {
        
        if (this.em == null) {
            
            this.em = this.contexto.getEntityManagerFactory().createEntityManager();
        }
        
        return this.em;
    }
    
    @After
    public void closeEntityManager() {
        
        if (this.em != null) {
        
            this.em.close();
        }
    }
}
