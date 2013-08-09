package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.registros.Modulo;

@DataSet
public class CalcularMedicionesDeUnModuloTest extends ConsultaTest {

    @Test
    public void noEncuentraNingunaMedicionParaElModulo() {
        
        Modulo moduloSinMediciones = new Modulo();
        moduloSinMediciones.setId(1L);
        
        List<?> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                               .setParameter("modulo", moduloSinMediciones)
                               .getResultList();
        
        assertThat(items, is(empty()));
    }
    
    @Test
    public void encuentraUnaUnicaMedicion() {
        
        Modulo moduloConUnaUnicaMedicion = new Modulo();
        moduloConUnaUnicaMedicion.setId(2L);
        
        List<?> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                               .setParameter("modulo", moduloConUnaUnicaMedicion)
                               .getResultList();
        
        assertThat("cantidad de items encontrados", items.size(), is(equalTo(1)));
    }
}

