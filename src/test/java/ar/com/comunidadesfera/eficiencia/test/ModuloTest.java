package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Modulo.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.registros.Modulo;

public class ModuloTest {

    @Test
    public void verificarVersion() {
        
        Modulo modulo = new Modulo();
        modulo.setNombre(MULTIPLES_PASOS.nombre);
        modulo.setDescripcion(MULTIPLES_PASOS.descripcion);
        modulo.setEntorno(null);
        modulo.setVersion(MULTIPLES_PASOS.version);
        
        assertThat("version", modulo.getVersion(), is(MULTIPLES_PASOS.version));
        
        modulo.verificarVersion();
        
        assertThat("version", modulo.getVersion(), is(MULTIPLES_PASOS.version));

        final int[] versionesIncorrectas = {0, -1, -20000};
        
        for (int versionIncorrrecta : versionesIncorrectas) {
            
            modulo.setVersion(versionIncorrrecta);
            
            assertThat("version incorrecta", modulo.getVersion(), is(versionIncorrrecta));
            
            modulo.verificarVersion();
            
            assertThat("version luego de verificar", modulo.getVersion(), not(is(versionIncorrrecta)));
            
            assertThat("version luego de verificar", modulo.getVersion(), is(Modulo.VERSION_MINIMA));
          
        }
    }

    @Test
    public void getIndentificacion() {
        
        Modulo modulo = new Modulo();
        modulo.setNombre(MULTIPLES_PASOS.nombre);
        modulo.setDescripcion(MULTIPLES_PASOS.descripcion);
        modulo.setEntorno(null);
        modulo.setVersion(MULTIPLES_PASOS.version);

        /* por el momento la identificación es igual al nombre,
         * se verificará esta decisión de diseño a futuro */
        assertThat("identificacion", modulo.getIdentificacion(), is(MULTIPLES_PASOS.nombre));
        
        final String identificacion = "test:" + MULTIPLES_PASOS.nombre;
        
        modulo.setIdentificacion(identificacion);
        
        assertThat("identificacion", modulo.getIdentificacion(), is(identificacion));
    }
}
