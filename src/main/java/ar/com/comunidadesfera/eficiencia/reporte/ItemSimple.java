package ar.com.comunidadesfera.eficiencia.reporte;

import java.util.Collections;
import java.util.List;

public class ItemSimple<T> extends ItemBasico<T> {

    private static final long serialVersionUID = -487886303501608468L;

    private Number valor;

    public ItemSimple() {
    }

    public ItemSimple(ItemReporte<T> superItem, T objeto, String nombre,
                      String descripcion, Long valor) {

        super(superItem, objeto, nombre, descripcion);
        this.setValor(valor);
    }

    public ItemSimple(String nombre, Number valor) {

        super(nombre);
        this.setValor(valor);
    }

    public ItemSimple(T objeto, Number valor) {

        super(objeto);
        this.setValor(valor);
    }
    
    public ItemSimple(T objeto, String nombre, Number valor) {
        
        super(objeto, nombre);
        this.setValor(valor);
    }

    @Override
    public Number getValor() {

        return this.valor;
    }

    public void setValor(Number valor) {

        this.valor = valor;
    }

    @Override
    public List<ItemReporte<T>> getSubItems() {

        return Collections.emptyList();
    }

    @Override
    public void addSubItem(ItemReporte<T> item) {
        
        throw new UnsupportedOperationException("No se pueden agrega subitems a un ItemSimple");
    }
    
    @Override
    public Number getMaximo() {
        
        return this.getValor();
    }
}
