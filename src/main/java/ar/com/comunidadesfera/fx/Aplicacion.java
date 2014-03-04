package ar.com.comunidadesfera.fx;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public abstract class Aplicacion extends Application {

    @Override
    public void start(Stage stagePrincipal) throws Exception {

        WeldContainer weldContainer = new Weld().initialize();
        
        this.seleccionarIniciar(weldContainer.instance()).get().ejecutar(stagePrincipal);
    }
    
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {
        
        return instancias.select(Iniciar.class);
    }
}
