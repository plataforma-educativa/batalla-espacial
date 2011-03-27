package ar.com.comunidadesfera.eficiencia;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;

/**
 * Interfaz común para todos los Instrumentos de Medición de Módulos, que 
 * crean Mediciones.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface InstrumentoDeMedicion {

    /**
     * @return devuelve una nueva Medición que tiene los datos recolectados
     *         por el Instrumento de Medición.
     */
    Medicion getMedicion();
}
