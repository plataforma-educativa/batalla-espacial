package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.reporte.ItemReporte;

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
    @SuppressWarnings("unchecked")
    public void encuentraUnaUnicaMedicion() {
        
        Modulo moduloConUnaUnicaMedicion = new Modulo();
        moduloConUnaUnicaMedicion.setId(2L);
        
        List<ItemReporte<Medicion>> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                                                   .setParameter("modulo", moduloConUnaUnicaMedicion)
                                                   .getResultList();
        
        assertThat("items encontrados", items, hasSize(1));
        assertThat("item", items, contains(itemReporteQue().tieneValor(276L).tieneObjeto(medicion(100L))));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void encuentraMultiplesMediciones() {
        
        Modulo moduloConMultiplesMediciones = new Modulo();
        moduloConMultiplesMediciones.setId(3L);
        
        List<ItemReporte<Medicion>> items = this.em.createNamedQuery("calcularMedicionesDeUnModulo")
                                                   .setParameter("modulo", moduloConMultiplesMediciones)
                                                   .getResultList();
        
        assertThat("items encontrados", items, hasSize(3));
        assertThat("items", items, contains(itemReporteQue().tieneValor(1002L).tieneObjeto(medicion(201L)),
                                            itemReporteQue().tieneValor(2005L).tieneObjeto(medicion(203L)),
                                            itemReporteQue().tieneValor(4102L).tieneObjeto(medicion(202L))));
    }
}

