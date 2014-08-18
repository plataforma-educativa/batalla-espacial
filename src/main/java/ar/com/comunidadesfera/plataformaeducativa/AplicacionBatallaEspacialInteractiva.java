package ar.com.comunidadesfera.plataformaeducativa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import ar.com.comunidadesfera.fx.Aplicacion;
import ar.com.comunidadesfera.fx.Iniciar;

/**
 * Aplicación JavaFX que inicia una versión de la Batalla Espacial en modo Interactivo.
 *
 */
public class AplicacionBatallaEspacialInteractiva extends Aplicacion {

    @Override
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {

        return instancias.select(IniciarBatallaEspacialInteractiva.class);
    }
    
    @Override
    protected void cargaTerminada() {

        super.cargaTerminada();

        /* notifica a la Ejecución que la Batalla Espacial está lista para
         * interactuar con ella */
        EjecucionInteractiva.instancia().cargaTerminada();
    }
    
    @Override
    protected void cargando(Stage stage) {
        
        Pane panel = new Pane();
        panel.setStyle("-fx-background-image: url('/ar/com/comunidadesfera/batallaespacial/interfaz/inicio-interactivo.png')");
        Scene scene = new Scene(panel, 600, 231);
        stage.setScene(scene);
    }

    @Override
    protected void cerrando(Stage stage) {

        super.cerrando(stage);
        
        /* fuerza el cierre del Intérprete de DrJava */
        System.exit(0);
    }
    
    public static void main(String[] args) {
        
        Application.launch(args);
    }
}
