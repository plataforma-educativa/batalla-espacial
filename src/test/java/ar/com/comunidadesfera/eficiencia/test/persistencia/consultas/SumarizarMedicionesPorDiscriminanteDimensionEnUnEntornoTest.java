package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
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
    public void sumarizaTresMedicionesDeDosDimensionesConDosDiscriminantesEnElEntorno() {
        
        List<ItemReporte<?>> items = ejecutar(moduloConId(2L), Unidad.INSTRUCCIONES);
        
        final Discriminante CATEGORIA_3 = categoriaConId(3L);
        final Discriminante CATEGORIA_4 = categoriaConId(4L);
        
        assertThat("items sumarizados", items, hasSize(4));
        assertThat("items", items, contains(
                
                   itemReporteCon().clasificador(10L).objeto(CATEGORIA_3).valor(closeTo(091.0, 0.01)),
                   itemReporteCon().clasificador(10L).objeto(CATEGORIA_4).valor(closeTo(009.0, 0.01)),
                   
                   itemReporteCon().clasificador(20L).objeto(CATEGORIA_3).valor(closeTo(384.0, 0.01)),
                   itemReporteCon().clasificador(20L).objeto(CATEGORIA_4).valor(closeTo(020.0, 0.01))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void sumarizaMultiplesMedicionesDeSeisDimensionesConDiferentesDiscriminantesEnElEntorno() {
        
        List<ItemReporte<?>> items = ejecutar(moduloConId(5L), Unidad.INSTRUCCIONES);
        
        final Discriminante CATEGORIA_6 = categoriaConId(6L);
        final Discriminante CATEGORIA_7 = categoriaConId(7L);
        final Discriminante CATEGORIA_8 = categoriaConId(8L);
        
        assertThat("items sumarizados", items, hasSize(11));
        assertThat("items", items, contains(
                
                   itemReporteCon().clasificador(0L).objeto(CATEGORIA_8).valor(closeTo(01.0, 0.1)),
                   
                   itemReporteCon().clasificador(2L).objeto(CATEGORIA_6).valor(closeTo(01.0, 0.1)),
                   itemReporteCon().clasificador(2L).objeto(CATEGORIA_7).valor(closeTo(01.5, 0.1)),
                   
                   itemReporteCon().clasificador(3L).objeto(CATEGORIA_6).valor(closeTo(03.0, 0.1)),
                   itemReporteCon().clasificador(3L).objeto(CATEGORIA_7).valor(closeTo(06.0, 0.1)),
                   
                   itemReporteCon().clasificador(4L).objeto(CATEGORIA_6).valor(closeTo(05.0, 0.1)),
                   itemReporteCon().clasificador(4L).objeto(CATEGORIA_7).valor(closeTo(11.0, 0.1)),
                   
                   itemReporteCon().clasificador(5L).objeto(CATEGORIA_6).valor(closeTo(08.5, 0.1)),
                   itemReporteCon().clasificador(5L).objeto(CATEGORIA_7).valor(closeTo(18.0, 0.1)),
                   
                   itemReporteCon().clasificador(6L).objeto(CATEGORIA_6).valor(closeTo(07.5, 0.1)),
                   itemReporteCon().clasificador(6L).objeto(CATEGORIA_7).valor(closeTo(15.0, 0.1))));
    }
    
    @SuppressWarnings("unchecked")
    private List<ItemReporte<?>> ejecutar(Modulo modulo, Unidad unidadMedida) {
        
        return this.em.createNamedQuery("sumarizarMedicionesPorDiscriminanteDimensionEnUnEntorno")
                      .setParameter("entorno", modulo)
                      .setParameter("unidadMedida", unidadMedida)
                      .getResultList();
    }
}
