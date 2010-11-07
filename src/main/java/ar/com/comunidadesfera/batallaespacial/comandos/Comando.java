package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * 
 * 
 * @author Mariano Tugnarelli
 */
public interface Comando {

	/**
	 * @pre ninguna.
	 * @post ejecuta el Comando en la Partida dada.
	 *
	 */
	void ejecutar(Partida partida);

    /**
     * @pre ninguna.
     * @post si la instancia invocada es invertible, 
     *       crea el Comando inverso y lo devuelve. Devuelve null
     *       el caso de que el Comando no sea invertible. 
     * 
     * @return Comando inverso a la instancia invocada.
     */
    Comando invertir(); 
}
