package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Soldado;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Orden;

/**
 * Implementación de la perspectiva de Subalterno para un Soldado. 
 * Desarrollado como un 
 * 
 * @author Mariano
 *
 * @param <T> tipo del Subalterno
 * @param <S> tipo del Superior
 */
public class SoldadoSubalterno<T extends Soldado, S extends Soldado> 
                    implements Soldado, Subalterno<S> {

    private T soldado;
    
    private S superior;
    
    /**
     * Ordenes que restan impartir al subalterno.
     */
    private Queue<Orden> ordenes = new LinkedBlockingQueue<Orden>();

    
    public SoldadoSubalterno(T soldado, S superior) {
        
        this.soldado = soldado;
        this.superior = superior;
    }
    
    public Civilizacion getCivilizacion() {
        
        return this.getSoldado().getCivilizacion();
    }

    public String getNombre() {
        
        return this.getSoldado().getNombre();
    }

    public Queue<Orden> getOrdenes() {
        
        return this.ordenes;
    }

    /**
     * Devuelve la implementación concreta del subalterno.
     * @return
     */
    protected T getSoldado() {
        
        return this.soldado;
    }

    public S getSuperior() {

        return this.superior;
    }
}
