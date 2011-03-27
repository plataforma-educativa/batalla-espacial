package ar.com.comunidadesfera.eficiencia.instrumentos;

import ar.com.comunidadesfera.eficiencia.Ejecucion;

/**
 * Implementación básica del patrón Decorador (Decorator o Wrapper) para
 * el tipo Contador.
 * Constituye un punto de extensión para todo Decorador de Contador.
 *  
 * @author Mariano Tugnarelli
 *
 * @param <T> Tipo de Contador que decora.
 */
public abstract class ContadorDecorado<T extends Contador> implements Contador {

    private T contador;

    public ContadorDecorado(T contador) {
     
        this.contador = contador;
    }
    
    protected T getContador() {
        
        return this.contador;
    }

    public void incrementar() {
        
        this.getContador().incrementar();
    }

    public long getValor() {
        
        return this.getContador().getValor();
    }

    public Ejecucion getEjecucion() {
        
        return this.getContador().getEjecucion();
    }

    public void incrementar(long incremento) {
        
        this.getContador().incrementar(incremento);
    }

    public Contador getParcial(String discriminante) {
        
        return this.getContador().getParcial(discriminante);
    }

    public String getDiscriminante() {
        
        return this.getContador().getDiscriminante();
    }
}
