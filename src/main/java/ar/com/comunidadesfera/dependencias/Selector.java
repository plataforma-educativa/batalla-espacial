package ar.com.comunidadesfera.dependencias;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.clasificadores.Dinamica;

/**
 * Permite seleccionar entre las múltiples implementaciones disponibles para las interfaces
 * de las que depende el kernel de la Batalla Espacial.
 * 
 * El Selector proporciona las instancias a ser inyectadas para aquellas dependencias
 * clasificadas con @Dinamica.  
 * La implementación puede ser cambiada en tiempo de ejecución.
 * 
 * @author Mariano Tugnarelli
 *
 */
@ApplicationScoped
public class Selector {

    
    private Proveedor<BatallaEspacial> batallaEspacial;
    
    @Inject
    protected void configurar(BeanManager beanManager, @Any Instance<BatallaEspacial> batallaEspacial) {

        this.batallaEspacial = new Proveedor<BatallaEspacial>(beanManager,
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
    
    public Alternativa<BatallaEspacial> getAlternativa(Class<? extends BatallaEspacial> implementacion) {
        
        Alternativa<BatallaEspacial> encontrada = null;
        
        for (Alternativa<BatallaEspacial> alternativa : this.getAlternativasBatallaEspacial()) {
            
            if (alternativa.getImplementacion().equals(implementacion)) {
                
                encontrada = alternativa;
            }
        }
        
        return encontrada;
    }
}
