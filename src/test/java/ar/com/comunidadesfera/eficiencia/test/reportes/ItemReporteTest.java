package ar.com.comunidadesfera.eficiencia.test.reportes;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.RegistroDeEjecucion;
import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;

public class ItemReporteTest {

    @Test
    public void crearConValorLong() {
        
        ItemReporte<Void> item = new ItemReporte<>(34L);
        
        assertThat("valor", item.getValor(), is(instanceOf(Long.class)));
        assertThat("valor del item", item.getValor().longValue(), is(equalTo(34L)));
    }
    
    @Test
    public void crearConValorInteger() {
        
        ItemReporte<Void> item = new ItemReporte<>(41);
        
        assertThat("valor", item.getValor(), is(instanceOf(Integer.class)));
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(41)));
    }

    @Test
    public void crearConValorDouble() {
        
        ItemReporte<Void> item = new ItemReporte<>(12.6);
        
        assertThat("valor", item.getValor(), is(instanceOf(Double.class)));
        assertThat("valor del item", item.getValor().doubleValue(), is(closeTo(12.6, 0.01)));
    }

    @Test
    public void crearConValor() {
        
        ItemReporte<Void> item = new ItemReporte<>(928);
        
        assertThat("valor del item", item.getValor().intValue(), is(928));
        assertThat("nombre del item", item.getNombre(), is(nullValue()));
        assertThat("objeto del item", item.getObjeto(), is(nullValue()));
    }
    
    @Test
    public void crearConNombreValor() {
        
        final String nombre = "Incrementos";

        ItemReporte<Void> item = new ItemReporte<>(nombre, 36);
        
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(36)));
        assertThat("nombre del item", item.getNombre(), is(equalTo(nombre)));
        assertThat("objeto del item", item.getObjeto(), is(nullValue()));
    }
    
    @Test
    public void crearConValorObjeto() {
        
        final Object objeto = new Object();
        
        ItemReporte<Object> item = new ItemReporte<>(903, objeto);
        
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(903)));
        assertThat("nombre del item", item.getNombre(), is(nullValue()));
        assertThat("objeto del item", item.getObjeto(), is(sameInstance(objeto)));
    }
    
    @Test
    public void crearConValorObjetoClasificador() {
        
        final Object objeto = new Object();
        final Object clasificador = "Decremento";
        
        ItemReporte<Object> item = new ItemReporte<Object>(391, objeto, clasificador);

        assertThat("valor del item", item.getValor().intValue(), is(equalTo(391)));
        assertThat("nombre del item", item.getNombre(), is(nullValue()));
        assertThat("objeto del item", item.getObjeto(), is(sameInstance(objeto)));
        assertThat("clasificador del item", item.getClasificador(), is(sameInstance(clasificador)));
    }
    
    @Test
    public void crearConNombreValorObjetoClasificador() {
        
        final Object objeto = new Object();
        final Object clasificador = 34;
        
        ItemReporte<Object> item = new ItemReporte<Object>("Incrementos", 90, objeto, clasificador);
        
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(90)));
        assertThat("nombre del item", item.getNombre(), is(equalTo("Incrementos")));
        assertThat("objeto del item", item.getObjeto(), is(sameInstance(objeto)));
        assertThat("clasificador del item", item.getClasificador(), is(sameInstance(clasificador)));
    }
    
    @Test
    public void crearConNombreValorObjeto() {
        
        final Object objeto = new Object();
        
        ItemReporte<Object> item = new ItemReporte<>("Intercambios", 123, objeto);
        
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(123)));
        assertThat("nombre del item", item.getNombre(), is(equalTo("Intercambios")));
        assertThat("objeto del item", item.getObjeto(), is(sameInstance(objeto)));
    }

    @Test
    public void crearConSubItems() {
        
        List<ItemReporte<Void>> subItems = Arrays.asList(new ItemReporte<Void>(56),
                                                         new ItemReporte<Void>(34),
                                                         new ItemReporte<Void>(10));

        ItemReporte<Void> item = new ItemReporte<>(subItems);
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(100)));
        assertThat("nombre del item", item.getNombre(), is(nullValue()));
        assertThat("objeto del item", item.getObjeto(), is(nullValue()));
    }
    
    @Test
    public void crearConNombreSubItems() {
        
        ItemReporte<Void> item = new ItemReporte<>("Total",
                                                   Arrays.asList(new ItemReporte<Void>(56),
                                                                 new ItemReporte<Void>(34),
                                                                 new ItemReporte<Void>(14),
                                                                 new ItemReporte<Void>(87)));
        
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(191)));
        assertThat("nombre del item", item.getNombre(), is(equalTo("Total")));
        assertThat("objeto del item", item.getObjeto(), is(nullValue()));
    }
    
    @Test
    public void crearConSubItemsClasificador() {
        
        final Object clasificador = "Comparaciones";
        
        ItemReporte<Void> item = new ItemReporte<>(Arrays.asList(new ItemReporte<Void>(56),
                                                                 new ItemReporte<Void>(34),
                                                                 new ItemReporte<Void>(14),
                                                                 new ItemReporte<Void>(87)),
                                                   clasificador);
        
        assertThat("valor del item", item.getValor().intValue(), is(equalTo(191)));
        assertThat("nombre del item", item.getNombre(), is(nullValue()));
        assertThat("objeto del item", item.getObjeto(), is(nullValue()));
        assertThat("clasificador del item", item.getClasificador(), is(sameInstance(clasificador)));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void getSubItems() {

        List<ItemReporte<Void>> subItems = Arrays.asList(new ItemReporte<Void>(32),
                                                         new ItemReporte<Void>(28));
        
        ItemReporte<Void> item = new ItemReporte<>(subItems);
        
        assertThat("subItems", item.getSubItems(), hasSize(2));
        assertThat("subItems", item.getSubItems(), contains(hasProperty("valor", is(equalTo(32))), 
                                                            hasProperty("valor", is(equalTo(28)))));
        assertThat("valor", item.getValor().intValue(), is(equalTo(60)));
     }
    
    @Test
    public void getSubItemsDeItemSimple() {
        
        ItemReporte<Void> item = new ItemReporte<>(14);
        
        assertThat("subItems", item.getSubItems(), is(empty()));
        assertThat("valor", item.getValor().intValue(), is(equalTo(14)));
    }
    
    @Test
    public void getProporcionDeItemSimple() {
        
        ItemReporte<Void> item = new ItemReporte<>(98);
        
        assertThat("proporcion", item.getProporcion(), is(closeTo(1.0, 0.001)));
    }
    
    @Test
    public void getProporcionEnLosSubItems() {
        
        List<ItemReporte<Void>> subItems = Arrays.asList(new ItemReporte<Void>(45),
                                                         new ItemReporte<Void>(12),
                                                         new ItemReporte<Void>(86));
        
        ItemReporte<Void> item = new ItemReporte<>(subItems);
        
        assertThat("proporcion del subItem", item.getSubItems().get(0).getProporcion(), is(closeTo(0.3146, 0.0001)));
        assertThat("proporcion del subItem", item.getSubItems().get(1).getProporcion(), is(closeTo(0.0839, 0.0001)));
        assertThat("proporcion del subItem", item.getSubItems().get(2).getProporcion(), is(closeTo(0.6013, 0.0001)));
    }
    
    @Test
    public void getObjetoDevuelveUnaMedicion() {
        
        ItemReporte<Medicion> item = new ItemReporte<>(234, new Medicion());

        assertThat("objeto del item", item.getObjeto(), is(any(Medicion.class)));
    }
    
    @Test
    public void getObjetoDevuelveUnRegistroDeEjecucion() {
        
        ItemReporte<RegistroDeEjecucion> item = new ItemReporte<>(345, new RegistroDeEjecucion());
        
        assertThat("objeto del item", item.getObjeto(), is(any(RegistroDeEjecucion.class)));
    }
    
    @Test
    public void getClasificadorDevuelveObjetoPorDefecto() {
        
        ItemReporte<Object> item = new ItemReporte<>(294, new Object());
        
        assertThat("clasificador", item.getClasificador(), is(sameInstance(item.getObjeto())));
    }

    @Test
    public void getClasificadorPuedeDevolverNull() {
        
        ItemReporte<Object> item = new ItemReporte<>(921, new Object(), null);
        
        assertThat("clasificador", item.getClasificador(), is(nullValue()));
    }
}
