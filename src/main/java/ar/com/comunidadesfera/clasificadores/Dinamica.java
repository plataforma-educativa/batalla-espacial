package ar.com.comunidadesfera.clasificadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Clasificador para una implementación seleccionada dinámicamente de una interfaz.
 * 
 * Diseñada para clasificar métodos anotados como @Produces que seleccionan la implementación
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
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dinamica {

}
