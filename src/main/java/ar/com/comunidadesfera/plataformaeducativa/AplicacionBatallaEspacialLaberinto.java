package ar.com.comunidadesfera.plataformaeducativa;

import javafx.application.Application;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import ar.com.comunidadesfera.fx.Iniciar;

public class AplicacionBatallaEspacialLaberinto extends AplicacionBatallaEspacialInteractiva {

    @Override
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {

        return instancias.select(IniciarBatallaEspacialLaberinto.class);
    }

    public static void main(String[] args) {
        
        Application.launch(args);
    }    
}
