package ar.com.comunidadesfera.fx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import org.controlsfx.dialog.Dialogs;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public abstract class Aplicacion extends Application {

    private Stage stageCargando;
    
    protected final Task<WeldContainer> cargar = new Task<WeldContainer>() {
        
        @Override
        protected WeldContainer call() throws Exception {
            
            return new Weld().initialize();
        }
        
        @Override
        protected void succeeded() {

            try {

                Stage stagePrincipal = new Stage(StageStyle.DECORATED);
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
    
    
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {
        
        return instancias.select(Iniciar.class);
    }
}
