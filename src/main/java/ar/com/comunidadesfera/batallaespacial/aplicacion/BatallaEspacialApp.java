package ar.com.comunidadesfera.batallaespacial.aplicacion;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;

/**
 * Punto de entrada de la aplicación.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacialApp {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
    
        BatallaEspacial batallaEspacial = new BatallaEspacialBasica();
        
        batallaEspacial.iniciar();
    }

}
