package ar.com.comunidadesfera.eficiencia.test.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;

@RunWith(JMock.class)
@Ignore
public class AdministradorDeMedicionesTest {

    private Mockery simulador = new JUnit4Mockery();
    
    private AdministradorDeMediciones administrador;
    
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    @Before
    public void inicializar() {
        
        this.emf = this.simulador.mock(EntityManagerFactory.class);
        this.em = this.simulador.mock(EntityManager.class);
        
        this.simulador.checking(this.expectativasComunes());
        
        this.administrador = AdministradorDeMediciones.instancia();
        this.administrador.setEntityManagerFactory(emf);
    }
    
    /**
     * @return Expectativas comunes a todos los tests.
     */
    private Expectations expectativasComunes() {
        
        return new Expectations() {{
            
            oneOf(emf).createEntityManager();
            will(returnValue(em));
        }};
    }
    
}
