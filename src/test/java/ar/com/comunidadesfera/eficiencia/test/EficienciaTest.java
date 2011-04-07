package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.MULTIPLES_PASOS;
import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_10;
import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_20;

import java.util.Random;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Eficiencia;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;

public class EficienciaTest extends TestBasico {

    @Test
    public void getContextoCentral() {
        
        Contexto contexto = Eficiencia.getContexto();
        Assert.assertThat(contexto, Matchers.notNullValue());
        Assert.assertThat(contexto, Matchers.sameInstance(this.contexto));
    }
    
    @Test
    public void configurarContexto() {
        
        this.contexto.setPersistente(true);
        Assert.assertTrue("contexto persistente", 
                          this.contexto.isPersistente());
        this.contexto.setPersistente(false);
        Assert.assertFalse("contexto persistente", 
                           this.contexto.isPersistente());
    }
    
    @Test
    public void medirAlgoritmoSimpleConContador() {
        
        /* N : 10 */
        Ejecucion ejecucion10 = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                               SIMPLE_10.tamaño);
        
        Contador contador = ejecucion10.contarInstrucciones();
        
        String[] numeros = new String[(int)SIMPLE_10.tamaño[0]];
        for (int i = 0; i < numeros.length; i++) {
            
            numeros[i] = String.valueOf(i);
            contador.incrementar();
        }
        
        Assert.assertThat("valor del contador ", 
                          contador.getValor(), 
                          Matchers.is(SIMPLE_10.tamaño[0]));
        Assert.assertThat("ejecución del contador", 
                          contador.getEjecucion(), 
                          Matchers.sameInstance(ejecucion10));
        Assert.assertThat("algoritmo de la ejecucion", 
                          ejecucion10.getModulo().getNombre(),
                          Matchers.is(SIMPLE_10.modulo.nombre));
        Assert.assertThat(ejecucion10.contarInstrucciones(), 
                          Matchers.sameInstance(contador));
        
        ejecucion10.terminar();

        /* N : 20 */
        Ejecucion ejecucion20 = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre, 
                                                               SIMPLE_20.tamaño);
        contador = ejecucion20.contarInstrucciones();
        
        numeros = new String[(int)SIMPLE_20.tamaño[0]];
        for (int i = 0; i < numeros.length; i++) {
            
            numeros[i] = String.valueOf(i);
            contador.incrementar();
            
            Assert.assertThat("valor del contador ", 
                              contador.getValor(), 
                              Matchers.is(i + 1L));
        }
        
        
        Assert.assertThat("valor del contador ", 
                          contador.getValor(), 
                          Matchers.is(SIMPLE_20.tamaño[0]));
        Assert.assertThat("ejecución del contador", 
                          contador.getEjecucion(),
                          Matchers.sameInstance(ejecucion20));
        Assert.assertThat("algoritmo de la ejecucion",
                          ejecucion20.getModulo().getNombre(),
                          Matchers.is(SIMPLE_20.modulo.nombre));
        
        ejecucion20.terminar();

    }

    @Test
    public void medirAlgoritmoMultipleConContador() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre, 
                                                             MULTIPLES_PASOS.tamaño);
        
        Contador contador = ejecucion.contarInstrucciones();
        
        Random random = new Random();
        int[] valores = new int[(int) MULTIPLES_PASOS.tamaño[0]];
        for(int i = 0; i < valores.length; i++) {
            
            valores[i] = random.nextInt();
            
            /* considera la generación y la asignación como 2 operaciones */
            contador.incrementar(2);
        }
        
        Assert.assertThat("valor del contador ", 
                contador.getValor(), 
                Matchers.is(MULTIPLES_PASOS.tamaño[0] * 2));
        
        ejecucion.terminar();
    }
    
    @Test
    public void medirAlgoritmoMultipleConContadoresParciales() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre, 
                                                             MULTIPLES_PASOS.tamaño);
        
        Contador contador = ejecucion.contarInstrucciones();
        
        Contador contadorG = contador.getParcial("generaciones");
        Contador contadorI = contador.getParcial("incrementos");
        
        Random random = new Random();
        
        /* inicialización */
        contador.incrementar();
        int[] valores = new int[(int) MULTIPLES_PASOS.tamaño[0]];
        for(int i = 0; i < valores.length; i++) {
            
            /* cuenta el incremento */
            contadorI.incrementar();
            
            valores[i] = random.nextInt();
            
            /* cuenta la generación */
            contadorG.incrementar();
            
            /* cuenta general */
            contador.incrementar();
        }
        
        
        Assert.assertThat("contador total",
                          contador.getValor(),
                          Matchers.is(MULTIPLES_PASOS.tamaño[0] * 3 + 1));
        Assert.assertThat(contador.getDiscriminante(),
                          Matchers.is((Discriminante)ejecucion.getModulo()));
        Assert.assertThat("contador de asignaciones",
                          contadorG.getValor(),
                          Matchers.is(MULTIPLES_PASOS.tamaño[0]));
        Assert.assertThat(contadorG.getDiscriminante().getNombre(),
                          Matchers.is("generaciones"));
        Assert.assertThat("contador de asignaciones",
                          contadorI.getValor(),
                          Matchers.is(MULTIPLES_PASOS.tamaño[0]));
        Assert.assertThat(contadorI.getDiscriminante().getNombre(),
                          Matchers.is("incrementos"));
        
        ejecucion.terminar();
    }
}
