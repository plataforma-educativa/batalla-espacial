package ar.com.comunidadesfera.persistencia.itest.transacciones;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Modulo.*;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.persistencia.Configuracion;
import ar.com.comunidadesfera.persistencia.Configuracion.Tipo;
import ar.com.comunidadesfera.persistencia.Entidad;

@RunWith(Arquillian.class)
public class DemarcacionDeTransaccionesIT extends ITBasico {
    
    private static final String COUNT_MODULO = "SELECT COUNT(m) FROM Modulo m WHERE identificacion = :identificacion AND m.version = :version";
    
    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_NAME)
    public static final String persistenceUnitName = "eficienciaPersistenceUnit";
    
    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_PROPERTIES)
    public static Map<Object, Object> getPersistenceUnitProperties() throws IOException  {
        
        Properties properties = new Properties();
        
        properties.load(DemarcacionDeTransaccionesIT.class
                            .getResourceAsStream("./persistence.properties"));
        
        return properties;
    }
    
    @Inject
    private AdministradorSimulado administrador;
    
    /**
     * Define cómo será el paquete a desplegar en el contexto embebido.
     * 
     * @return
     */
    @Deployment
    public static JavaArchive createTestArchive() {
        
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                         .addPackages(true, DemarcacionDeTransaccionesIT.class.getPackage())
                         .addPackages(true, Entidad.class.getPackage())
                         .addAsManifestResource("META-INF/beans.xml" ,ArchivePaths.create("beans.xml"));
    }
    
    @Test
    public void gestionarTransaccion() {
        
        Modulo modulo = new Modulo(SIMPLE.nombre);
        
        this.administrador.guardar(modulo);
             
        long encontradas = (Long) this.getEntityManager()
                                        .createQuery(COUNT_MODULO)
                                        .setParameter("identificacion", modulo.getIdentificacion())
                                        .setParameter("version", modulo.getVersion())
                                        .getSingleResult();  
        
        Assert.assertThat(encontradas, Matchers.is(1L));
    }
}
