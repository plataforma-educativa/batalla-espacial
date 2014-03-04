package ar.com.comunidadesfera.plataformaeducativa;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.galaxias.andromeda.BatallaEspacialAndromeda;
import ar.com.comunidadesfera.dependencias.Selector;

public class IniciarBatallaEspacialInteractiva extends IniciarBatallaEspacial {

    @Inject
    private Selector selector;
    
    @Override
    protected void configurar() {

        super.configurar();

        this.selector.getAlternativa(BatallaEspacialAndromeda.class).seleccionar();
    }
}
