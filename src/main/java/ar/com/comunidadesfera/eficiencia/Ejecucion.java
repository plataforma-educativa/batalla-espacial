package ar.com.comunidadesfera.eficiencia;

import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;

public interface Ejecucion {

    /**
     * @return Módulo al que corresponde la Ejecución.
     */
    Modulo getModulo();

    /**
     * @return Problema sobre el que se realiza la Ejecución.
     */
    Problema getProblema();

    /**
     * @return Contador de instrucciones asociado a la Ejecución.
     */
    Contador contarInstrucciones();
    
    /**
     * @post registra un Observador de la Ejecución.
     * @param observador
     */
    void agregarObservador(Observador observador);

    /**
     * @post da por terminada la Ejecución. Invocaciones posteriores a métodos
     *       que devuelvan Contadores generarán exceptiones. 
     */
    void terminar();

    
    /**
     * Interfaz a implementar por aquellos componentes que deseen ser
     * notificados de los eventos generados por una ejecución.
     *
     */
    public interface Observador {
        
        /**
         * @pre <code>instrumento</code> ha sido creado para medir
         *      la Ejecución.
         *      
         * @param ejecucion
         * @param instrumento Instrumento de Medición creado.
         */
        void instrumentoDeMedicionCreado(Ejecucion ejecucion, 
                                         InstrumentoDeMedicion instrumento);
        
        /**
         * @pre la Ejecución ha terminado.
         * @param ejecucion 
         */
        void ejecucionTerminada(Ejecucion ejecucion);
        
    }
}
