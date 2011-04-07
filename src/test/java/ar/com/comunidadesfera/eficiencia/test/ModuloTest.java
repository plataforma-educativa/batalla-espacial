package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Modulo.MULTIPLES_PASOS;

import org.hamcrest.Matchers;
import org.junit.Assert;
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
        
        Assert.assertThat("version", modulo.getVersion(),
                          Matchers.is(MULTIPLES_PASOS.version));
        
        modulo.verificarVersion();
        
        Assert.assertThat("version", modulo.getVersion(),
                          Matchers.is(MULTIPLES_PASOS.version));

        final int[] versionesIncorrectas = {0, -1, -20000};
        
        for (int versionIncorrrecta : versionesIncorrectas) {
            
            modulo.setVersion(versionIncorrrecta);
            
            Assert.assertThat("version incorrecta", modulo.getVersion(),
                              Matchers.is(versionIncorrrecta));
            
            modulo.verificarVersion();
            
            Assert.assertThat("version luego de verificar", 
                              modulo.getVersion(),
                              Matchers.not(Matchers.is(versionIncorrrecta)));
            
            Assert.assertThat("version luego de verificar", 
                              modulo.getVersion(),
                              Matchers.is(Modulo.VERSION_MINIMA));
          
        }
    }
}
