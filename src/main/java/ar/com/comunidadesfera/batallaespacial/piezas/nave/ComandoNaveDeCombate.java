package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.piezas.ComandoAbstracto;

/**
 * Clase básica para ser extendida por todos los Comandos de NaveDeCombate.
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class ComandoNaveDeCombate extends ComandoAbstracto {

    private final ControlNaveDeCombate control;
    
    public ComandoNaveDeCombate(ControlNaveDeCombate control) {
        
        this.control = control;
    }
    
    /**
     * 
     * @return Nave sobre la que opera el Comando.
     */
    protected NaveDeCombate getNave() {
        
        return this.control.getNave();
    }

    
    public void ejecutar(Partida partida) {
        
        /* cuando se ejecuta un comnado para la Nave de Combate, 
         * las notificaciones son borradas */
        this.getNave().getNotificaciones().clear();
    }
    
    /**
     * 
     * @param partida : Partida para la cual se requiere un iterador.
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Tablero.Iterador getIterador(Partida partida) {
        
        /* se utiliza un sólo Iterador para el ControlAplicacion */
        Tablero.Iterador iterador = this.control.getIterador();
        
        if (iterador == null) {
            
            iterador = partida.getTablero().iterator();
            this.control.setIterador(iterador);
        }
        
        return iterador;
    }

}
