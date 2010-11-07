package ar.com.comunidadesfera.batallaespacial.test.pieza;

import java.util.Set;
import java.util.TreeSet;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;

/**
 * Escenario utilizado en las pruebas unitarias.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class EscenarioSimulado implements Escenario {

    private Set<Notificacion> notificaciones;
    
    public EscenarioSimulado(Notificacion... notificaciones) {

        this.notificaciones = new TreeSet<Notificacion>();
        
        for (Notificacion notificacion : notificaciones) {
            
            this.notificaciones.add(notificacion);
        }
        
    }
    
    public void ejecutar() {


    }

    public Set<Notificacion> getNotificaciones() {
        
        return this.notificaciones;
    }

}
