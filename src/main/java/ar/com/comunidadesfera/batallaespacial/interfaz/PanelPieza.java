package ar.com.comunidadesfera.batallaespacial.interfaz;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class PanelPieza extends Group implements Pieza.Observador {

    private PanelTablero panelTablero;
    
    private Pieza pieza;
    
    public PanelPieza(Pieza pieza, Node dibujoPieza) {
        
        super();
        this.pieza = pieza;
        
        this.getChildren().add(dibujoPieza);
        this.pieza.agregarObservador(this);
    }

    @Override
    public void cambio(Pieza pieza) {
        
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                
                panelTablero.actualizar(PanelPieza.this);
                
            }
        });
        
    }

    public Pieza getPieza() {

        return this.pieza;
    }

    public void setPanelTablero(PanelTablero panelTablero) {

        this.panelTablero = panelTablero;
    }
}
