package ar.com.comunidadesfera.eficiencia.reportes;

import java.util.Collections;
import java.util.List;

/**
 * Un ItemReporte corresponde a cada uno de los elementos que componen un {@link Reporte}.
 *
 * Mínimamente un ItemReporte tiene un <strong>valor</strong>, asociado directamente
 * o resultante de la suma de los subItems del mismo.
 * 
 * Un ItemReporte puede tener:
 * 
 *   - un nombre
 *   - un objeto asociado
 *   - un clasificador
 *   - una lista de subItems
 *   - un superItem 
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T> tipo de objeto asociado a este ItemReporte
 */
public class ItemReporte<T> {

    private Number valor;
    private String nombre;
    private T objeto;
    private Object clasificador;
    private ItemReporte<T> superItem;
    private List<ItemReporte<T>> subItems = Collections.emptyList();

    public ItemReporte(Number valor) {

        this.valor = valor;
    }

    public ItemReporte(String nombre, Number valor) {

        this.valor = valor;
        this.nombre = nombre;
    }

    public ItemReporte(List<ItemReporte<T>> subItems) {
 
        this.setSubItems(subItems);
    }

    public ItemReporte(Number valor, T objeto) {
        
        this.valor = valor;
        this.objeto = objeto;
        this.clasificador = objeto;
    }

    public ItemReporte(String nombre, Number valor, T objeto) {

        this.valor = valor;
        this.nombre = nombre;
        this.objeto = objeto;
        this.clasificador = objeto;
    }

    public ItemReporte(String nombre, List<ItemReporte<T>> subItems) {
        
        this.setSubItems(subItems);
        this.nombre = nombre;
    }

    public ItemReporte(Number valor, T objeto, Object clasificador) {
        
        this.valor = valor;
        this.objeto = objeto;
        this.clasificador = clasificador;
    }

    public ItemReporte(String nombre, Number valor, T objeto, Object clasificador) {

        this.valor = valor;
        this.nombre = nombre;
        this.objeto = objeto;
        this.clasificador = clasificador;
    }

    public ItemReporte(List<ItemReporte<T>> subItems, Object clasificador) {
        
        this.setSubItems(subItems);
        this.clasificador = clasificador;
    }

    private void setSubItems(List<ItemReporte<T>> subItems) {
        
        this.subItems = subItems;
        
        double total = 0;

        for (ItemReporte<T> item : this.subItems) {

            total += item.getValor().doubleValue();
            
            item.superItem = this;
        }

        this.valor = total;
    }

    public Number getValor() {
        
        return this.valor;
    }

    public String getNombre() {

        return this.nombre;
    }

    public List<ItemReporte<T>> getSubItems() {

        return this.subItems;
    }

    public double getProporcion() {

        double proporcion = 1.0;

        if (this.superItem != null) {

            proporcion = this.getValor().doubleValue() / this.superItem.getValor().doubleValue();
        }
        
        return proporcion;
    }

    public T getObjeto() {
        
        return this.objeto;
    }

    public Object getClasificador() {

        return this.clasificador;
    }
    
    @Override
    public String toString() {

        return new StringBuilder(this.getClass().getSimpleName())
                        .append("[")
                        .append("nombre: ")
                        .append(this.getNombre())
                        .append(", valor: ")
                        .append(this.getValor())
                        .append("]")
                        .toString();
    }
}
