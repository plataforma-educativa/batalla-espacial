package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;

/**
 * Ordena monitorear la cantidad de torpedos disponibles.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class MonitorearCantidadDeTorpedos extends OrdenSincronizada<Integer> {

    @Override
    protected Integer getResultado(PilotoHumano piloto) {
        
        return piloto.getCabinaDeControl().getMonitor().getCantidadDeTorpedos();
    }

}
