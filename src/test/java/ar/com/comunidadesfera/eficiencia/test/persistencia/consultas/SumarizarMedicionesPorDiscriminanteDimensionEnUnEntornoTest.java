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
public class SumarizarMedicionesPorDiscriminanteDimensionEnUnEntornoTest 
    extends ConsultaTest {

    @Test
    public void noEncuentraMedicionesEnElEntorno() {
        
        List<?> items = ejecutar(moduloConId(1L), Unidad.INSTRUCCIONES);
        
        assertThat("items sumarizados", items, empty());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void sumarizaDosMedicionesParaCadaUnoDeLosDosDiscriminantesEnElEntorno() {
        
        List<ItemReporte<?>> items = ejecutar(moduloConId(2L), Unidad.INSTRUCCIONES);
        
        assertThat("items sumarizados", items, hasSize(4));
        assertThat("items", items, contains(itemReporteCon().valor(closeTo(091.0, 0.01))
                                                            .objeto(categoriaConId(3L)),
                                            itemReporteCon().valor(closeTo(009.0, 0.01))
                                                            .objeto(categoriaConId(4L)),
                                            itemReporteCon().valor(closeTo(384.0, 0.01))
                                                            .objeto(categoriaConId(3L)),
                                            itemReporteCon().valor(closeTo(020.0, 0.01))
                                                            .objeto(categoriaConId(4L))));
    }
    
    @SuppressWarnings("unchecked")
    private List<ItemReporte<?>> ejecutar(Modulo modulo, Unidad unidadMedida) {
        
        return this.em.createNamedQuery("sumarizarMedicionesPorDiscriminanteDimensionEnUnEntorno")
                      .setParameter("entorno", modulo)
                      .setParameter("unidadMedida", unidadMedida)
                      .getResultList();
    }
}
