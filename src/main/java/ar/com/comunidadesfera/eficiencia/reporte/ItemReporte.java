package ar.com.comunidadesfera.eficiencia.reporte;

import java.io.Serializable;
import java.util.List;

public interface ItemReporte<T> extends Serializable {

    T getObjeto();
    
    String getNombre();
    
    String getDescripcion();
    
    Number getValor();
    
    Double getProporcion();
    
    ItemReporte<T> getSuperItem();
    
    void setSuperItem(ItemReporte<T> item);
    
    List<ItemReporte<T>> getSubItems();
    
    void addSubItem(ItemReporte<T> item);
    
    Number getMaximo();
}