package ar.com.comunidadesfera.batallaespacial.galaxias.andromeda;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;

public class BatallaEspacialAndromeda extends BatallaEspacial {

    @Override
    protected ControlAplicacion crearControlAplicacion() {

        return new ControlAplicacion(this.getVista());
    }
}
