package ar.com.comunidadesfera.plataformaeducativa;

import java.util.Map;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.calificadores.Dinamica;
import ar.com.comunidadesfera.persistencia.Configuracion;
import ar.com.comunidadesfera.persistencia.Configuracion.Tipo;

/**
 * 
 * @author Mariano Tugnarelli
 *
 */
@ApplicationScoped
public class PlataformaEducativa {

    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_NAME)
    public static final String PERSISTENCE_UNIT_NAME = "";
    
    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_PROPERTIES)
    public static final Map<Object, Object> PERSISTENCE_UNIT_PROPERTIES = new Properties();
    
    private BatallaEspacial batallaEspacial;
    
    @Inject 
    public void setBatallaEspacial(@Dinamica BatallaEspacial batallaEspacial) {
        
        this.batallaEspacial = batallaEspacial;
    }
    
    public void iniciar() {
        
        this.batallaEspacial.iniciar();
    }
    
    public static void main(String[] args) {

        WeldContainer weld = new Weld().initialize();
        weld.instance().select(PlataformaEducativa.class).get().iniciar();
    }
}
