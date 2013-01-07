package ar.com.comunidadesfera.plataformaeducativa;

import javax.enterprise.util.AnnotationLiteral;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javafx.application.Application;
import javafx.stage.Stage;

public class PlataformaEducativaFX extends Application {

    @Override
    @SuppressWarnings("serial")
    public void start(Stage stagePrincipal) throws Exception {

        WeldContainer weldContainer = new Weld().initialize();
        
        weldContainer.event().select(Stage.class, new AnnotationLiteral<StartupScene>() {}).fire(stagePrincipal);
    }
    
    public static void main(String[] args) {
        
        Application.launch(args);
    }
}
