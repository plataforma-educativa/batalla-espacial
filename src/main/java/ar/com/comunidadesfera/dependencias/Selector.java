package ar.com.comunidadesfera.dependencias;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.calificadores.Dinamica;

/**
 * Permite seleccionar entre las múltiples implementaciones disponibles para las interfaces
 * de las que depende el kernel de la Batalla Espacial.
 * 
 * El Selector proporciona las instancias a ser inyectadas para aquellas dependencias
 * cualificada con @Dinamica.  
 * La implementación puede ser cambiada en tiempo de ejecución.
 * 
 * @author Mariano Tugnarelli
 *
 */
@ApplicationScoped
public class Selector {

    
    private Proveedor<BatallaEspacial> batallaEspacial;
    
    private BeanManager beanManager;
    
    @Inject
    public void setBeanManager(BeanManager beanManager) {
        
        this.beanManager = beanManager;
    }
    
    @Inject
    public void setBatallaEspacial(@Any Instance<BatallaEspacial> batallaEspacial) {

        this.batallaEspacial = new Proveedor<BatallaEspacial>(this.beanManager,
                                                              BatallaEspacial.class, 
                                                              batallaEspacial);
    }
    
    @Produces @Dinamica
    public BatallaEspacial getBatallaEspacial() {

        return this.batallaEspacial.get();
    }
    
    public Set<Alternativa<BatallaEspacial>> getAlternativasBatallaEspacial() {
        
        return this.batallaEspacial.getAlternativas();
    }
}
