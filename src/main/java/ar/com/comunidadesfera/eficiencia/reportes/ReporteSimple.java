package ar.com.comunidadesfera.eficiencia.reportes;

import java.util.List;

/**
 * Reporque Jerárquico que utiliza el listado de Items proporcionados sin realizar ningún
 * procesamiento de los mismos.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public class ReporteSimple<T> extends ReporteJerarquico<T> {

    public ReporteSimple(List<ItemReporte<T>> items) {

        this.setItems(items);
    }
}
