package ar.com.comunidadesfera.batallaespacial.interfaz;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
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
    
    private Controlador controlador;
    
    public PanelTablero() {
        super();
    }
    
    public void setControlador(Controlador controlador) {
        
        this.controlador = controlador;
    }
    
    public void setTablero(Tablero tablero) {
        
        this.tablero = tablero;
    }

    public void setDibujanteDePiezas(DibujanteDePiezas dibujanteDePiezas) {
        
        this.dibujanteDePiezas = dibujanteDePiezas;
    }

    public void disponerPiezas() {

        this.setVgap(3);
        this.setHgap(3);
        this.setGridLinesVisible(true);
        
        /* Recorre todas las Piezas que están ocupando una posición en el Tablero */
        for (Pieza pieza : this.tablero) {
                            
            this.agregar(pieza);
        }
        
        for (int columna = 0; columna <= this.tablero.getColumnas() + 2; columna++) {
            
            this.add(this.dibujanteDePiezas.dibujarMargen(), columna, 0);
            this.add(this.dibujanteDePiezas.dibujarMargen(), columna, this.tablero.getFilas() + 2);
        }
        
        for (int fila = 1; fila <= this.tablero.getFilas() + 1; fila++) {
            
            this.add(this.dibujanteDePiezas.dibujarMargen(), 0, fila);
            this.add(this.dibujanteDePiezas.dibujarMargen(), this.tablero.getColumnas() + 2, fila);
        }
        
    }
    

    private void agregar(Pieza pieza) {
        
        if (pieza != null) {
            
            /* dibuja la Pieza */
            Node dibujoPieza = this.dibujanteDePiezas.dibujar(pieza);
            
            PanelPieza panelPieza = new PanelPieza();
            panelPieza.setPieza(pieza);
            panelPieza.setDibujoPieza(dibujoPieza);
            panelPieza.setControlador(this.controlador);
            
            this.actualizar(panelPieza);

            /* hace tareas específicas para cada tipo de Pieza */
            pieza.recibir(this);
        }
    }

    public void actualizar(PanelPieza panel) {
        
        Pieza pieza = panel.getPieza();
        
        Tablero.Casillero casillero = pieza.getCasillero();
        
        /* en caso de estar ocupando otra posición */
        this.getChildren().remove(panel);

        if (casillero != null) {

            this.add(panel, casillero.getColumna() + 1, casillero.getFila() + 1); 

            GridPane.setValignment(panel, VPos.CENTER);
            GridPane.setHalignment(panel, HPos.CENTER);
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
