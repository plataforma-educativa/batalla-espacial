package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.Direccion;

/**
 * Comando que representa la acción de Avance.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Avanzar extends Comando {

	/**
	 * @pre ninguna.
	 * @post devuelve la Dirección de avance.
	 * 
	 * @return dirección en la cual se ejecuta la acción de avance.
	 */
	Direccion getDireccion();
	
	/**
	 * @pre ninguna.
	 * @post devuelve el valor del impulso utilizado para avanzar.
	 * 
	 * @return valor de impulso.
	 */
	int getImpulso();
}
