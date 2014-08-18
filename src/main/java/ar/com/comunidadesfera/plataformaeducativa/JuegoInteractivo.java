package ar.com.comunidadesfera.plataformaeducativa;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import ar.com.comunidadesfera.fx.Aplicacion;

public abstract class JuegoInteractivo {

    private static AtomicBoolean iniciado = new AtomicBoolean(false);
    
    public JuegoInteractivo(Class<? extends Aplicacion> claseAplicacion) {

        if (! iniciado.getAndSet(true)) {

            EjecucionInteractiva ejecucion = EjecucionInteractiva.instancia();
            ejecucion.setClase(claseAplicacion);

            Thread thread = new Thread(EjecucionInteractiva.instancia());
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
        
        while (EjecucionInteractiva.instancia().estaCargando(1, TimeUnit.SECONDS)) {
            
            System.out.print(".");
        }
        
        System.out.print(": ");
    }
    
}
