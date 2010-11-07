package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;

/**
 * Ordena monitorear el estado de la carga de una Sustancia.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class MonitorearCarga extends OrdenSincronizada<Long>{

    private Sustancia sustancia;
    
    public MonitorearCarga(Sustancia sustancia) {

        this.sustancia = sustancia;
    }
    
    @Override
    protected Long getResultado(PilotoHumano piloto) {
     
        return piloto.getCabinaDeControl().getMonitor().getCarga(this.sustancia);
    }

}
