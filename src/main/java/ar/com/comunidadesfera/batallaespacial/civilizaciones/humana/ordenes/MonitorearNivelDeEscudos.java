package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;

/**
 * Ordena monitorear el nivel de los escudos.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class MonitorearNivelDeEscudos extends OrdenSincronizada<Byte> {

    @Override
    protected Byte getResultado(PilotoHumano piloto) {
        
        return piloto.getCabinaDeControl().getMonitor().getNivelDeEscudos();
    }

    
}
