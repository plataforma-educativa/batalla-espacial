package ar.com.comunidadesfera.fx;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

public abstract class Iniciar {
    
    @Inject 
    private FXMLLoader fxmlLoader;
    
    public void ejecutar(Stage stage) {

        this.configurar();
        
        try (InputStream is = getClass().getResourceAsStream(this.getFxmlLocation())) {
            
            Parent root = (Parent) fxmlLoader.load(is);

            stage.setScene(new Scene(root, 800, 600));
            
            stage.show();
            
        } catch (IOException e) {
            
            throw new IllegalStateException("No se pudo cargar el FXML: " + this.getFxmlLocation(), e);
        }
    }

    protected abstract String getFxmlLocation();
    
    /**
     * pre : aún no se ha inicializado la Aplicación.
     * post: configura el ambiente.
     * 
     * Punto de extensión para que cada implementación pueda llevar adelante tareas de configuración
     * previas a iniciar la Aplicación.
     */
    protected void configurar() {
        
    }
}
