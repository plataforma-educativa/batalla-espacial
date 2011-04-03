package ar.com.comunidadesfera.eficiencia.ejecuciones;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;

/**
 * Implementación básica del patrón Decorador (Decorator o Wrapper) para
 * el tipo Ejecucion.
 * Constituye un punto de extensión para todo Decorador de Ejecucion.
 *  
 * @author Mariano Tugnarelli
 *
 * @param <T> Tipo de Ejecucion que decora.
 */
public abstract class EjecucionDecorada<T extends Ejecucion> implements Ejecucion {

    private T ejecucion;
    
    public EjecucionDecorada(T ejecucion) {
        
        this.ejecucion = ejecucion;
    }
    
    @Override
    public Contador contarInstrucciones() {

        return this.getEjecucion().contarInstrucciones();
    }

    @Override
    public Modulo getModulo() {

        return this.getEjecucion().getModulo();
    }

    protected T getEjecucion() {
        
        return this.ejecucion;
    }
    
}
