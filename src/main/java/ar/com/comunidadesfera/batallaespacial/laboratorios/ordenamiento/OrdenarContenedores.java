package ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.dependencias.Alternativa;
import ar.com.comunidadesfera.dependencias.Proveedor;

public class OrdenarContenedores {

    private Proveedor<OrdenadorDeContenedores> proveedor;
    
    public List<Contenedor> ejecutar(List<Contenedor> contenedores) {
        
        Contenedor[] arreglo = contenedores.toArray(new Contenedor[0]);
        
        this.proveedor.get().ordenar(arreglo);
        
        return Arrays.asList(arreglo);
    }
    
    @Inject
    protected void setImplementaciones(@Any Instance<OrdenadorDeContenedores> instancias,
                                       BeanManager beanManager) {
        
        this.proveedor = new Proveedor<OrdenadorDeContenedores>(beanManager,
                                                                OrdenadorDeContenedores.class, 
                                                                instancias);
    }
    
    public List<Alternativa<OrdenadorDeContenedores>> getAlternativas() {
        
        return new ArrayList<>(this.proveedor.getAlternativas());
    }
}
