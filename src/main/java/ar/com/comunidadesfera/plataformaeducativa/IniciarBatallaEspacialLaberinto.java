package ar.com.comunidadesfera.plataformaeducativa;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.galaxias.magallanes.BatallaEspacialMagallanes;
import ar.com.comunidadesfera.dependencias.Selector;

public class IniciarBatallaEspacialLaberinto extends IniciarBatallaEspacial {

    @Inject
    private Selector selector;
    
    @Override
    protected void configurar() {

        super.configurar();

        this.selector.getAlternativa(BatallaEspacialMagallanes.class).seleccionar();
    }
}
