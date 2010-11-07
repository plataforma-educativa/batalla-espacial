package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import java.util.concurrent.Semaphore;

import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Orden que devuelve un resultado luego de su ejecución.
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class OrdenSincronizada<T> implements Orden {

    protected PilotoHumano piloto;
    
    protected Semaphore semaforo;

    public OrdenSincronizada() {

        this.semaforo = new Semaphore(0);
    }
    
    /**
     * @nota Template Method.
     */
    public final Comando ejecutar(PilotoHumano piloto) {
    
        this.piloto = piloto;
    
        this.ejecutar();
        
        /* indica que se ha ejecutado el comando */
        this.semaforo.release();
        
        return this.getProximoComando();
    }

    /**
     * @post realiza la ejecución de la Orden.
     * @nota punto de extensión.
     */
    protected void ejecutar() {
        
    }
    
    /**
     * @post devuelve el próximo Comando a ejecutar.
     * @nota punto de extensión. 
     */
    protected Comando getProximoComando() {
        
        return null;
    }
    
    /**
     * @post devuelve el resultado de haber ejecutado la Orden.
     */
    public final T getResultado() {
        
        try {
            
            /* espera hasta obtener el reporte */
            this.semaforo.acquire();
            
            return this.getResultado(this.piloto);

        } catch (InterruptedException ie) {
            
            throw new RuntimeException("Orden interrumpida", ie);
            
        } finally {
            
            this.semaforo.release();
        }
    }
    
    /**
     * @post devuelve el resultado de la ejecución del comando. 
     */
    protected abstract T getResultado(PilotoHumano piloto);
}
