package ar.com.comunidadesfera.eficiencia.reportes;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Reporte Jerárquico que agrupa los Items por su clasificación, generando
 * dos niveles de ItemReporte.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public class ReporteAgrupadoPorClasificacion<T> extends ReporteJerarquico<T> {

    public ReporteAgrupadoPorClasificacion(List<ItemReporte<T>> items) {

        this.setItems(this.agrupar(items));
    }
    
    /**
     * Construye nuevos ItemReporte que agrupan a aquellos Items en <code>items</code> que poseen la misma
     * clasificación. 
     * 
     * @param items
     * @return Items que agrupan los ItemReporte de <code>items</code> con el mismo clasificador.
     */
    private List<ItemReporte<T>> agrupar(List<ItemReporte<T>> items) {
        
        List<ItemReporte<T>> itemsAgrupados = new LinkedList<>();
        
        for (Entry<Object, List<ItemReporte<T>>> clase : this.clasificar(items).entrySet()) {
            
            itemsAgrupados.add(new ItemReporte<>(clase.getValue(), clase.getKey()));
        }
        
        return itemsAgrupados;
    }
    
    /**
     * Construye clases de equivalencia sobre los <code>items</code> utilizando su clasificador como
     * relación de equivalencia.  
     * 
     * @param items listado de ItemReporte a clasificar
     * @return clases de equivalencias encontradas.
     */
    protected Map<Object, List<ItemReporte<T>>> clasificar(List<ItemReporte<T>> items) {

        Map<Object, List<ItemReporte<T>>> clasesDeEquivalencia = new LinkedHashMap<>();
        
        for (ItemReporte<T> item : items) {
            
            List<ItemReporte<T>> elementos = clasesDeEquivalencia.get(item.getClasificador());
            
            /* si no existe */
            if (elementos == null) {
                
                elementos = new LinkedList<>();
                clasesDeEquivalencia.put(item.getClasificador(), elementos);
            }
            
            elementos.add(item);
        }

        return clasesDeEquivalencia;
    }
}
