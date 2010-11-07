package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.Notificacion;

/**
 * Un Escenario representa la interacción entre Piezas.
 * Mantiene la información del contexto del iteracción y
 * determina los efectos entre los participantes.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Escenario {

    /**
     * @pre ninguna.
     * @post realiza la interacción entre los particiapantes;
     *
     */
    void ejecutar();
    
    /**
     * @return Notificaciones vinculadas a la interacción.
     */
    Set<Notificacion> getNotificaciones();
}
