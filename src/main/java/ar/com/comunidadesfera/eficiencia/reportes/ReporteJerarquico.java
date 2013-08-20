package ar.com.comunidadesfera.eficiencia.reportes;

import java.util.List;

/**
 * Reporte que agrupa todos los Items como subItems de un ItemReporte raiz.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public abstract class ReporteJerarquico<T> extends Reporte<T> {

    private ItemReporte<T> superItem;
    
    protected void setItems(List<ItemReporte<T>> items) {
        
        this.superItem = new ItemReporte<>(items);
    }
    
    @Override
    public List<ItemReporte<T>> getItems() {

        return this.superItem.getSubItems();
    }
}
