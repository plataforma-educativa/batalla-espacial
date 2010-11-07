package ar.com.comunidadesfera.batallaespacial;

public interface Civilizacion {

    /**
     * Devuelve el nombre que identifica la Cilización.
     * 
     * @return nombre de la Civilización.
     */
	String getNombre();

    /**
     * @pre ninguna.
     * @post construye un Comandante para la Civilización.   
     * 
     * @return Comandante de la Civilización.
     */
    Comandante construirComandante();
    
}
