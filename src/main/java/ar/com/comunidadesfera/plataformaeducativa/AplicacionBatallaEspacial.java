package ar.com.comunidadesfera.plataformaeducativa;

import javafx.application.Application;
import ar.com.comunidadesfera.fx.Aplicacion;
import ar.com.comunidadesfera.fx.Iniciar;

public class AplicacionBatallaEspacial extends Aplicacion {

    @Override
    protected Class<? extends Iniciar> getClaseIniciar() {
    
        return IniciarBatallaEspacial.class;
    }

    public static void main(String[] args) {
        
        Application.launch(args);
    }

}
