package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Piloto de la Civilización Humana.
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
public class PilotoHumano implements Piloto {

    private ComandanteHumano comandante;
    
    private String nombre;
    
    private CabinaDeControl cabina;

    private PlanDeVuelo planDeVuelo;
    
    public PilotoHumano(ComandanteHumano comandante,
                        String nombre) {
        
        this.nombre = nombre;
        
        this.setComandante(comandante);
    }
    
    public Comando proximoComando() {

        return this.getPlanDeVuelo() != null ? 
                    this.getPlanDeVuelo().proximoComando(this) : 
                    this.getCabinaDeControl().getControl().esperar();
                
    }

    public void setCabinaDeControl(CabinaDeControl cabina) {
    
        this.cabina = cabina;
    }
    
    public CabinaDeControl getCabinaDeControl() {
        
        return this.cabina;
    }

    public Civilizacion getCivilizacion() {

        return this.comandante.getCivilizacion();
    }

    public String getNombre() {
        
        return this.nombre;
    }
    
    public PlanDeVuelo getPlanDeVuelo() {
        
        return this.planDeVuelo;
    }

    public void setPlanDeVuelo(PlanDeVuelo planDeVuelo) {
        
        this.planDeVuelo = planDeVuelo;
    }
    
    private void setComandante(ComandanteHumano comandante) {
        
        this.comandante = comandante;
    }
    
    public ComandanteHumano getComandante() {
       
        return this.comandante;
    }
}
