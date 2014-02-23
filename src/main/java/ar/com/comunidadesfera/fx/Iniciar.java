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

            Scene scene = new Scene(root);
            this.configurar(scene);
            stage.setScene(scene);
            
            this.configurar(stage);
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
    
    /**
     * pre : aún no se ha inicializado la Aplicación.
     * post: configura el Scene de la Aplicación, antes de agregarlo al Stage principal.
     *
     * Punto de extensión para que cada implementación pueda llevar adelante tareas de configuración
     * sobre la Scene, por ejemplo para agregar stylesheets, etc.
     * 
     * @param scene
     */
    protected void configurar(Scene scene) {
        
    }
 
    /**
     * pre : aún no se ha inicializado la Aplicación.
     * post: configura el Stage de la Aplicación, antes de mostrarlo.
     *
     * Punto de extensión para que cada implementación pueda llevar adelante tareas de configuración
     * sobre el Stage, por ejemplo para definir el ícono.
     *
     * @param stage
     */
    protected void configurar(Stage stage) {
        
        stage.setTitle(this.fxmlLoader.getResources().getString("titulo"));
    }
}
