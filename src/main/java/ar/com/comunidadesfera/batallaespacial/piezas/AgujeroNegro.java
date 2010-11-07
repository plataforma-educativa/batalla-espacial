package ar.com.comunidadesfera.batallaespacial.piezas;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.ReporteEstatico;

public class AgujeroNegro extends Pieza {

    private static final Reporte reporte = new ReporteEstatico(Espectro.DESCONOCIDO, null, null);
    
    public AgujeroNegro() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public String getIdentificacion() {

        return "Agujero Negro";
    }

    @Override
    public Reporte reportar() {

        return reporte;
    }

}
