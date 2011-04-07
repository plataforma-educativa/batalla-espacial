package ar.com.comunidadesfera.persistencia.test;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.persistencia.Entidad;

/**
 * Pruebas unitarias sobre la interfaz protegida de la clase Entidad.
 * Esta interfaz expone funcionalidad para la extensión de la clase.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class EntidadExtendidaTest extends Entidad {

    private static final String NOMBRE_ATRIBUTO_1 = "nombre";
    private static final String VALOR_ATRIBUTO_1 = "Mariano";
    
    private static final String NOMBRE_ATRIBUTO_2 = "metodologia";
    private static final String VALOR_ATRIBUTO_2 = "agil";
    
    
    private boolean describirFueInvocado = false;
    
    @Override
    protected void describir(StringBuilder builder) {
        super.describir(builder);
        
        this.describirPropiedad(builder, NOMBRE_ATRIBUTO_1, VALOR_ATRIBUTO_1);
        this.describirPropiedad(builder, NOMBRE_ATRIBUTO_2, VALOR_ATRIBUTO_2);
        
        /* este método fue invocado */
        this.describirFueInvocado = true;
    }
    
    @Before
    public void inicializar() {
        this.setId(Datos.ENTIDAD_47253.id);
    }
    
    @Test
    public void testToString() { 

        Assert.assertFalse(this.describirFueInvocado);

        final String toString = this.toString();
        
        Assert.assertThat(toString, Matchers.containsString("id")); 
        Assert.assertThat(toString, Matchers.containsString(Datos.ENTIDAD_47253.id.toString())); 
        
        Assert.assertTrue(this.describirFueInvocado);
        Assert.assertThat(toString, Matchers.containsString(NOMBRE_ATRIBUTO_1));
        Assert.assertThat(toString, Matchers.containsString(VALOR_ATRIBUTO_1));
        Assert.assertThat(toString, Matchers.containsString(NOMBRE_ATRIBUTO_2));
        Assert.assertThat(toString, Matchers.containsString(VALOR_ATRIBUTO_2)); 
        
    }
    
    
}
