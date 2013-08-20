package ar.com.comunidadesfera.eficiencia.reportes;

import java.util.List;

/**
 * Listado de elementos, de tipo {@link ItemReporte}, que contienen información
 * estadística.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T> tipo de elemento asociado a cada Item del Reporte
 */
public abstract class Reporte<T> {

    public abstract List<ItemReporte<T>> getItems();
}
