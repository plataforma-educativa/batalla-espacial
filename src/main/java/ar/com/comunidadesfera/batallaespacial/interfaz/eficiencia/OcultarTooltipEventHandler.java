package ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class OcultarTooltipEventHandler implements EventHandler<MouseEvent> {

    private Tooltip tooltip;
    
    public OcultarTooltipEventHandler(Tooltip tooltip) {
    
        this.tooltip = tooltip;
    }

    @Override
    public void handle(MouseEvent event) {
        
        this.tooltip.hide();
    }
}
