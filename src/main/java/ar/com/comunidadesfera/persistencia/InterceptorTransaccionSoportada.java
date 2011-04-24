package ar.com.comunidadesfera.persistencia;

import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;

/**
 * Implementación del Interceptor Transaccional para métodos que 
 * requieren ser ejecutados en una transacción, si existe una transacción
 * activa se suma, o de lo contrario inicia una nueva transacción.
 * 
 * @see InterceptorTransaccional
 * @author Mariano Tugnarelli
 *
 */
@Interceptor
@Transaccional(EstrategiaTransaccional.SOPORTADA)
public class InterceptorTransaccionSoportada extends InterceptorTransaccional {

    @Override
    protected EntityManager init(Escenario escenario) {

        EntityManager entityManager = this.contexto.buscarEntityManager();
        

        if (entityManager == null) {
            
            escenario.setIniciaSesion();
            entityManager = this.contexto.agregarEntityManager();
        }
        
        return entityManager;
    }

    @Override
    protected void onCatch(EntityManager em, Escenario escenario) {

        if (em.getTransaction().isActive()) {
            
            this.setRollbackOnly(em);
        }
    }
}
