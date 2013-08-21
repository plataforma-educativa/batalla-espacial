package ar.com.comunidadesfera.eficiencia.test.persistencia;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;

/**
 * Pruebas unitarias sobre los métodos del AdministradorDeMediciones 
 * para asegurar que aquellos que ejecutan named queries proveen los parámetros
 * correctamente.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class AdministradorDeMedicionesTest extends TestDePersistencia {

    private AdministradorDeMediciones administrador;
    
    @Before
    public void inicializar() {
        
        this.administrador = new AdministradorDeMediciones();
        this.administrador.setEntityManager(this.em);
    }

    @Test
    public void buscarMediciones() {
        
        this.administrador.buscarMediciones(moduloConId(1L));
    }
    
    @Test
    public void sumarizarMedicionesPorDiscriminante() {
        
        this.administrador.sumarizarMedicionesPorDiscriminante(categoriaConId(1L));
    }
    
    @Test
    public void promediarMedicionesPorDiscriminanteDimension() {
        
        this.administrador.promediarMedicionesPorDiscriminanteDimension(categoriaConId(3L));
    }

    @Test
    public void buscarModulos() {
        
        this.administrador.buscarModulos("busqueda");
    }
    
}
