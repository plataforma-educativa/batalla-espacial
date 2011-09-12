package ar.com.comunidadesfera.batallaespacial.calificadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Calificador para una implementación seleccionada dinámicamente de una interfaz.
 * 
 * Diseñada para calificar métodos anotados como @Produces que seleccionan la implementación
 * concreta dinámicamente. 
 * 
 * Aplicado a un método, un parámero o un atributo indica que debe inyectarse
 * una implementación seleccionada dinámicamente.
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dinamica {

}
