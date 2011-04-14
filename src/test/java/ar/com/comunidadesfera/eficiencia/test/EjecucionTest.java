package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_10;
import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_20;

import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.excepciones.EjecucionTerminadaException;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Dimension;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class EjecucionTest extends TestBasico {

    private Ejecucion ejecucion;
    
    @Test
    public void getModulo() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                        SIMPLE_10.tamaño);
        
        Modulo modulo = this.ejecucion.getModulo();
        
        Assert.assertThat("nombre del módulo",
                          modulo.getNombre(), 
                          Matchers.is(SIMPLE_10.modulo.nombre));
        
        this.ejecucion.terminar();
    }
    
    @Test
    public void getProblema() {
        
        Date antesDeIniciar = new Date();
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre,
                                                        SIMPLE_10.tamaño);
        Date despuesDeIniciar = new Date();

        Problema problema = this.ejecucion.getProblema();
        Assert.assertThat(problema, Matchers.notNullValue());
        
        Assert.assertThat("fin", problema.getInicio(),
                          Matchers.notNullValue());
        Assert.assertThat("inicio", problema.getInicio(), 
                          this.estaEntre(antesDeIniciar, despuesDeIniciar));
        Assert.assertThat("fin", problema.getFin(),
                         Matchers.nullValue());
        
        List<Dimension> dimensiones = problema.getDimensiones();
        Assert.assertThat("dimensiones",
                          dimensiones, 
                          Matchers.notNullValue());
        Assert.assertThat("cantidad de dimensiones", 
                          dimensiones.size(), 
                          Matchers.is(SIMPLE_10.tamaño.length));
        
        for (int i = 0; i < SIMPLE_10.tamaño.length; i++) {
            
            Dimension dimension = dimensiones.get(i);
            
            Assert.assertThat("nombre de la dimensión " + i,
                    dimension.getNombre(), 
                              Matchers.nullValue());
            Assert.assertThat("valor de la dimensión " + i,
                              dimension.getValor(), 
                              Matchers.is(SIMPLE_10.tamaño[i]));
        }
        
        Assert.assertThat("cantidad de dimensiones", 
                          problema.dimensiones(), 
                          Matchers.is(SIMPLE_10.tamaño.length));
        
        for (int i = 0; i < SIMPLE_10.tamaño.length; i++) {

            Dimension dimension = problema.getDimension(i);
            
            Assert.assertThat("nombre de la dimensión " + i,
                    dimension.getNombre(), 
                              Matchers.nullValue());
            Assert.assertThat("valor de la dimensión " + i,
                              dimension.getValor(), 
                              Matchers.is(SIMPLE_10.tamaño[i]));
            
            dimension.setNombre("N" + i);
            
            dimension = problema.getDimension(i);
            
            Assert.assertThat("nombre de la dimensión " + i,
                              dimension.getNombre(), 
                              Matchers.is("N" + i));
        }
       
        Date antesDeTerminar = new Date();
        this.ejecucion.terminar();
        Date despuesDeTerminar = new Date();
        
        Assert.assertThat("fin", 
                          problema.getFin(),
                          this.estaEntre(antesDeTerminar, despuesDeTerminar));  
    }
    
    @Test
    public void contarInstrucciones() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.tamaño);

        Contador contador = this.ejecucion.contarInstrucciones();
        long cuenta = 0;
        
        for (int dimension = 0; dimension < SIMPLE_20.tamaño.length; dimension++) {
            
            contador.incrementar(2);
            cuenta+=2;
            
            for (int i = 0; i < SIMPLE_20.tamaño[dimension]; i++) {

                contador.incrementar();
                cuenta+=1;
            }
        }
        
        Assert.assertThat(contador, Matchers.is(InstrumentoDeMedicion.class));
        
        InstrumentoDeMedicion instrumento = contador;
        
        Medicion medicion = instrumento.getMedicion();
        
        Assert.assertThat("medición", medicion, Matchers.notNullValue());

        Medida medida = medicion.getResultado();
        
        Assert.assertThat("resultado de la medición", medida, 
                          Matchers.notNullValue());
        Assert.assertThat("magnitud del resultado", medida.getMagnitud(),
                          Matchers.is(cuenta));
        Assert.assertThat("unidad del resultado", medida.getUnidad(),
                          Matchers.is(Unidad.INSTRUCCIONES));
        
        this.ejecucion.terminar();
    }
    
    
    @Test(expected = EjecucionTerminadaException.class)
    public void throwEjecucionTerminadaException() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.tamaño);
        
        this.ejecucion.terminar();
        
        this.ejecucion.contarInstrucciones();
    }
}
