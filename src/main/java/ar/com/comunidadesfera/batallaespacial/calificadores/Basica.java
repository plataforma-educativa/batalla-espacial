package ar.com.comunidadesfera.batallaespacial.calificadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Calificador para una implementación básica, por defecto, de una interfaz.
 * 
 * Aplicado a un clase indica que corresponde con una implementación
 * básica de una interfaz.
 * 
 * Aplicado a un método, un parámero o un atributo indica que debe inyectarse
 * la implementación así anotada.
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Basica {

}
