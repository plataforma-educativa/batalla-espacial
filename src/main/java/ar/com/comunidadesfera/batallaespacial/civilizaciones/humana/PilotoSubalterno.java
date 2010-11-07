package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Piloto Humano que se encuentra bajo las ordenes de un Comandante Humano. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public class PilotoSubalterno extends SoldadoSubalterno<PilotoHumano, ComandanteHumano> 
                                implements Piloto {

    public PilotoSubalterno(PilotoHumano piloto) {
        
        super(piloto, piloto.getComandante());
        
        /* el piloto se reporta como subalterno del comandante */
        this.getSoldado().getComandante().reportarSubalterno(this);
        
        /* se utiliza un Plan de Vuelo Comandado para controlar al Piloto Subalterno */
        this.getSoldado().setPlanDeVuelo(new PlanDeVueloComandado(this.getOrdenes()));
        
    }
    
    public Comando proximoComando() {
        
        return this.getSoldado().proximoComando();
    }

    public void setCabinaDeControl(CabinaDeControl cabina) {

        this.getSoldado().setCabinaDeControl(cabina);
    }
    
    public CabinaDeControl getCabinaDeControl() {
        
        return this.getSoldado().getCabinaDeControl();
    }
    
    public PlanDeVuelo getPlanDeVuelo() {
        
        return this.getSoldado().getPlanDeVuelo();
    }

}
