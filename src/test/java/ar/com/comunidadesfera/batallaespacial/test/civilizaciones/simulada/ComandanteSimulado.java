package ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada;

import ar.com.comunidadesfera.batallaespacial.Comandante;

/**
 * Comandante utilizado para las pruebas unitarias.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class ComandanteSimulado implements Comandante {

    public CivilizacionSimulada civilizacion;
    
    public ComandanteSimulado(CivilizacionSimulada civilizacion) {
        this.civilizacion = civilizacion;
    }

    public PilotoSimulado[] construirEscuadron(int integrantes)
            throws IllegalArgumentException {
        
        if (integrantes <= 0) {
            
            throw new IllegalArgumentException("Cantidad de integrantes de un escuadrón inválido: " + 
                                               integrantes);
        }
                    
        PilotoSimulado escuadron[] = new PilotoSimulado[integrantes];
        
        for (int i = 0; i < escuadron.length; i++) {
            
            escuadron[i] = new PilotoSimulado(getCivilizacion());
        }

        return escuadron;
    }

    public CivilizacionSimulada getCivilizacion() {
        
        return civilizacion;
    }

    public String getNombre() {
        
        return "Comandante " + this.getCivilizacion().getNombre();
    }

}
