package ar.com.comunidadesfera.persistencia.test;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.persistencia.Entidad;

/**
 * Pruebas unitarias sobre la interfaz pública de la clase Entidad.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class EntidadTest {
    
    private Entidad entidad1;
    
    private Entidad entidad2;
    
    private Entidad entidadNula;
    
    private Entidad entidadIgualEntidadNula;
    
    private Entidad referenciaEntidadNula;
    
    private Entidad referenciaEntidad1;
    
    private Entidad entidadIgualEntidad1;
    
    @Before
    public void crearEntidades() {
        
        this.entidad1 = this.crearEntidad(Datos.ENTIDAD_1);
        this.entidad2 = this.crearEntidad(Datos.ENTIDAD_2);
        this.entidadNula = this.crearEntidad(Datos.ENTIDAD_NULL);
        this.entidadIgualEntidad1 = this.crearEntidad(Datos.ENTIDAD_1);
        this.referenciaEntidad1 = this.entidad1;
        this.referenciaEntidadNula = this.entidadNula;
        this.entidadIgualEntidadNula = this.crearEntidad(Datos.ENTIDAD_NULL);
    }
    
    protected Entidad crearEntidad(Datos datos) {
        
        Entidad entidad = new EntidadSimulada();
        entidad.setId(datos.id);
        return entidad;
    }
    
    @Test
    public void getId() {
        
        Assert.assertThat(this.entidad1.getId(), Matchers.is(Datos.ENTIDAD_1.id));
        Assert.assertThat(this.entidad2.getId(), Matchers.is(Datos.ENTIDAD_2.id));
        Assert.assertThat(this.entidadNula.getId(), Matchers.nullValue());
        Assert.assertThat(this.entidadIgualEntidad1.getId(), Matchers.is(Datos.ENTIDAD_1.id));
        Assert.assertThat(this.entidadIgualEntidadNula.getId(), Matchers.nullValue());
        
        Assert.assertThat(this.entidad1, Matchers.sameInstance(this.referenciaEntidad1));
        Assert.assertThat(this.entidadNula, Matchers.sameInstance(this.referenciaEntidadNula));
        
    }
    
    @Test
    public void testEquals() {
        
        Assert.assertThat(this.entidad1, Matchers.equalTo(this.entidadIgualEntidad1));
        Assert.assertThat(this.entidadIgualEntidad1, Matchers.equalTo(this.entidad1));
        
        Assert.assertThat(this.entidad1, Matchers.not(Matchers.equalTo(this.entidad2)));
        Assert.assertThat(this.entidad2, Matchers.not(Matchers.equalTo(this.entidad1)));
        Assert.assertThat(this.entidad1, Matchers.not(Matchers.equalTo(this.entidadNula)));
        Assert.assertThat(this.entidadNula, Matchers.not(Matchers.equalTo(this.entidad1)));
        
        Assert.assertThat(this.entidadNula, Matchers.equalTo(this.referenciaEntidadNula));
        Assert.assertThat(this.entidadNula, Matchers.not(Matchers.equalTo(this.entidadIgualEntidadNula)));
        Assert.assertThat(this.entidadIgualEntidadNula, Matchers.not(Matchers.equalTo(this.entidadNula)));
    }
    
    @Test
    public void testHashCode() {
        
        Assert.assertThat(this.entidad1.hashCode(), Matchers.equalTo(this.entidadIgualEntidad1.hashCode()));
        Assert.assertThat(this.entidadIgualEntidad1.hashCode(), Matchers.equalTo(this.entidad1.hashCode()));
        
        Assert.assertThat(this.entidad1.hashCode(), Matchers.not(Matchers.equalTo(this.entidad2.hashCode())));
        Assert.assertThat(this.entidad2.hashCode(), Matchers.not(Matchers.equalTo(this.entidad1.hashCode())));
        Assert.assertThat(this.entidad1.hashCode(), Matchers.not(Matchers.equalTo(this.entidadNula.hashCode())));
        Assert.assertThat(this.entidadNula.hashCode(), Matchers.not(Matchers.equalTo(this.entidad1.hashCode())));
        
        Assert.assertThat(this.entidadNula.hashCode(), Matchers.equalTo(this.referenciaEntidadNula.hashCode()));
        Assert.assertThat(this.entidadNula.hashCode(), Matchers.not(Matchers.equalTo(this.entidadIgualEntidadNula.hashCode())));
        Assert.assertThat(this.entidadIgualEntidadNula.hashCode(), Matchers.not(Matchers.equalTo(this.entidadNula.hashCode())));
    }
}
