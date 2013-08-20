package ar.com.comunidadesfera.eficiencia.test.reportes;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;
import ar.com.comunidadesfera.eficiencia.reportes.Reporte;
import ar.com.comunidadesfera.eficiencia.reportes.ReporteAgrupadoPorClasificacion;
import ar.com.comunidadesfera.eficiencia.reportes.ReporteSimple;

public class ReporteTest {

    @Test
    public void crearReporteConItems() {
        
        List<ItemReporte<Void>> items = Arrays.asList(new ItemReporte<Void>(23),
                                                      new ItemReporte<Void>(82),
                                                      new ItemReporte<Void>(29),
                                                      new ItemReporte<Void>(15));
        
        Reporte<Void> reporte = new ReporteSimple<>(items);
        
        assertThat("items del reporte", reporte.getItems(), hasSize(4));
    }
    
    @Test
    public void crearReporteAgrupandoItemsPorClasificacion() {
        
        final Object intercambios = "Intercambios";
        final Object comparaciones = "Comparaciones";
        
        List<ItemReporte<String>> items = Arrays.asList(new ItemReporte<>(45, intercambios.toString()),
                                                        new ItemReporte<>(67, comparaciones.toString()),
                                                        new ItemReporte<>(23, intercambios.toString()),
                                                        new ItemReporte<>(57, comparaciones.toString()));
        
        Reporte<String> reporte = new ReporteAgrupadoPorClasificacion<>(items);
        
        assertThat("items del reporte", reporte.getItems(), hasSize(2));
        
        /* Intercambios */
        ItemReporte<String> itemIntercambios = reporte.getItems().get(0);
        assertThat("items[0]", itemIntercambios.getValor().intValue(), is(equalTo(68)));
        assertThat("clasificacion del items[0]", itemIntercambios.getClasificador(), is(equalTo(intercambios)));
        
        List<ItemReporte<String>> subItemsIntercambios = itemIntercambios.getSubItems();
        assertThat("subItems de items[0]", subItemsIntercambios, hasSize(2));
        assertThat("subItems[0] de items[0]", subItemsIntercambios.get(0).getValor().intValue(), is(equalTo(45)));
        assertThat("subItems[1] de items[0]", subItemsIntercambios.get(1).getValor().intValue(), is(equalTo(23)));
        
        /* Comparaciones */
        ItemReporte<String> itemComparaciones = reporte.getItems().get(1);
        assertThat("items[1]", itemComparaciones.getValor().intValue(), is(equalTo(124)));
        assertThat("clasificacion del items[1]", itemComparaciones.getClasificador(), is(equalTo(comparaciones)));
        
        List<ItemReporte<String>> subItemsComparaciones = itemComparaciones.getSubItems();
        assertThat("subItems de items[1]", subItemsComparaciones, hasSize(2));
        assertThat("subItems[0] de items[1]", subItemsComparaciones.get(0).getValor().intValue(), is(equalTo(67)));
        assertThat("subItems[1] de items[1]", subItemsComparaciones.get(1).getValor().intValue(), is(equalTo(57)));
    }
    
    @Test
    public void getProporcionDeLosItems() {
        
        Reporte<Void> reporte = new ReporteSimple<>(Arrays.asList(new ItemReporte<Void>(23),
                                                                  new ItemReporte<Void>(82),
                                                                  new ItemReporte<Void>(29),
                                                                  new ItemReporte<Void>(15)));

        List<ItemReporte<Void>> items = reporte.getItems();
        assertThat("items[0]", items.get(0).getProporcion(), is(closeTo(0.1543, 0.0001)));
        assertThat("items[1]", items.get(1).getProporcion(), is(closeTo(0.5503, 0.0001)));
        assertThat("items[2]", items.get(2).getProporcion(), is(closeTo(0.1946, 0.0001)));
        assertThat("items[3]", items.get(3).getProporcion(), is(closeTo(0.1006, 0.0001)));
    }

}
