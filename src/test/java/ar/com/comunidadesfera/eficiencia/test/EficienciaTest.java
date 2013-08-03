package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Eficiencia;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.RegistroDeEjecucion;

public class EficienciaTest extends TestBasico {

    @Test
    public void getContextoCentral() {
        
        Contexto contexto = Eficiencia.getContexto();
        assertThat(contexto, notNullValue());
        assertThat(contexto, sameInstance(this.contexto));
    }
    
    @Test
    public void configurarContexto() {
        
        this.contexto.setPersistente(true);
        assertThat("contexto persistente", this.contexto.isPersistente(), is(true));
        this.contexto.setPersistente(false);
        assertThat("contexto persistente", this.contexto.isPersistente(), is(false));
    }

    @Test
    public void iniciarEjecucion() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                             SIMPLE_10.problema.nombre, 
                                                             SIMPLE_10.dimension);
        
        assertThat("modulo", ejecucion.getModulo().getNombre(), is(SIMPLE_10.modulo.nombre));
        assertThat("problema", ejecucion.getProblema().getNombre(), is(SIMPLE_10.problema.nombre));
        assertThat("dimensión", ejecucion.getDimension(), is(SIMPLE_10.dimension));
    }
    
    @Test
    public void obtenerRegistroDeEjecucion() {
        
        long antes = System.nanoTime();
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                             SIMPLE_10.problema.nombre,
                                                             SIMPLE_10.dimension); 

        ejecucion.terminar();
        
        long despues = System.nanoTime();
        
        RegistroDeEjecucion registro = ejecucion.getRegistro();
        
        assertThat("registro", registro, notNullValue());
        assertThat("problema del registro", registro.getProblema(), sameInstance(ejecucion.getProblema()));
        assertThat("modulo del registro", registro.getModulo(), sameInstance(ejecucion.getModulo()));
        assertThat("inicio del registro", registro.getInicio().getTime(), lessThanOrEqualTo(antes));
        assertThat("fin del registro", registro.getFin().getTime(), lessThanOrEqualTo(despues));
    }
    
    @Test
    public void medirAlgoritmoSimple10ConContador() {
        
        Ejecucion ejecucion10 = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre,
                                                               SIMPLE_10.problema.nombre,
                                                               SIMPLE_10.dimension);
        Contador contador = ejecucion10.contarInstrucciones();
        
        String[] numeros = new String[(int)SIMPLE_10.dimension];
        for (int i = 0; i < numeros.length; i++) {
            
            numeros[i] = String.valueOf(i);
            contador.incrementar();
        }
        
        assertThat("valor del contador ", contador.getValor(), is(SIMPLE_10.dimension));
        assertThat("ejecución del contador", contador.getEjecucion(), sameInstance(ejecucion10));
        assertThat("algoritmo de la ejecucion", ejecucion10.getModulo().getNombre(), is(SIMPLE_10.modulo.nombre));
        assertThat(ejecucion10.contarInstrucciones(), sameInstance(contador));
        
        ejecucion10.terminar();
    }
    
    @Test
    public void medirAlgoritmoSimple20ConContador() {
        
        Ejecucion ejecucion20 = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                               SIMPLE_20.problema.nombre,
                                                               SIMPLE_20.dimension);
        Contador contador = ejecucion20.contarInstrucciones();
        
        String[] numeros = new String[(int)SIMPLE_20.dimension];
        for (int i = 0; i < numeros.length; i++) {
            
            numeros[i] = String.valueOf(i);
            contador.incrementar();
            
            assertThat("valor del contador ", contador.getValor(), is(i + 1L));
        }
        
        
        assertThat("valor del contador ", contador.getValor(), is(SIMPLE_20.dimension));
        assertThat("ejecución del contador", contador.getEjecucion(), sameInstance(ejecucion20));
        assertThat("algoritmo de la ejecucion", ejecucion20.getModulo().getNombre(), is(SIMPLE_20.modulo.nombre));
        
        ejecucion20.terminar();

    }

    @Test
    public void medirAlgoritmoMultipleConContador() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre,
                                                             MULTIPLES_PASOS.problema.nombre,
                                                             MULTIPLES_PASOS.dimension);
        
        Contador contador = ejecucion.contarInstrucciones();
        
        Random random = new Random();
        int[] valores = new int[(int) MULTIPLES_PASOS.dimension];
        for(int i = 0; i < valores.length; i++) {
            
            valores[i] = random.nextInt();
            
            /* considera la generación y la asignación como 2 operaciones */
            contador.incrementar(2);
        }
        
        assertThat("valor del contador ", contador.getValor(), is(MULTIPLES_PASOS.dimension * 2));
        
        ejecucion.terminar();
    }
    
    @Test
    public void medirAlgoritmoMultipleConContadoresParciales() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre, 
                                                             MULTIPLES_PASOS.problema.nombre,
                                                             MULTIPLES_PASOS.dimension);
        
        Contador contador = ejecucion.contarInstrucciones();
        
        Contador contadorG = contador.getParcial("generaciones");
        Contador contadorI = contador.getParcial("incrementos");
        
        Random random = new Random();
        
        /* inicialización */
        contador.incrementar();
        int[] valores = new int[(int) MULTIPLES_PASOS.dimension];
        for(int i = 0; i < valores.length; i++) {
            
            /* cuenta el incremento */
            contadorI.incrementar();
            
            valores[i] = random.nextInt();
            
            /* cuenta la generación */
            contadorG.incrementar();
            
            /* cuenta general */
            contador.incrementar();
        }
        
        
        assertThat("contador total", contador.getValor(), is(MULTIPLES_PASOS.dimension * 3 + 1));
        assertThat(contador.getDiscriminante(), is((Discriminante)ejecucion.getModulo()));
        assertThat("contador de asignaciones", contadorG.getValor(), is(MULTIPLES_PASOS.dimension));
        assertThat(contadorG.getDiscriminante().getNombre(), is("generaciones"));
        assertThat("contador de asignaciones", contadorI.getValor(), is(MULTIPLES_PASOS.dimension));
        assertThat(contadorI.getDiscriminante().getNombre(), is("incrementos"));
        
        ejecucion.terminar();
    }
}
