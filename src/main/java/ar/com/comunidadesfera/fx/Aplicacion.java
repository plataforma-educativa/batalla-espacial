package ar.com.comunidadesfera.fx;

import javafx.application.Application;
import javafx.stage.Stage;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public abstract class Aplicacion extends Application {

    @Override
    public void start(Stage stagePrincipal) throws Exception {

        WeldContainer weldContainer = new Weld().initialize();
        
        weldContainer.instance().select(this.getClaseIniciar()).get().ejecutar(stagePrincipal);
    }
    
    protected abstract Class<? extends Iniciar> getClaseIniciar();
}
