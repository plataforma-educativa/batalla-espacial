
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private static AtomicBoolean iniciada = new AtomicBoolean(false);
    
    public BatallaEspacial() {
        
        if (! iniciada.getAndSet(true)) {
            
            Thread thread = new Thread(AplicacionBatallaEspacialInteractiva.EJECUCION);
            /* hack para que funcione el Interaction Pane de DrJava */
            thread.setContextClassLoader(this.getClass().getClassLoader());
            thread.start();

            this.esperarCarga();
            
        } else {

            System.out.println("Iniciada previamente");
        }
    }

    private void esperarCarga() {
        
        System.out.print("Cargando");
        
        while (AplicacionBatallaEspacialInteractiva.EJECUCION.estaCargando(1, TimeUnit.SECONDS)) {
            
            System.out.print(".");
        }
        
        System.out.println();
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return "Batalla Espacial";
    }
}
