package ar.com.comunidadesfera.batallaespacial.juego;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Radar;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;

/**
 * 
 *
 */
public class ControlDeRadar {

    /**
     * Tablero que controla. 
     */
    private Tablero tablero;
    
    /**
     * Iterador sobre el Tablero
     * @param tablero
     */
    private Tablero.Iterador iterador;
    
    /**
     * Crea el ControlAplicacion de Radar para el Tablero dado. 
     * @param tablero
     */
    public ControlDeRadar(Tablero tablero) {
        
        super();
        this.tablero = tablero;
        this.iterador = this.tablero.iterator();
    }


    public Pieza buscarPieza(Pieza pieza, Direccion direccion, int distancia) 
        throws CasilleroInvalidoException {
        
        return this.iterador.move(pieza, direccion, distancia)
                            .get();
    }
    
    /**
     * @pre ninguna.
     * @post construye un Radar para la Pieza dada.
     * 
     * @param pieza : Pieza en la que se localiza el Radar.
     * @return Radar para la Pieza.
     */
    public Radar crearRadar(Pieza pieza) {
        
        return new RadarImpl(pieza);
    }
    
    /**
     * Implementación de Radar.
     *
     */
    private class RadarImpl implements Radar {

        public static final int DISTANCIA = 1;
        
        private Pieza pieza;
        
        private RadarImpl(Pieza pieza) {
            
            this.pieza = pieza;
        }
        
        public Reporte getReporte(Direccion direccion) {

            Reporte reporte = null;
            
            try {
                
            
                Pieza pieza = ControlDeRadar.this.buscarPieza(this.pieza, direccion, 
                                                              DISTANCIA);

                if (pieza != null) {
                    
                    reporte = pieza.reportar();
                    
                } else {
                    
                    reporte = new ReporteEstatico(Espectro.VACIO, null, null);
                }
                
            } catch (CasilleroInvalidoException cie) {
                
                /* no existe información */
                reporte = new ReporteEstatico(Espectro.DESCONOCIDO, null, null);
            }
            
            return reporte;
        }
    
    }
    
}
