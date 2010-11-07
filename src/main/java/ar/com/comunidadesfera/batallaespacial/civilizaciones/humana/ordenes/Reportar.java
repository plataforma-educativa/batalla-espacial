package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;

/**
 * Orden que permite obtener el reporte de un Radar.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class Reportar extends OrdenSincronizada<Reporte> {

    private Direccion direccion;
    
    public Reportar(Direccion direccion) {

        this.direccion = direccion;
    }
    
    @Override
    protected Reporte getResultado(PilotoHumano piloto) {
        
        return this.piloto.getCabinaDeControl().getRadar().getReporte(direccion);
    }
}
