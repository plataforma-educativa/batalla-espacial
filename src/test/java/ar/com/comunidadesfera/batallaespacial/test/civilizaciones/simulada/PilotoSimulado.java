package ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Pilotoo utilizado para las pruebas unitarias.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class PilotoSimulado implements Piloto {

    private CivilizacionSimulada civilizacion;

    private CabinaDeControl cabina;
    
    public PilotoSimulado(CivilizacionSimulada civilizacion) {
        this.civilizacion = civilizacion;
    }

    public Comando proximoComando() {
        return null;
    }

    public void setCabinaDeControl(CabinaDeControl cabina) {
        this.cabina = cabina;
    }

    public CabinaDeControl getCabinaDeControl() {
        return cabina;
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public String getNombre() {
        return "Piloto " + this.getCivilizacion().getNombre();
    }

}
