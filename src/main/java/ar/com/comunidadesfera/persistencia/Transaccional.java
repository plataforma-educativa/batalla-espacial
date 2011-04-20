package ar.com.comunidadesfera.persistencia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Define un método/clase (todos los métodos de una clase) como transaccionales.
 * Un método transaccional se ejecuta en el contexto de una trasacción JPA. Si
 * el método finaliza normalmente se hace commit de la transacción. Si el 
 * método lanza una excepción se hace rollback de la transacción.
 * 
 * @see http://download.oracle.com/javaee/5/api/javax/ejb/TransactionAttribute.html
 * @author Mariano Tugnarelli
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaccional {

    /**
     * Define la estrategia para manipular el contexto transaccional.
     */
    EstrategiaTransaccional estrategia() default EstrategiaTransaccional.REQUERIDA;
    
}
