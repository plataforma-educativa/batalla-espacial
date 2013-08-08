package ar.com.comunidadesfera.plataformaeducativa;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.enterprise.inject.Produces;

import ar.com.comunidadesfera.fx.Iniciar;
import ar.com.comunidadesfera.persistencia.Configuracion;
import ar.com.comunidadesfera.persistencia.Configuracion.Tipo;

public class IniciarBatallaEspacial extends Iniciar {

    private static final String PERSISTENCE_UNIT_PROPERTIES = "/META-INF/persistence.properties";
    
    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_NAME)
    public final String PERSISTENCE_UNIT_NAME = "eficienciaPersistenceUnit";
    
    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_PROPERTIES)
    public Map<Object, Object> loadPersistenceunitProperties() {
        
        Properties persistenceUnitProperties = new Properties();
        
        try (InputStream origen = this.getClass().getResourceAsStream(PERSISTENCE_UNIT_PROPERTIES)) {
            
            persistenceUnitProperties.load(origen);
        
        } catch (Exception e) {
        
            throw new IllegalStateException("No fue posible cargar la configuración de persistencia desde: " +
                                            PERSISTENCE_UNIT_PROPERTIES, e);
        }
        
        return persistenceUnitProperties;
    }
    
    
    @Override
    protected String getFxmlLocation() {

        return "/ar/com/comunidadesfera/batallaespacial/interfaz/principal.fxml";
    }
}
