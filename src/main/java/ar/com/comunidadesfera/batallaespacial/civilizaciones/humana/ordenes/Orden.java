package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Orden impartida sobre un Piloto Humano que controla la ejecución de un
 * Comando.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface Orden {

    /**
     * @post se ejecuta la orden sobre el Piloto y devuelve el comando
     *       resultante.
     */
    Comando ejecutar(PilotoHumano piloto);
}
