package ar.com.comunidadesfera.dependencias;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Provider;

import ar.com.comunidadesfera.batallaespacial.calificadores.Basica;
import ar.com.comunidadesfera.batallaespacial.calificadores.Dinamica;

/**
 * Proveedor de implementaciones para la interfaz T. 
 * Expone las Alternativas disponibles para la interfaz T.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public class Proveedor<T> {

    private final static Annotation[] CUALIFICADORES_POR_DEFECTO = {

        new AnnotationLiteral<Basica>(){
            private static final long serialVersionUID = 1L;
        }
    };
    
    private final static Annotation[] CUALIFICADORES_DISPONIBLES = {
        
        new AnnotationLiteral<Any>() {
            private static final long serialVersionUID = 1L;
        }
    };    

    private final static Annotation CUALIFICADOR_DINAMICO = new AnnotationLiteral<Dinamica>() {
        
        private static final long serialVersionUID = 1L;
    };
    
    private final Class<T> interfaz;
    
    private final Instance<T> instance;
    
    private final BeanManager beanManager;

    private Provider<T> provider;
    
    Proveedor(BeanManager beanManager, Class<T> interfaz, Instance<T> instance) {
     
        this.interfaz = interfaz;
        this.instance = instance;
        this.beanManager = beanManager;
        
        this.seleccionar();
    }
    
    private void seleccionar() {
        
        if (! this.instance.isUnsatisfied()) {

            if (! this.instance.isAmbiguous()) {
                
                this.provider = this.instance;
                
            } else {
                
                Instance<T> defaultIntance = this.instance.select(CUALIFICADORES_POR_DEFECTO); 
                
                if (defaultIntance.isAmbiguous() || defaultIntance.isUnsatisfied()) {
                    
                    throw new IllegalStateException("No se encontró implementacion @Basica en: " +
                                                    this.instance);
                }
                
                this.provider = defaultIntance;
            }
            
        } else {

            this.provider = null;
        }   
    }
    
    @SuppressWarnings("unchecked")
    void seleccionar(Alternativa<T> alternativa) {
        
        this.provider = this.instance.select((Class<T>) alternativa.getImplementacion());
    }
    
    public T get() {
        
        return this.provider != null ? this.provider.get() : null;
    }
    
    public Set<Alternativa<T>> getAlternativas() {
        
        Set<Alternativa<T>> alternativas = new HashSet<Alternativa<T>>();
        
        Set<Bean<?>> beans = beanManager.getBeans(this.interfaz, CUALIFICADORES_DISPONIBLES);

        /* remueve todos los beans que están cualificados como Dinámicos */
        for (Bean<?> bean: beans) {
            
            if (! bean.getQualifiers().contains(CUALIFICADOR_DINAMICO)) {
                
                alternativas.add(new Alternativa<T>(this, bean));
            }
        }
        
        return alternativas;
    }
}