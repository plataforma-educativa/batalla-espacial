package ar.com.comunidadesfera.persistencia.itest.transacciones;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.persistencia.Transaccional;

public class AdministradorSimulado {
    
    private EntityManager em;

    @Transaccional
    public void guardar(Modulo  modulo) {
    
        this.em.persist(modulo);
    }
    
    @Inject
    public void setEntityManager(EntityManager em) {
        
        this.em = em;
    }
    
    protected EntityManager getEntityManager() {
        
        return this.em;
    }
}
