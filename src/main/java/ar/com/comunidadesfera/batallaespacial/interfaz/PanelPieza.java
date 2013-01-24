package ar.com.comunidadesfera.batallaespacial.interfaz;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class PanelPieza extends Group implements Vista, Pieza.Observador {

    private PanelTablero panelTablero;
    
    private Pieza pieza;
    
    private Controlador controlador;
    
    private final Runnable actualizarPosicion = new Runnable() {
        
        @Override
        public void run() {
            
            panelTablero.actualizar(PanelPieza.this);
            
        }
    };

    private final EventHandler<MouseEvent> mostrarDetalle = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent evento) {
            
            controlador.seleccionar(pieza, PanelPieza.this);
        }
        
    };
    
    public PanelPieza() {
        
        super();
        this.setOnMousePressed(this.mostrarDetalle);
        this.setCursor(Cursor.HAND);
    }

    public void setPieza(Pieza pieza) {
        
        this.pieza = pieza;
        this.pieza.agregarObservador(this);
    }
    
    public void setDibujoPieza(Node dibujoPieza) {
        
        this.getChildren().clear();
        this.getChildren().add(dibujoPieza);
    }
    
    public void setControlador(Controlador controlador) {
        
        this.controlador = controlador;
    }
    
    @Override
    public void cambio(Pieza pieza) {
        
        Platform.runLater(actualizarPosicion);
    }

    public Pieza getPieza() {

        return this.pieza;
    }

    public void setPanelTablero(PanelTablero panelTablero) {

        this.panelTablero = panelTablero;
    }
}
