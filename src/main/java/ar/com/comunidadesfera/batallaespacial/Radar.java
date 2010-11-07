package ar.com.comunidadesfera.batallaespacial;

/**
 * El Radar da información del contexto en el que se 
 * encuentra una Nave.
 * 
 * @author Mariano Tugnarelli
 */
public interface Radar {

    /**
	 * @pre <code>direccion</code> no es nulo.
	 * @post devuelve un Reporte con los datos acerca de la 
     *       posición contigua en la Direccion dada.
	 *  
	 * @param direccion : Dirección de la cual se quiere obtener el Reporte. 
	 * @return Reporte de datos.
	 */
	Reporte getReporte(Direccion direccion);
}
