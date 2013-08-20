package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;
import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;

@DataSet
public class SumarizarMedicionesPorDiscriminanteEnUnEntornoTest extends ConsultaTest {
    
    
    @Test
    public void noEncuentraMedicionesEnElEntorno() {
        
        List<?> items = ejecutar(moduloConId(1L), Unidad.INSTRUCCIONES);
        
        assertThat("items sumarizados", items, empty());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void sumarizaDosMedicionesParaCadaUnoDeLosDosDiscriminantesEnElEntorno() {
        
        List<ItemReporte<?>> items = ejecutar(moduloConId(2L), Unidad.INSTRUCCIONES);
        
        assertThat("items sumarizados", items, hasSize(2));
        assertThat("items", items, containsInAnyOrder(itemReporteCon().valor(472L)
                                                                      .objeto(categoriaConId(3L)),
                                                      itemReporteCon().valor(28L)
                                                                      .objeto(categoriaConId(4L))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void sumarizaMultiplesMedicionesConDiferentesDiscriminantesEnElEntorno() {
        
        
        List<ItemReporte<?>> items = ejecutar(moduloConId(5L), Unidad.INSTRUCCIONES);

        assertThat("items sumarizados", items, hasSize(3));
        assertThat("items", items, containsInAnyOrder(itemReporteCon().valor(29L)
                                                                      .objeto(categoriaConId(6L)),
                                                      itemReporteCon().valor(58L)
                                                                      .objeto(categoriaConId(7L)),
                                                      itemReporteCon().valor(1L)
                                                                      .objeto(categoriaConId(8L))));
    }
    
    @Test
    public void sumarizaSoloMedicionesConLaMismaUnidadMedida() {
        
        List<ItemReporte<?>> items = ejecutar(moduloConId(9L), Unidad.INSTRUCCIONES);
        
        assertThat("items sumarizados", items, hasSize(2));
    }
    
    @SuppressWarnings("unchecked")
    private List<ItemReporte<?>> ejecutar(Modulo modulo, Unidad unidadMedida) {
        
        return this.em.createNamedQuery("sumarizarMedicionesPorDiscriminanteEnUnEntorno")
                      .setParameter("entorno", modulo)
                      .setParameter("unidadMedida", unidadMedida)
                      .getResultList();
    }
}
