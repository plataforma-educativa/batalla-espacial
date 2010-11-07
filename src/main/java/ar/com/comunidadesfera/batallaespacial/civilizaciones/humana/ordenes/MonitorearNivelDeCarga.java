package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;

/**
 * Ordena monitorear el nivel de carga.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class MonitorearNivelDeCarga extends OrdenSincronizada<Byte> {

    @Override
    protected Byte getResultado(PilotoHumano piloto) {
        
        return piloto.getCabinaDeControl().getMonitor().getNivelDeCarga();
    }

}
