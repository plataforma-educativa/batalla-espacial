package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;

import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class MedicionTest extends TestBasico {

    @Test
    public void obtenerMedicion() {
        
        Date antes = this.registrarTiempo();
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre,
                                                             MULTIPLES_PASOS.problema.nombre,
                                                             MULTIPLES_PASOS.dimension);
        final String discriminante = "pares";
        
        Contador contadorTotal = ejecucion.contarInstrucciones();
        Contador contadorParcial = contadorTotal.getParcial(discriminante);
        
        long cuentaTotal = 0;
        long cuentaParcial = 0;
        
        for (int i = 0; i < MULTIPLES_PASOS.dimension; i++) {
            
            contadorTotal.incrementar();
            cuentaTotal+=1;
            
            if (i % 2 == 0) {
                
                contadorParcial.incrementar();
                cuentaTotal+=1;
                cuentaParcial+=1;
            }
            
            this.esperar(5);
        }
        
        ejecucion.terminar();
        
        Date despues = this.registrarTiempo();
        
        Medicion medicionTotal = contadorTotal.getMedicion();
        
        assertThat("medicionTotal", medicionTotal, notNullValue());
        assertThat("medicionTotal", medicionTotal, medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
        assertThat("inicio", medicionTotal.getInicio(), greaterThanOrEqualTo(antes));
        assertThat("fin", medicionTotal.getFin(), lessThanOrEqualTo(despues));
        assertThat("resultado", medicionTotal.getResultado(), medidaCon(cuentaTotal, Unidad.INSTRUCCIONES));
        
        Medicion medicionParcial = contadorParcial.getMedicion();
        
        assertThat("medicionParcial", medicionParcial, notNullValue());
        assertThat("medicionParcial", medicionParcial, medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
        assertThat("inicio", medicionParcial.getInicio(), greaterThanOrEqualTo(antes));
        assertThat("fin", medicionParcial.getFin(), lessThanOrEqualTo(despues));
        assertThat("resultado", medicionParcial.getResultado(), medidaCon(cuentaParcial, Unidad.INSTRUCCIONES));
        
        Discriminante discriminanteTotal = medicionTotal.getDiscriminante();
        
        assertThat("discriminanteTotal", discriminanteTotal, discriminanteCon(medicionTotal.getModulo().getNombre(), null));
        assertThat(discriminanteTotal, instanceOf(Modulo.class));
        assertThat(discriminanteTotal, is((Discriminante) medicionTotal.getModulo()));
        
        Discriminante discriminanteParcial = medicionParcial.getDiscriminante();
        
        assertThat("discriminanteParcial", discriminanteParcial, discriminanteCon(discriminante, discriminanteTotal));
        
    }

    
    @Test
    public void getModuloNulo() {
        
        Medicion medicion = new Medicion();
        
        assertThat("modulo", medicion.getModulo(), nullValue());
    }
    
    @Test
    public void getProblemaNulo() {
        
        Medicion medicion = new Medicion();

        assertThat("problema", medicion.getProblema(), nullValue());
    }
    
    @Test
    public void getInicioNulo() {
        
        Medicion medicion = new Medicion();
        
        assertThat("inicio", medicion.getInicio(), nullValue());
    }
    
    @Test
    public void getFinNulo() {
        
        Medicion medicion = new Medicion();
        
        assertThat("fin", medicion.getFin(), nullValue());
    }
}

