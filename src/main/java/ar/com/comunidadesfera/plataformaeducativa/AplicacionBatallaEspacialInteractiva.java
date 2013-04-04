package ar.com.comunidadesfera.plataformaeducativa;

import javafx.application.Application;
import ar.com.comunidadesfera.fx.Aplicacion;
import ar.com.comunidadesfera.fx.Iniciar;

public class AplicacionBatallaEspacialInteractiva extends Aplicacion {

    @Override
    protected Class<? extends Iniciar> getClaseIniciar() {
    
        return IniciarBatallaEspacialInteractiva.class;
    }

    public static void main(String[] args) {
        
        Application.launch(args);
    }

}
