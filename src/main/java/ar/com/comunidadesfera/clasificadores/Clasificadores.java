package ar.com.comunidadesfera.clasificadores;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.Any;
import javax.enterprise.util.AnnotationLiteral;


public interface Clasificadores {

    Annotation Any = new AnnotationLiteral<Any>() {
        private static final long serialVersionUID = 1L;
    };
    
    Annotation Dinamica =  new AnnotationLiteral<Dinamica>() {
        private static final long serialVersionUID = 1L;
    };
    
    Annotation Basica = new AnnotationLiteral<Basica>() {
        private static final long serialVersionUID = 1L;
    };
}
