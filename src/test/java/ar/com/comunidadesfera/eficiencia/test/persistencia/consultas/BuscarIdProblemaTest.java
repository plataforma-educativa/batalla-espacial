package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

public class BuscarIdProblemaTest extends ConsultaTest {

    @Test
    @DataSet
    public void unProblemaEncontrado() {
        
        Long idProblema = (Long) this.em.createNamedQuery("buscarIdProblema")
                                        .setParameter("nombre", "Ordenamiento")
                                        .getSingleResult();
        
        assertThat("id del problema", idProblema, is(equalTo(14L)));
    }
}
