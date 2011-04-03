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

}
