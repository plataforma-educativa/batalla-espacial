package ar.com.comunidadesfera.plataformaeducativa;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Provider;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.calificadores.Basica;
import ar.com.comunidadesfera.batallaespacial.calificadores.Dinamica;

/**
 * Responsable de proveer componentes que exponen una interfaz determinada 
 * para la cual existen múltiples implementaciones.
 * La implementación concreta se selecciona dinámicamente en tiempo de ejecución.
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
@ApplicationScoped
public class ProveedorDeComponentes {
    
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
    
    private Proveedor<BatallaEspacial> batallaEspacial;
    
    private BeanManager beanManager;
    
    @Inject
    public void setBeanManager(BeanManager beanManager) {
        
        this.beanManager = beanManager;
    }
    
    @Inject
    public void setBatallaEspacial(@Any Instance<BatallaEspacial> batallaEspacial) {

        this.batallaEspacial = new Proveedor<BatallaEspacial>(BatallaEspacial.class, 
                                                              batallaEspacial);
    }
    
    @Produces @Dinamica
    public BatallaEspacial getBatallaEspacial() {

        return this.batallaEspacial.get();
    }
    
    public Set<Alternativa<BatallaEspacial>> getAlternativasBatallaEspacial() {
        
        return this.batallaEspacial.getAlternativas();
    }
    
    private class Proveedor<T> {
        
        private final Class<T> interfaz;
        
        private final Instance<T> instance;
        
        private Provider<T> provider;
        
        public Proveedor(Class<T> interfaz, Instance<T> instance) {
         
            this.interfaz = interfaz;
            this.instance = instance;
            
            this.seleccionar();
        }
        
        private void seleccionar() {
            
            if (! this.instance.isUnsatisfied()) {

                if (! this.instance.isAmbiguous()) {
                    
                    this.provider = this.instance;
                    
                } else {
                    
                    this.provider = this.instance.select(CUALIFICADORES_POR_DEFECTO);
                }
                
            } else {

                this.provider = null;
            }   
        }
        
        @SuppressWarnings("unchecked")
        protected void seleccionar(Alternativa<T> alternativa) {
            
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
    
    
    public class Alternativa<T> {
        
        private Proveedor<T> proveedor;
        
        private Bean<?> bean;
        
        protected Alternativa(Proveedor<T> proveedor, Bean<?> bean) {
            
            this.bean = bean;
            this.proveedor = proveedor;
        }
        
        public Class<?> getImplementacion() {
            return this.bean.getBeanClass();
        }
        
        public void seleccionar() {
            
            this.proveedor.seleccionar(this);
        }
    }
}
