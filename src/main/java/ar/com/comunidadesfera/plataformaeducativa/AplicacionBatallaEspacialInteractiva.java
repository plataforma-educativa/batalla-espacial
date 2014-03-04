package ar.com.comunidadesfera.plataformaeducativa;

import javafx.application.Application;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import ar.com.comunidadesfera.fx.Aplicacion;
import ar.com.comunidadesfera.fx.Iniciar;

public class AplicacionBatallaEspacialInteractiva extends Aplicacion {

    @Override
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {

        return instancias.select(IniciarBatallaEspacialInteractiva.class);
    }
    
    public static void main(String[] args) {
        
        Application.launch(args);
    }

}
