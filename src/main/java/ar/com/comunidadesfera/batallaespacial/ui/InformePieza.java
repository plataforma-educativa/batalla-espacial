package ar.com.comunidadesfera.batallaespacial.ui;

import javax.swing.SwingUtilities;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza.Observador;

/**
 * Informe de una Pieza.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class InformePieza extends Informe implements Observador, Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = 712877986731817142L;

    public InformePieza(Pieza pieza) {
        
        super(pieza);
        
        /* se registra como Observador de la Pieza*/
        pieza.agregarObservador(this);
    }

    public void cambio(Pieza pieza) {

        SwingUtilities.invokeLater(this);
    }

    public void run() {
        
        this.fireTableRowsUpdated(0,this.getRowCount() - 1);
    }

}
