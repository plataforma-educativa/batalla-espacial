package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.MULTIPLES_PASOS;
import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_10;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.contextos.ContextoBasico;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medicion_;
import ar.com.comunidadesfera.eficiencia.registros.Modulo_;
import ar.com.comunidadesfera.eficiencia.registros.Problema_;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;
import ar.com.comunidadesfera.persistencia.test.TestDePersistencia;

public class ContextoPersistenteTest extends TestDePersistencia {
    
    private Date inicio;
    
    @Before
    public void inicializarContexto() {
        
        ContextoBasico contextoBasico = new ContextoBasico(); 
        
        contextoBasico.setEntityManagerFactory(this.getEntityManagerFactory());
        
        this.contexto = contextoBasico;
        this.contexto.setPersistente(true);
        
        this.inicio = new Date();
    }
    
    @Test
    public void medicionIndividual() {
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                             SIMPLE_10.tamaño);
        
        Contador contador = ejecucion.contarInstrucciones();

        /* algoritmo medido*/
        int[] valores = new int[(int) SIMPLE_10.tamaño[0]];
        
        for (int i = 0; i < valores.length; i++) {
            
            valores[i] = i;
            contador.incrementar();
        }
        
        ejecucion.terminar();
        
        List<Medicion> mediciones = this.buscarMediciones(SIMPLE_10.modulo.nombre);
        
     
        Assert.assertThat("mediciones persistidas", mediciones.size(),
                          Matchers.is(1));
        
        Medicion medicion = mediciones.get(0);
        
        Assert.assertThat("resultado de la medición", medicion.getResultado(),
                          this.medidaCon(SIMPLE_10.tamaño[0], Unidad.INSTRUCCIONES));
        Assert.assertThat("resultado", medicion,
                          this.medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
    }

    @Test
    public void medicionesMultiples() {

        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre, 
                                                             MULTIPLES_PASOS.tamaño);
        
        Contador contador = ejecucion.contarInstrucciones();
        
        int[][] matriz = new int[(int) MULTIPLES_PASOS.tamaño[0]]
                                [(int) MULTIPLES_PASOS.tamaño[1]];
        contador.incrementar();
        
        Contador inicializacion = contador.getParcial("inicializacion");
        for(int i = 0; i < matriz.length; i++) {
            
            for(int j = 0; j < matriz[i].length; j++){
                
                matriz[i][j] = i * j;
                inicializacion.incrementar();
            }
        }
        
        Contador procesamiento = contador.getParcial("procesamiento");
        for(int i = 1; i < matriz.length - 1; i++) {
            
            for(int j = 1; j < matriz[i].length - 1; j++) {
                
                matriz[i][j] = matriz[i][j] + matriz[i-1][j-1] - matriz[i+1][j+1];
                procesamiento.incrementar();
            }
        }
        
        ejecucion.terminar();
        
        List<Medicion> mediciones = this.buscarMediciones(MULTIPLES_PASOS.modulo.nombre);
        
        Assert.assertThat("mediciones persistidas", mediciones.size(),
                          Matchers.is(3));

        for(Medicion medicion : mediciones) {
            
            Assert.assertThat("medicion de", medicion, 
                              this.medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
            
        }
    }
    
    @Test
    public void medicionesMultiplesNuevamente() {

        /* comprueba que la ejecución repetida de multiples mediciones del mismo
         * módulo resultan en distintos problemas */
        
        this.inicio = new Date();
        this.medicionesMultiples();
        
        this.inicio = new Date();
        this.medicionesMultiples();

        this.inicio = new Date();
        this.medicionesMultiples();
        
        List<Medicion> mediciones = this.buscarMediciones(MULTIPLES_PASOS.modulo.nombre, false);

        Assert.assertThat("mediciones persistidas", mediciones.size(),
                          Matchers.is(12));
    }

    private List<Medicion> buscarMediciones(String modulo) {
        
        return this.buscarMediciones(modulo, true);
    }
    
    private List<Medicion> buscarMediciones(String modulo, boolean problemaActual) {

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Medicion> query = builder.createQuery(Medicion.class);
        
        Root<Medicion> medicion = query.from(Medicion.class);
        
        Predicate restriccion = builder.equal(medicion.get(Medicion_.modulo)
                                       .get(Modulo_.nombre), modulo);

        if (problemaActual) {
            
            restriccion = builder.and(restriccion,
                                      builder.greaterThanOrEqualTo(medicion.get(Medicion_.problema)
                                                                           .get(Problema_.inicio), 
                                                                   this.inicio));
        }
        
        query.where(restriccion);
        
        return this.em.createQuery(query).getResultList();
    }
    
}
