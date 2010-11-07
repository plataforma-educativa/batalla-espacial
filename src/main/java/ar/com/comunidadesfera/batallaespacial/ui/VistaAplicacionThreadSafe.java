package ar.com.comunidadesfera.batallaespacial.ui;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.SwingUtilities;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;


/**
 * Proxy de la Vista que asegura que la actualización de los atributos
 * de la vista son thread safe. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public class VistaAplicacionThreadSafe implements VistaAplicacion, Runnable {

    /**
     * Implementación concreta 
     */
    private VistaAplicacion impl;
    
    /**
     * Valores a actualizar
     * 
     */
    private AtomicLong ronda = new AtomicLong();
    private AtomicBoolean visible = new AtomicBoolean();
    

    /**
     * Crea el proxy sobre la Vista de la Aplicación para hacerla thread-safe.
     * 
     * @param vistaAplicacion
     */
    public VistaAplicacionThreadSafe(VistaAplicacion vistaAplicacion) {
        
        super();
        this.impl = vistaAplicacion;
    }

    public void setRonda(long ronda) {

        this.ronda.set(ronda);
        SwingUtilities.invokeLater(this);
    }

    public void setPanelTablero(PanelTablero panelTablero) {
        
        this.impl.setPanelTablero(panelTablero);
    }

    public void registrarObservador(Observador observador) {

        this.impl.registrarObservador(observador);
    }

    public void setVisible(boolean visible) {

        this.visible.set(visible);
        SwingUtilities.invokeLater(this);
    }

    public void run() {

        this.impl.setRonda(this.ronda.get());
        // TODO evaluar el impacto de remover setVisible para evitar que tome
        // la aplicación.
        //this.impl.setVisible(this.visible.get());
        this.impl.revalidate();
    }

    public void revalidate() {

        this.impl.revalidate();
    }

    public void addInforme(Pieza pieza, String[] properties) {

        SwingUtilities.invokeLater(new AddInforme(pieza, properties));
    }
    

    private class AddInforme implements Runnable {

        private Pieza pieza;
        private String[] properties;
        
        public AddInforme(Pieza pieza, String[] properties) {
            
            this.pieza = pieza;
            this.properties = properties;
        }
        
        public void run() {

            VistaAplicacionThreadSafe.this.impl.addInforme(this.pieza, this.properties);
        }
        
        
    }
}
