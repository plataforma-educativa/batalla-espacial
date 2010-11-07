package ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;

/**
 * Civilización utilizada para las pruebas unitarias.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class CivilizacionSimulada implements Civilizacion {

    public CivilizacionSimulada() {
    }

    public ComandanteSimulado construirComandante() {
        return new ComandanteSimulado(this);
    }

    public String getNombre() {
        return "Civilizacion Simulada";
    }
}