package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;

import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.excepciones.EjecucionTerminadaException;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.RegistroDeEjecucion;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class EjecucionTest extends TestBasico {

    private Ejecucion ejecucion;
    
    @Test
    public void getModulo() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                        SIMPLE_10.problema.nombre,
                                                        SIMPLE_10.dimension);
        Modulo modulo = this.ejecucion.getModulo();
        
        this.usarContador(this.ejecucion.contarInstrucciones());
        
        this.ejecucion.terminar();
        
        assertThat("módulo", modulo, notNullValue());
        assertThat("nombre del módulo", modulo.getNombre(), is(SIMPLE_10.modulo.nombre));
        
    }
    
    @Test
    public void getProblema() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre,
                                                        SIMPLE_10.problema.nombre,
                                                        SIMPLE_10.dimension);
        
        Problema problema = this.ejecucion.getProblema();

        
        this.usarContador(this.ejecucion.contarInstrucciones());
        
        assertThat("problema", problema, notNullValue());
        assertThat("dimensión", this.ejecucion.getDimension(), is(SIMPLE_10.dimension));
        
        this.ejecucion.terminar();
        
        assertThat("problema", this.ejecucion.getProblema(), sameInstance(problema));
   }
    
    @Test
    public void getRegistro() {
        
        Date antesDeIniciar = this.registrarTiempo();
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                        SIMPLE_10.problema.nombre,
                                                        SIMPLE_10.dimension);
        
        Date despuesDeIniciar = this.registrarTiempo();
        
        this.usarContador(this.ejecucion.contarInstrucciones());
        
        Date antesDeTerminar = this.registrarTiempo();

        this.ejecucion.terminar();

        Date despuesDeTerminar = this.registrarTiempo();
        
        RegistroDeEjecucion registro = this.ejecucion.getRegistro();
        
        assertThat("registro", registro, notNullValue());
        assertThat("inicio", registro.getInicio(), estaEntre(antesDeIniciar, despuesDeIniciar));
        assertThat("fin", registro.getFin(), estaEntre(antesDeTerminar, despuesDeTerminar));
    }
    
    @Test
    public void contarInstrucciones() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.problema.nombre,
                                                        SIMPLE_20.dimension);

        Contador contador = this.ejecucion.contarInstrucciones();

        long cuenta = this.usarContador(contador);

        this.ejecucion.terminar();
        
        assertThat(contador, isA(InstrumentoDeMedicion.class));
        
        InstrumentoDeMedicion instrumento = contador;
        
        Medicion medicion = instrumento.getMedicion();
        
        assertThat("medición", medicion, notNullValue());

        Medida medida = medicion.getResultado();
        
        assertThat("resultado de la medición", medida, notNullValue());
        assertThat("magnitud del resultado", medida.getMagnitud(), is(cuenta));
        assertThat("unidad del resultado", medida.getUnidad(), is(Unidad.INSTRUCCIONES));
        
    }

    private long usarContador(Contador contador) {
        
        long cuenta = 0;
        
        for (int dimension = 0; dimension < SIMPLE_20.dimension; dimension++) {
            
            contador.incrementar(2);
            cuenta+=2;
            
            for (int i = 0; i < SIMPLE_20.dimension; i++) {

                contador.incrementar();
                cuenta+=1;
            }
        }
        
        return cuenta;
    }
    
    
    @Test(expected = EjecucionTerminadaException.class)
    public void throwEjecucionTerminadaException() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.problema.nombre,
                                                        SIMPLE_20.dimension);
        
        this.ejecucion.terminar();
        
        this.ejecucion.contarInstrucciones();
    }
}
