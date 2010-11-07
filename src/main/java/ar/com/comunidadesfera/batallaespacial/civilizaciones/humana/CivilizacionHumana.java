package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Comandante;

/**
 * Civilizacion Humana.
 * Desarrollada para simplificar la construcción de Pilotos.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class CivilizacionHumana implements Civilizacion {

    private static final String NOMBRE = "Humanos";
    private static final String NOMBRE_COMANDANTE = "Comandante Humano";
    
    /**
     * Instancia de la Civilización Humana utilizada como referencia
     * en las partidas simples.
     */
    private static CivilizacionHumana instancia = null;
    
    /**
     * Comandante de la civilización.
     */
    private ComandanteHumano comandante;
     
    public CivilizacionHumana() {

        synchronized (CivilizacionHumana.class) {
            
            this.comandante = new ComandanteHumano(this, NOMBRE_COMANDANTE);
            
            instancia = this;
        }
    }
    
    public Comandante construirComandante() {
        
        return this.comandante;
    }

    public String getNombre() {

        return NOMBRE;
    }

    public static CivilizacionHumana getInstancia() {

        synchronized (CivilizacionHumana.class) {
            
            if (instancia == null) {
                
                throw new IllegalStateException("La Civilización Humana no ha sido creada" +
                		                        " en una partida");
            }
            
            return instancia;
        }
    }

    public ComandanteHumano getComandante() {

        return this.comandante;
    }
}
