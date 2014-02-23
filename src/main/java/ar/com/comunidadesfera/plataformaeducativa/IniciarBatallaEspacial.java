package ar.com.comunidadesfera.plataformaeducativa;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    
    @Override
    protected void configurar(Scene scene) {
        
        super.configurar(scene);
        scene.getStylesheets().add("ar/com/comunidadesfera/batallaespacial/interfaz/estilos.css");
    }
    
    @Override
    protected void configurar(Stage stage) {

        super.configurar(stage);
        stage.setMinWidth(720);
        stage.setMinHeight(600);
        stage.getIcons().add(this.cargar("/ar/com/comunidadesfera/batallaespacial/interfaz/iconos/x128/icon.png"));
    }
    
    protected Image cargar(String url) {
        
        return new Image(this.getClass().getResourceAsStream(url));
    }
}
