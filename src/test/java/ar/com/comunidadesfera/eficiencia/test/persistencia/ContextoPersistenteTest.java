package ar.com.comunidadesfera.eficiencia.test.persistencia;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.contextos.ContextoBasico;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medicion_;
import ar.com.comunidadesfera.eficiencia.registros.Modulo_;
import ar.com.comunidadesfera.eficiencia.registros.RegistroDeEjecucion_;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class ContextoPersistenteTest extends TestDePersistencia {
    
    private Date inicio;
    
    @Before
    public void inicializarContexto() {
        
        ContextoBasico contextoBasico = new ContextoBasico(); 
        
        AdministradorDeMediciones administradorDeMediciones = new AdministradorDeMediciones();
        administradorDeMediciones.setEntityManager(this.em);
        contextoBasico.setAdministradorDeMediciones(administradorDeMediciones);
        
        this.contexto = contextoBasico;
        this.contexto.setPersistente(true);

        this.inicio = this.registrarTiempo();        
    }
    
    @Test
    public void medicionIndividual() {
        
        this.em.getTransaction().begin();
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre,
                                                             SIMPLE_10.problema.nombre,
                                                             SIMPLE_10.dimension);
        
        Contador contador = ejecucion.contarInstrucciones();

        /* algoritmo medido*/
        int[] valores = new int[(int) SIMPLE_10.dimension];
        
        for (int i = 0; i < valores.length; i++) {
            
            valores[i] = i;
            contador.incrementar();
        }
        
        ejecucion.terminar();
        
        this.em.getTransaction().commit();
        
        List<Medicion> mediciones = this.buscarMediciones(SIMPLE_10.modulo.nombre);
        
     
        assertThat("mediciones persistidas", mediciones.size(), is(1));
        
        Medicion medicion = mediciones.get(0);
         
        assertThat("resultado de la medición", medicion.getResultado(),
                   this.medidaCon(SIMPLE_10.dimension, Unidad.INSTRUCCIONES));
        assertThat("resultado", medicion,
                   this.medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
    }

    @Test
    public void medicionesMultiples() {

        this.em.getTransaction().begin();
        
        Ejecucion ejecucion = this.contexto.iniciarEjecucion(MULTIPLES_PASOS.modulo.nombre,
                                                             MULTIPLES_PASOS.problema.nombre,
                                                             MULTIPLES_PASOS.dimension);
        
        Contador contador = ejecucion.contarInstrucciones();
        
        int[][] matriz = new int[(int) MULTIPLES_PASOS.dimension / 2]
                                [(int) MULTIPLES_PASOS.dimension / 2];
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
        
        this.em.getTransaction().commit();
        
        List<Medicion> mediciones = this.buscarMediciones(MULTIPLES_PASOS.modulo.nombre);
        
        assertThat("mediciones persistidas", mediciones.size(), is(3));

        for(Medicion medicion : mediciones) {
            
            assertThat("medicion de", medicion, 
                       this.medicionDe(ejecucion.getModulo(), ejecucion.getProblema()));
            
        }
    }
    
    @Test
    public void medicionesMultiplesNuevamente() {

        /* comprueba que la ejecución repetida de multiples mediciones del mismo
         * módulo resultan en distintas ejecuciones */
        
        this.inicio = this.registrarTiempo();
        this.medicionesMultiples();
        
        this.inicio = this.registrarTiempo();
        this.medicionesMultiples();
        
        this.inicio = this.registrarTiempo();
        this.medicionesMultiples();
        
        List<Medicion> mediciones = this.buscarMediciones(MULTIPLES_PASOS.modulo.nombre, false);

        assertThat("mediciones persistidas", mediciones.size(), is(9));
    }

    private List<Medicion> buscarMediciones(String modulo) {
        
        return this.buscarMediciones(modulo, true);
    }
    
    private List<Medicion> buscarMediciones(String modulo, boolean problemaActual) {

        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Medicion> query = builder.createQuery(Medicion.class);
        
        Root<Medicion> medicion = query.from(Medicion.class);
        
        Predicate restriccion = builder.equal(medicion.get(Medicion_.ejecucion)
                                                      .get(RegistroDeEjecucion_.modulo)
                                                      .get(Modulo_.nombre), 
                                              modulo);

        if (problemaActual) {
            
            restriccion = builder.and(restriccion,
                                      builder.greaterThanOrEqualTo(medicion.get(Medicion_.ejecucion)
                                                                           .get(RegistroDeEjecucion_.inicio), 
                                                                   this.inicio));
        }
                
        query.where(restriccion);
        
        return this.em.createQuery(query).getResultList();
    }
    
}
