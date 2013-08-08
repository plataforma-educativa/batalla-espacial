package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

@DataSet
public class BuscarIdProblemaTest extends ConsultaTest {

    @Test
    public void encuentraUnProblemaPorNombre() {
        
        Long idProblema = (Long) this.em.createNamedQuery("buscarIdProblema")
                                        .setParameter("nombre", "Ordenamiento")
                                        .getSingleResult();
        
        assertThat("id del problema", idProblema, is(equalTo(14L)));
    }
    
    @Test(expected = NoResultException.class)
    public void noEncuentraUnProblema() {
        
        this.em.createNamedQuery("buscarIdProblema")
               .setParameter("nombre", "Camino Minimo")
               .getSingleResult();
    }
}
