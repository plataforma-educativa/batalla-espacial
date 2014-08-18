package ar.com.comunidadesfera.plataformaeducativa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import ar.com.comunidadesfera.fx.Aplicacion;

/**
 * Runnable que lanza la Aplicación Batalla Espacial Interactiva.
 * 
 */
public class EjecucionInteractiva implements Runnable {

    /**
     * Única instancia de Ejecución de la Aplicación Batalla Espacial Interactiva 
     */
    private final static EjecucionInteractiva instancia = new EjecucionInteractiva();
    
    private Class<? extends Aplicacion> clase = AplicacionBatallaEspacialInteractiva.class;
    
    private final CountDownLatch cargada;
    
    private EjecucionInteractiva() {

        this.cargada = new CountDownLatch(1);
    }
    
    void cargaTerminada() {
        
        this.cargada.countDown();
    }
    
    public void setClase(Class<? extends Aplicacion> clase) {
        
        this.clase = clase;
    }
    
    /**
     * Indica si la Batalla Espacial Interactiva terminó de cargar y está lista
     * para recibir interacciones, esperándo a lo sumo el tiempo especificado por 'espera' y 'unidad'.
     * 
     * @param espera
     * @param unidad
     * @return
     */
    public boolean estaCargando(long espera, TimeUnit unidad) {
        
        boolean cargando = true;
        
        try {
            
            cargando = ! this.cargada.await(espera, unidad);
            
        } catch (InterruptedException ignorada) {
            
            /* si se interrumpe la ejecución, asume que la carga terminó */
        }
        
        return cargando;
    }
    
    @Override
    public void run() {

        Application.launch(this.clase);
    }
    
    public static EjecucionInteractiva instancia() {
        
        return instancia;
    }
}