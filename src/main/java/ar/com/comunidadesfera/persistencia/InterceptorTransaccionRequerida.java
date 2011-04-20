package ar.com.comunidadesfera.persistencia;

import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;

/**
 * Implementación del Interceptor Transaccional para métodos que 
 * requieren ser ejecutados en una nueva transacción.
 * 
 * @see InterceptorTransaccional
 * @author Mariano Tugnarelli
 *
 */
@Interceptor
@Transaccional(estrategia = EstrategiaTransaccional.REQUERIDA)
public class InterceptorTransaccionRequerida extends InterceptorTransaccional {


    @Override
    protected EntityManager init(Escenario escenario) {

        EntityManager entityManager = this.contexto.buscarEntityManager();
        
        escenario.setSesionPropagada(entityManager != null);

        if (! escenario.isSesionPropagada()) {
            
            entityManager = this.contexto.agregarEntityManager();
        }
        
        return entityManager;
    }

    @Override
    protected void beginTry(EntityManager em, Escenario escenario) {
        
        if (! escenario.isSesionPropagada()) {
            
            this.begin(em);
        }
    }

    @Override
    protected void endTry(EntityManager em, Escenario escenario) {
        
        if (! escenario.isSesionPropagada()) {
            
            this.commit(em);
        }
    }

    @Override
    protected void onCatch(EntityManager em, Escenario escenario) {

        if (escenario.isSesionPropagada()) {
            
            this.setRollbackOnly(em);
            
        } else {
            
            this.rollback(em);
        }
    }

}
