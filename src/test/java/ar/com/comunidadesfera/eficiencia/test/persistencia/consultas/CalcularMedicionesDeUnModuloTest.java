package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.reporte.ItemReporte;

@DataSet
public class CalcularMedicionesDeUnModuloTest extends ConsultaTest {

    @Test
    public void noEncuentraNingunaMedicionParaElModulo() {
        
        List<?> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                               .setParameter("modulo", moduloConId(1L))
                               .getResultList();
        
        assertThat(items, is(empty()));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void encuentraUnaUnicaMedicion() {
        
       List<ItemReporte<Medicion>> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                                                   .setParameter("modulo", moduloConId(2L))
                                                   .getResultList();
        
        assertThat("items encontrados", items, hasSize(1));
        assertThat("item", items, contains(itemReporteQue().tieneValor(276L).tieneObjeto(medicionConId(100L))));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void encuentraMultiplesMediciones() {
        
        List<ItemReporte<Medicion>> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                                                   .setParameter("modulo", moduloConId(3L))
                                                   .getResultList();
        
        assertThat("items encontrados", items, hasSize(3));
        assertThat("items", items, contains(itemReporteQue().tieneValor(1002L).tieneObjeto(medicionConId(201L)),
                                            itemReporteQue().tieneValor(2005L).tieneObjeto(medicionConId(203L)),
                                            itemReporteQue().tieneValor(4102L).tieneObjeto(medicionConId(202L))));
    }
}

