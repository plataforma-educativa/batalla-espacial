package ar.com.comunidadesfera.eficiencia.test;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class MedicionTest extends TestBasico {

    @Test
    public void medicion() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(Datos.Ejecucion.MULTIPLES_PASOS.modulo.nombre, 
                                                             Datos.Ejecucion.MULTIPLES_PASOS.tamaño);
        final String discriminante = "pares";
        
        Contador contadorTotal = ejecucion.contarInstrucciones();
        Contador contadorParcial = contadorTotal.getParcial(discriminante);
        
        long cuentaTotal = 0;
        long cuentaParcial = 0;
        
        for (int i = 0; i < Datos.Ejecucion.MULTIPLES_PASOS.tamaño[0]; i++) {
            
            contadorTotal.incrementar();
            cuentaTotal+=1;
            
            if (i % 2 == 0) {
                
                contadorParcial.incrementar();
                cuentaTotal+=1;
                cuentaParcial+=1;
            }
        }
        
        Medicion medicionTotal = contadorTotal.getMedicion();
        
        Assert.assertThat("medicionTotal", medicionTotal, 
                          Matchers.notNullValue());
        Assert.assertThat("medicionTotal", medicionTotal, 
                          this.medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
        Assert.assertThat("resultado de la medicionTotal", medicionTotal.getResultado(),
                          this.medidaCon(cuentaTotal, Unidad.INSTRUCCIONES));
        
        Medicion medicionParcial = contadorParcial.getMedicion();
        
        Assert.assertThat("medicionParcial", medicionParcial,
                          Matchers.notNullValue());
        Assert.assertThat("medicionParcial", medicionParcial,
                          this.medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
        Assert.assertThat("resultado de la medicionParcial", medicionParcial.getResultado(),
                          this.medidaCon(cuentaParcial, Unidad.INSTRUCCIONES));
        
        Discriminante discriminanteTotal = medicionTotal.getDiscriminante();
        
        Assert.assertThat("discriminanteTotal", discriminanteTotal,
                          this.discriminanteCon(medicionTotal.getModulo().getNombre(), null));
        Assert.assertThat(discriminanteTotal, Matchers.instanceOf(Modulo.class));
        Assert.assertThat(discriminanteTotal, Matchers.is((Discriminante) medicionTotal.getModulo()));
        
        Discriminante discriminanteParcial = medicionParcial.getDiscriminante();
        
        Assert.assertThat("discriminanteParcial", discriminanteParcial,
                          this.discriminanteCon(discriminante, discriminanteTotal));        
        
    }
}

