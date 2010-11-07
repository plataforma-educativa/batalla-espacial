package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.Direccion;

/**
 * Comando que representa la acción de AtaqueIndividual.
 * 
 * @author mariano
 *
 */
public interface Atacar extends Comando {

	
	/**
	 * @pre ninguna
	 * @post devuelve la Dirección hacia la cual se realiza el ataque.
	 * 
	 * @return Dirección de ataque.
	 */
	Direccion getDireccion();
    
}
