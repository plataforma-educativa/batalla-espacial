package ar.com.comunidadesfera.plataformaeducativa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import ar.com.comunidadesfera.clasificadores.Clasificadores;
import ar.com.comunidadesfera.fx.Aplicacion;
import ar.com.comunidadesfera.fx.Iniciar;

public class AplicacionBatallaEspacial extends Aplicacion {

    @Override
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {

        return instancias.select(IniciarBatallaEspacial.class, Clasificadores.Basica);
    }

    public static void main(String[] args) {
        
        Application.launch(args);
    }

    @Override
    protected void cargando(Stage stage) {

        Pane panel = new Pane();
        panel.setStyle("-fx-background-image: url('/ar/com/comunidadesfera/batallaespacial/interfaz/inicio.png')");
        Scene scene = new Scene(panel, 600, 231);
        stage.setScene(scene);

    }

}
