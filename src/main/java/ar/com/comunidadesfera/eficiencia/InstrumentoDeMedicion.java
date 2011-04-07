package ar.com.comunidadesfera.eficiencia;

import java.util.List;

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
     *         por el Instrumento de Medición. La Medición devuelta
     *         corresponde con el resultado total del proceso.
     */
    Medicion getMedicion();
    
    /**
     * @return devuelve una lista con todas las Mediciones intermedias,
     *         parciales y totales recolectadas por el Instrumento de
     *         Medición.
     */
    List<Medicion> getMediciones();
}
