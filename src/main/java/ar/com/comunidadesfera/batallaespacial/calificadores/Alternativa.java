package ar.com.comunidadesfera.batallaespacial.calificadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Calificador para una implementación alternativa de una interfaz.
 * 
 * Aplicado a un clase indica que corresponde con una implementación
 * alternativa (no básica) de una interfaz.
 * 
 * @author Mariano Tugnarelli
 *
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Alternativa {

}
