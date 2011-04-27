package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Casillero;
import ar.com.comunidadesfera.batallaespacial.ui.TableroLayout.Posicion;

/**
 * 
 * @author Mariano Tugnarelli
 *
 */
public class LabelPieza extends JLabel implements Pieza.Observador, Cloneable {

    /**
     * 
     */
    private static final long serialVersionUID = 4768603356238939630L;
    
    /**
     * Posición de la pieza en el layout del tablero.
     */
    private Posicion posicion;

    /**
     * Construye una pieza a partir de una imagen y la asocia a una posición 
     * dada del tablero.
     * 
     * @param imagen
     * @param posicion
     */
    public LabelPieza(Image imagen, Posicion posicion) {
        
        super(new ImageIcon(imagen));
        this.setPosicion(posicion);
    }
    
    protected LabelPieza(Icon icono) {
        super(icono);
        this.setPosicion(null);
    }

    /**
     * Cambia la posición de la pieza.
     * 
     */
    protected void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    
    /**
     * Devuelve la posición de la pieza
     */
    public Posicion getPosicion() {
        return this.posicion;
    }

    /**
     * @see Pieza.Observador#cambio(Pieza)
     */
    public void cambio(Pieza pieza) {
        
        Casillero casillero = pieza.getCasillero();
        
        if (casillero != null) {
            
            /* actualiza la posición */
            this.posicion.setColumna(casillero.getColumna());
            this.posicion.setFila(casillero.getFila());
            
        } else {
            
            /* la pieza fue sacada de la partida */
            this.posicion.anular();
        }
    }
    
    public LabelPieza clone() {
        
        return new LabelPieza(this.getIcon());
    }
}
 