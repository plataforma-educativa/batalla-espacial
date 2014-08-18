package ar.com.comunidadesfera.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import org.controlsfx.dialog.Dialogs;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public abstract class Aplicacion extends Application {

    private Stage stageCargando;
    
    protected final EventHandler<WindowEvent> cerrar = new EventHandler<WindowEvent> () {
        
        @Override
        public void handle(WindowEvent event) {
            
            cerrando((Stage) event.getSource());
        }
    };
    
    protected final Task<WeldContainer> cargar = new Task<WeldContainer>() {
        
        @Override
        protected WeldContainer call() throws Exception {
            
            return new Weld().initialize();
        }
        
        @Override
        protected void succeeded() {

            try {

                Stage stagePrincipal = new Stage(StageStyle.DECORATED);
                stagePrincipal.setOnCloseRequest(cerrar);
                
                WeldContainer weldContainer = this.valueProperty().get();
                seleccionarIniciar(weldContainer.instance()).get().ejecutar(stagePrincipal);
                
            } catch (Exception e) {

                Dialogs.create()
                       .showException(e);
                
            } finally {
                
                cargaTerminada();
            }
        }
    };
    
    protected void cargaTerminada() {
        
        this.stageCargando.close();
    }
    
    @Override
    public void start(Stage stageInicial) throws Exception {
        
        this.stageCargando = stageInicial;
        
        this.stageCargando.initStyle(StageStyle.UNDECORATED);
        this.stageCargando.setIconified(false);
        cargando(this.stageCargando);
        this.stageCargando.show();

        new Thread(this.cargar).start();
    }
    
    protected abstract void cargando(Stage stage);
    
    protected void cerrando(Stage stage) {
        
        Platform.exit();
    }
    
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {
        
        return instancias.select(Iniciar.class);
    }
}
