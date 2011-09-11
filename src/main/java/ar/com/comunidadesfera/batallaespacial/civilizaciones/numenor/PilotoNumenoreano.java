package ar.com.comunidadesfera.batallaespacial.civilizaciones.numenor;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;



public class PilotoNumenoreano implements Piloto {

    private CabinaDeControl cabina;
    private Numenor civilizacion;
    
    public PilotoNumenoreano(Numenor civilizacion) {
        super();
        this.civilizacion = civilizacion;
    }

    public void setCabinaDeControl(CabinaDeControl cabina) {

        this.cabina = cabina;
    }

    public Comando proximoComando() {

        Comando comando;
        
        Reporte reporte = this.cabina.getRadar().getReporte(Direccion.NORTE);
        
        if (reporte.getEspectro().equals(Espectro.VACIO)) {
            
            comando = this.cabina.getControl().avanzar( Direccion.NORTE );
            
        } else {
            
            comando = this.cabina.getControl().esperar();
        }
        
        return comando;
    }

    public Civilizacion getCivilizacion() {
        
        return this.civilizacion;
    }

    public String getNombre() {
        
        return "Piloto Numenoreano";
    }

}
