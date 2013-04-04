
import javafx.application.Application;
import ar.com.comunidadesfera.plataformaeducativa.AplicacionBatallaEspacialInteractiva;

/**
 * Batalla Espacial en la Galaxia Andrómeda.
 * Construida en el paquete default para facilitar su utilización en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial {

    private static final String DESCRIPCION = "Batalla Espacial iniciada";
    
    public BatallaEspacial() {
     
        Thread thread = new Thread(new LanzarAplicacionBatallaEspacial());

        /* hack para que funcione el Interaction Pane de DrJava */
        thread.setContextClassLoader(this.getClass().getClassLoader());
        
        thread.start();
    }

    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return DESCRIPCION;
    }
    
    private static class LanzarAplicacionBatallaEspacial implements Runnable {
        
        @Override
        public void run() {
            
            Application.launch(AplicacionBatallaEspacialInteractiva.class);
            
        }
    }
}
