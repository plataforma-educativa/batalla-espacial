package ar.com.comunidadesfera.batallaespacial;

/**
 * Las Notificaciones informan al Piloto de sucesos provocados
 * entre un movimiento y el siguiente sobre la Nave que controla.
 *
 */
public enum Notificacion {

    /**
     * La Nave despegó de la Base.
     */
    DESPEGUE,
    
    /**
     * La Nave chocó.
     */
    CHOQUE,
    
    /**
     * La Nave fue atacada.
     */
    ATAQUE, 
    
    /**
     * La Nave fue destruida.
     */
    DESTRUCCION,
}
