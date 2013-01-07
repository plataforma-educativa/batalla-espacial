package ar.com.comunidadesfera.plataformaeducativa;

import java.io.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class ApplicationStarter {
    
    @Inject 
    private FXMLLoader fxmlLoader;
    
    public void launchJavaFXApplication(@Observes @StartupScene Stage s) {

        InputStream is = null;

        try {
            
            is = getClass().getResourceAsStream("/ar/com/comunidadesfera/batallaespacial/interfaz/principal.fxml");
            
            Parent root = (Parent) fxmlLoader.load(is);
            
            s.setTitle("Batalla Espacial");
            
            s.setScene(new Scene(root, 800, 600));
            
            s.show();
            
        } catch (IOException e) {
            
            throw new IllegalStateException("cannot load FXML login screen", e);
        
        } finally {
            
            if (is != null) {
            
                try {
                
                    is.close();
                    
                } catch (IOException ignorada) {}
            }
        }
    }
}
