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
@Transaccional(estrategia = EstrategiaTransaccional.NO_SOPORTADA)
public class InterceptorTransaccionNoSoportada extends InterceptorTransaccional {

    @Override
    protected EntityManager init(Escenario escenario) {
        
        escenario.setIniciaSesion();
        return this.contexto.agregarEntityManager();
    }

}
