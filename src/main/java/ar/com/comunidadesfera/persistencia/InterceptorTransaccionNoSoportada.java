package ar.com.comunidadesfera.persistencia;

import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;

/**
 * Implementación del Interceptor Transaccional para métodos que 
 * no soportan ser ejecutados en una transacción.
 * 
 * @see InterceptorTransaccional
 * @author Mariano Tugnarelli
 *
 */
@Interceptor
@Transaccional(EstrategiaTransaccional.NO_SOPORTADA)
public class InterceptorTransaccionNoSoportada extends InterceptorTransaccional {

    @Override
    protected EntityManager init(Escenario escenario) {
        
        EntityManager entityManager = this.contexto.buscarEntityManager();
        
        /* sólo comienza una nueva sesión si no existe una activa o si
         * la existente ha iniciado una transacción, en cuyo caso debe
         * crear una nueva para aislarse de la transacción */
        if ((entityManager == null) || (entityManager.getTransaction().isActive())) {
            
            entityManager = this.contexto.agregarEntityManager();
            escenario.setIniciaSesion();
        
        } 

        return entityManager;
    }

}
