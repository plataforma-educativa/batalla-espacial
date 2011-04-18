package ar.com.comunidadesfera.persistencia.test;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hamcrest.Matchers;
import org.hibernate.ejb.Ejb3Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.test.TestBasico;


public abstract class TestDePersistencia extends TestBasico {

    protected static EntityManagerFactory emFactory;
    protected EntityManager em = null;

    @BeforeClass
    public static void initEntityManagerFactory() throws Exception {
        
        Properties testConfig = new Properties();
        testConfig.load(TestDePersistencia.class.getResourceAsStream("/test-persistence.properties"));
        
        emFactory = new Ejb3Configuration()
                        .configure("eficienciaPersistenceUnit",testConfig)
                        .buildEntityManagerFactory();
    }
    
    @AfterClass
    public static void closeEntityManagerFactory() throws Exception{
        
        if (emFactory != null) {
            
            emFactory.close();
        }
    }

    @Before
    public void initEntityManager() throws Exception {
        
        this.em = emFactory.createEntityManager();
        
    }
    
    @After
    public void closeEntityManager() throws Exception {
        
        this.em.close();
    }
    
    @Test
    public void getEntityManager() {
        
        Assert.assertThat(this.em, Matchers.notNullValue());
        Assert.assertThat(this.em.isOpen(), Matchers.is(true));
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        return TestDePersistencia.emFactory;
    }
}
