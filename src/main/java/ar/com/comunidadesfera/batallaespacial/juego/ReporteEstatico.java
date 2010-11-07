package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.Map;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Sustancia;

/**
 * Un Reporte Estático posee información que no se actualiza luego de su
 * construcción.  
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ReporteEstatico implements Reporte {

    private Civilizacion civilizacion;

    private Espectro espectro;

    private Map<Sustancia, Long> sustancias;
    
    /**
     * @pre <code>espectro</code> no puede ser null.
     * @post construye el reporte a partir de los parámetros dados.
     * 
     * @param espectro : Espectro detectado.
     * @param civilizacion : Civilización encontrada.
     * @param cantidadDeAntimateria : cantidad de antimateria encontrada.
     */
    public ReporteEstatico(Espectro espectro, Civilizacion civilizacion,
                           Map<Sustancia, Long> sustancias) {
        
        super();
        this.setEspectro(espectro);
        this.setCivilizacion(civilizacion);
        this.setSustancias(sustancias);
    }

    protected void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    protected void setEspectro(Espectro espectro) {
    
        /* el espectro no puede ser null */
        if (espectro == null) {
            throw new IllegalArgumentException("Espectro no puede ser null");
        }
        
        this.espectro = espectro;
    }

    public Espectro getEspectro() {
        return this.espectro;
    }

    public Civilizacion getCivilizacion() {
        return this.civilizacion;
    }

    public long getCantidadDeSustancia(Sustancia sustancia) {

        Long cantidad = null;
        
        if (this.sustancias != null) {
            
            cantidad = this.sustancias.get(sustancia);

        }

        return cantidad != null ? cantidad : 0;
    }

    protected void setSustancias(Map<Sustancia, Long> sustancias) {
        this.sustancias = sustancias;
    }


}
