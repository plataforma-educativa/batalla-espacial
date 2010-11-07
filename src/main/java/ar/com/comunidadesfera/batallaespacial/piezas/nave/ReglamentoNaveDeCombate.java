package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.PilotoControlado;
import ar.com.comunidadesfera.batallaespacial.juego.Reglamento;

/**
 * Reglamento para las Naves de Combate 
 * 
 * @author mariano
 *
 */
public class ReglamentoNaveDeCombate extends Reglamento {
    
    public ReglamentoNaveDeCombate(Partida<?> partida) {
        
        super(partida);
    }

    
    protected void antesDeDestruir(Pieza pieza) {
        
        NaveDeCombate nave = (NaveDeCombate) pieza;

        /* notifica la destrucción */
        nave.getNotificaciones().offer(Notificacion.DESTRUCCION);

        /* va la oportunidad de una última turno */
        this.getPartida().getMotor().runTurno(nave.getPiloto());
        
        /* el Piloto deja de Jugar */
        if (nave.getPiloto() instanceof PilotoControlado) {
            
            ((PilotoControlado) nave.getPiloto()).setJugando(false);
        }
    }

}
