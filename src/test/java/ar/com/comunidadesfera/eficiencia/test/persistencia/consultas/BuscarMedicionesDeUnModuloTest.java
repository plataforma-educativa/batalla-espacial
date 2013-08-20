package ar.com.comunidadesfera.eficiencia.test.persistencia.consultas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;
import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;

@DataSet
public class BuscarMedicionesDeUnModuloTest extends ConsultaTest {

    @Test
    public void noEncuentraNingunaMedicionParaElModulo() {
        
        List<?> items = ejecutar(moduloConId(1L), Unidad.INSTRUCCIONES);
        
        assertThat(items, is(empty()));
    }
    
    @Test
    public void encuentraUnaUnicaMedicion() {
        
       List<ItemReporte<Medicion>> items = ejecutar(moduloConId(2L), Unidad.INSTRUCCIONES);
        
        assertThat("items encontrados", items, hasSize(1));
        assertThat("item", items, contains(itemReporteQue().tieneValor(276L).tieneObjeto(medicionConId(100L))));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void encuentraMultiplesMediciones() {
        
        List<ItemReporte<Medicion>> items = ejecutar(moduloConId(3L), Unidad.INSTRUCCIONES);
        
        assertThat("items encontrados", items, hasSize(3));
        assertThat("items", items, contains(itemReporteQue().tieneValor(1002L).tieneObjeto(medicionConId(201L)),
                                            itemReporteQue().tieneValor(2005L).tieneObjeto(medicionConId(203L)),
                                            itemReporteQue().tieneValor(4102L).tieneObjeto(medicionConId(202L))));
    }
    
    @Test
    public void encuentraSoloMedicionesDeInstrucciones() {
        
        List<ItemReporte<Medicion>> items = ejecutar(moduloConId(4L), Unidad.INSTRUCCIONES);
        
        assertThat("items encontrador", items, hasSize(2));
    }
    
    @SuppressWarnings("unchecked")
    private List<ItemReporte<Medicion>> ejecutar(Modulo modulo, Unidad unidadMedida) {
        
        return this.em.createNamedQuery("buscarMedicionesDeUnModulo")
                      .setParameter("modulo", modulo)
                      .setParameter("unidadMedida", unidadMedida)
                      .getResultList();
    }
}

