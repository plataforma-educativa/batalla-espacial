package ar.com.comunidadesfera.eficiencia.reporte;

import java.util.Collections;
import java.util.List;

public class ItemCompuesto<T> extends ItemBasico<T> {

    private static final long serialVersionUID = 8156541380119137166L;

    private Number maximo = 0;
    
    private List<ItemReporte<T>> subItems = Collections.emptyList();

    public ItemCompuesto() {
    }

    public ItemCompuesto(T objeto) {
        
        super(objeto);
    }

    public ItemCompuesto(ItemReporte<T> superItem, T objeto, String nombre,
                         String descripcion) {
        
        super(superItem, objeto, nombre, descripcion);
    }

    public ItemCompuesto(List<ItemReporte<T>> subItems) {
        
        this();
        this.setSubItems(subItems);
    }

    public ItemCompuesto(String nombre, List<ItemReporte<T>> subItems) {
        
        super(nombre);
        this.setSubItems(subItems);
    }

    public ItemCompuesto(T objeto, List<ItemReporte<T>> subItems) {
        
        super(objeto);
        this.setSubItems(subItems);
    }

    public ItemCompuesto(T objeto, String nombre, List<ItemReporte<T>> subItems) {
        
        super(objeto, nombre);
        this.setSubItems(subItems);
    }
    
    public ItemCompuesto(T objeto, String nombre) {

        super(null, objeto, nombre, "");
    }
    
    @Override
    public List<ItemReporte<T>> getSubItems() {

        return this.subItems;
    }

    @Override
    public Number getValor() {

        double valor = 0;

        for (ItemReporte<T> item : this.getSubItems()) {

            valor += item.getValor().doubleValue();
        }

        return valor;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer()
                                .append("{")
                                .append(this.getNombre())
                                .append(" [");

        for (ItemReporte<T> i : this.getSubItems()) {

            sb.append(i).append(",");
        }
        
        return sb.append("] }").toString();
    }

    @Override
    public void addSubItem(ItemReporte<T> item) {

        item.setSuperItem(this);
        this.getSubItems().add(item);
        
        this.actualizarMaximo(item.getValor());
            
    }
    
    private void actualizarMaximo(Number valor) {

        if (this.getMaximo().doubleValue() < valor.doubleValue()) {
            
            this.setMaximo(valor);
        }   
    }

    private void setMaximo(Number valor) {
        
        this.maximo = valor;
    }
    
    @Override
    public Number getMaximo() {
        
        return this.maximo;
    }
    
    private void setSubItems(List<ItemReporte<T>> subItems) {
        
        this.subItems = subItems;
        
        for (ItemReporte<T> item : this.subItems) {
            
            item.setSuperItem(this);
            this.actualizarMaximo(item.getValor());
        }
        
    }
}
