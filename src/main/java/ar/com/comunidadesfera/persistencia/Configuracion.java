package ar.com.comunidadesfera.persistencia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuracion {
    
    Tipo value();
    
    public enum Tipo {
        
        PERSISTENCE_UNIT_NAME,
        PERSISTENCE_UNIT_PROPERTIES
    }
}
