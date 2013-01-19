package ar.com.comunidadesfera.batallaespacial.interfaz;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class PanelTablero extends GridPane implements VisitanteDePiezas {

    private Tablero tablero;
    
    private DibujanteDePiezas dibujanteDePiezas;
    
    public PanelTablero(Tablero tablero, DibujanteDePiezas dibujanteDePiezas) {
        super();
        
        this.tablero = tablero;
        this.dibujanteDePiezas = dibujanteDePiezas;
        
        this.disponerPiezas();
    }

    private void disponerPiezas() {

        this.setVgap(3);
        this.setHgap(3);
        
        /* Recorre todas las Piezas que están ocupando una posición en el Tablero */
        for (Pieza pieza : this.tablero) {
                            
            this.agregar(pieza);
        }
    }

    private void agregar(Pieza pieza) {
        
        if (pieza != null) {
            
            /* dibuja la Pieza */
            Node nodoPieza = this.dibujanteDePiezas.dibujar(pieza);
            
            this.actualizar(new PanelPieza(pieza, nodoPieza));

            /* hace tareas específicas para cada tipo de Pieza */
            pieza.recibir(this);
        }
    }

    public void actualizar(PanelPieza panel) {
        
        Pieza pieza = panel.getPieza();
        
        Tablero.Casillero casillero = pieza.getCasillero();
        
        this.getChildren().remove(panel);

        if (casillero != null) {

            /* en caso de estar ocupando otra posición */
            this.add(panel, casillero.getColumna(), casillero.getFila());
        }
        
        panel.setPanelTablero(this);
    }
    
    @Override
    public void visitar(Nave nave) {

    }

    @Override
    public void visitar(BaseEspacial base) {
        
        for (Nave nave : base.getNaves()) {
            
            this.agregar(nave);
        }
    }

    @Override
    public void visitar(Asteroide asteroide) {
        
    }

    @Override
    public void visitar(Contenedor contenedor) {
        
    }
}
