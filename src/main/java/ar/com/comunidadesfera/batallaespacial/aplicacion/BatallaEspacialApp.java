package ar.com.comunidadesfera.batallaespacial.aplicacion;

import ar.com.comunidadesfera.batallaespacial.ui.FrameAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacionThreadSafe;

public class BatallaEspacialApp {

    private ControlAplicacion control;
    private VistaAplicacion vista;
    
    public BatallaEspacialApp() {
        
        super();
        this.vista = new VistaAplicacionThreadSafe(new FrameAplicacion());
        this.control = new ControlAplicacion(this.vista);
    }

    public void run() {

        /* hace visible el frame */
        this.vista.setVisible(true);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
    
        BatallaEspacialApp app = new BatallaEspacialApp();
        
        app.run();
    }

}
