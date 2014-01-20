package ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class MostrarTooltipEventHandler implements EventHandler<MouseEvent> {

    private Tooltip tooltip;
    
    private Node node;
    
    public MostrarTooltipEventHandler(Tooltip tooltip, Node node) {
        
        this.tooltip = tooltip;
        this.node = node;
    }

    @Override
    public void handle(MouseEvent event) {

        this.tooltip.show(this.node, event.getScreenX() + 2, event.getScreenY() + 2);
    }
}
