package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.reporte.ItemReporte;

@DataSet
public class SumarizarMedicionesPorDiscriminanteEnUnEntornoTest extends ConsultaTest {
    
    
    @Test
    public void noEncuentraMedicionesEnElEntorno() {
        
        List<?> items = this.em.createNamedQuery("sumarizarMedicionesPorDiscriminanteEnUnEntorno")
                               .setParameter("entorno", moduloConId(1L))
                               .getResultList();
        
        assertThat("items encontrados", items, empty());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void sumarizaDosMedicionesParaCadaUnoDeLosDosDiscriminantesEnElEntorno() {
        
        List<ItemReporte<?>> items = this.em.createNamedQuery("sumarizarMedicionesPorDiscriminanteEnUnEntorno")
                                            .setParameter("entorno", moduloConId(2L))
                                            .getResultList();
        
        assertThat("items encontrador", items, hasSize(2));
        assertThat("items", items, contains(itemReporteQue().tieneValor(472L).tieneObjeto(categoriaConId(3L)),
                                            itemReporteQue().tieneValor(28L).tieneObjeto(categoriaConId(4L))));
    }
    
    
    
}
