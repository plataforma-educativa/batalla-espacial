package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.Arma;

/**
 * Ataque producido por una Pieza a un grupo de Piezas 
 * con un único tipo de Arma.
 * 
 */
public class AtaqueSimple implements Ataque {

    /**
     * Puntos para todas las Armas por defecto. 
     */
    public static final int PUNTOS_POR_ARMA = 5;
    
    
    /**
     * Puntos de ataque para cada Arma.
     */
    private static Map<Arma,Integer> puntos; 
    
    /**
     * Pieza que realiza el Ataque.
     */
    private Pieza atacante;
    
    /**
     * Piezas que reciben el Ataque.
     */
    private Pieza[] destinatarios;
    
    /**
     * Arma utilizada para Atacar.
     */
    private Arma arma;

    /**
     * Construye el Escenario con los parámetros especificados.
     * 
     * @param atacante
     * @param destinatario
     * @param arma
     */
    public AtaqueSimple(Pieza atacante, Arma arma, Pieza... destinatarios) {

        super();
        
        this.atacante = atacante;
        this.destinatarios = destinatarios;
        this.arma = arma;
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario#ejecutar()
     */
    public void ejecutar() {

        /* ataca a todas las Piezas */
        for (Pieza destinatario : this.destinatarios) {
            
            if (destinatario != null) {
                
                /* modifica los puntos del destinatario de acuerdo al Arma */
                destinatario.modificarPuntos( (-1) * puntos.get(this.arma),  this);
                
            } else {
                
                // TODO Notificar que no se ataca a nadie
            }
        }
    }
    
    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Ataque#getArmas()
     */
    public Set<Arma> getArmas() {
        
        return EnumSet.of( this.arma );
    }
    
    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Ataque#getAtacantes()
     */
    public Pieza[] getAtacantes() {
        
        return  new Pieza[] { this.atacante };
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Ataque#getDestinatarios()
     */
    public Pieza[] getDestinatarios() {

        return this.destinatarios;
    }

    /**
     * 
     * @return puntos asignados a cada Arma en un AtaqueIndividual.
     */
    public static Map<Arma,Integer> getPuntos() {
        
        return AtaqueSimple.puntos;
    }
    
    
    /* Inicialización de los atributos estáticos */
    static {

        /* crea la configuración de puntos por Arma */
        puntos = Collections.synchronizedMap( new EnumMap<Arma,Integer>(Arma.class) );
        
        /* configura los valores por defecto para todas las Armas */
        for (Arma arma : Arma.values()) {
            
            puntos.put(arma, PUNTOS_POR_ARMA * (arma.ordinal() + 1) );
            
        }
    }


    public Set<Notificacion> getNotificaciones() {
       
        return NOTIFICACION;
    }
    
}
