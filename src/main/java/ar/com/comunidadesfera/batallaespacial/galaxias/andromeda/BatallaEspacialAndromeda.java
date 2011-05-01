package ar.com.comunidadesfera.batallaespacial.galaxias.andromeda;

import ar.com.comunidadesfera.batallaespacial.aplicacion.BatallaEspacialBasica;
import ar.com.comunidadesfera.batallaespacial.calificadores.Alternativa;

@Alternativa
public class BatallaEspacialAndromeda extends BatallaEspacialBasica {

    @Override
    protected ControlAplicacion crearControlAplicacion() {

        return new ControlAplicacion(this.getVista());
    }
}
