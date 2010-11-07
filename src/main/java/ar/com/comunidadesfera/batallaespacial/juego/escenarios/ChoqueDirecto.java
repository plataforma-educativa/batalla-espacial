package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

/**
 * ChoqueDirecto entre Piezas, que produce daños equitativos
 * entre los participantes. 
 *
 */
public class ChoqueDirecto implements Choque {

    /**
     * Puntos por defecto de un choque.
     */
    public static final Integer PUNTOS_CHOQUE = 5;  
    
    /**
     * Factor utilizado para calcular la magnitud del 
     * choque entre Piezas.
     */
    private static AtomicInteger puntos = new AtomicInteger(PUNTOS_CHOQUE);
    
    /**
     * Piezas que participan en el Choque.
     */
    private Pieza[] piezas;
    
    /**
     * Construye un ChoqueDirecto entre las Piezas <code>piezas</code>.
     */
    public ChoqueDirecto(Pieza... piezas) {
        
        super();
        this.piezas = piezas;
    }

    /** 
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario#ejecutar()
     */
    public void ejecutar() {

        for (Pieza pieza : this.piezas) {
            
            /* modifica los puntos de todas las Piezas participantes */
            pieza.modificarPuntos((-1) * ChoqueDirecto.getPuntos(), this);
        }
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Choque#getPiezas()
     */
    public Pieza[] getPiezas() {
        
        return this.piezas;
    }

    /**
     * 
     * @return cantidad de puntos asignados al ChoqueDirecto.
     */
    public static int getPuntos() {

        return ChoqueDirecto.puntos.get();
    }

    /**
     * @pre ninguna.
     * @post cambia la cantidad de puntos asignados al ChoqueDirecto.
     * 
     */
    public static void setPuntos(int puntos) {
        
        ChoqueDirecto.puntos.set(puntos);
    }

    public Set<Notificacion> getNotificaciones() {

        return NOTIFICACION;
    }

}
