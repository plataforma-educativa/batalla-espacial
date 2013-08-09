package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.DatabaseUnitils;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public abstract class ConsultaTest {
    
    protected static EntityManagerFactory emFactory;
    
    protected EntityManager em = null; 
    
    @BeforeClass
    public static void initEntityManagerFactory() throws Exception {
        
        Properties testConfig = new Properties();
        testConfig.load(ConsultaTest.class.getResourceAsStream("./persistence.properties"));
        
        Ejb3Configuration config = new Ejb3Configuration();
        
        config.setDataSource(DatabaseUnitils.getDataSource());
        
        emFactory = config.configure("eficienciaPersistenceUnit",testConfig)
                          .buildEntityManagerFactory();
        
        /* genera / actualiza la definición del dataset */
        DatabaseUnitils.generateDatasetDefinition();
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
    
}
