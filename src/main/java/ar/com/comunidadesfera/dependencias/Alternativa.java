package ar.com.comunidadesfera.dependencias;

import javax.enterprise.inject.spi.Bean;

/**
 * Una Alternativa describe una de las posibles implementaciones disponibles para la interfaz T.
 * Permite seleccionar dicha Alternativa en el contexto del Proveedor que la expuso.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public class Alternativa<T> {
    
    private Proveedor<T> proveedor;
    
    private Bean<?> bean;
    
    Alternativa(Proveedor<T> proveedor, Bean<?> bean) {
        
        this.bean = bean;
        this.proveedor = proveedor;
    }
    
    public Class<?> getImplementacion() {
        
        return this.bean.getBeanClass();
    }
    
    public void seleccionar() {
        
        this.proveedor.seleccionar(this);
    }
}