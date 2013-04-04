package ar.com.comunidadesfera.batallaespacial.interfaz.util;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;


public class ValorSeleccionable<T> {

    private T valor;
    
    private Property<Boolean> seleccionado;
    
    public ValorSeleccionable(T valor) {
        
        this.valor = valor;
        this.seleccionado = new SimpleBooleanProperty();
    }
    
    public Property<Boolean> seleccionadoProperty() {
        
        return this.seleccionado;
    }
    
    public boolean isSeleccionado() {
        
        return this.seleccionado.getValue();
    }
    
    public T getValor() {
        
        return this.valor;
    }
    
    public static <T> List<T> seleccionados(List<ValorSeleccionable<T>> valoresSeleccionables) {
        
        List<T> valores = new LinkedList<T>();
        
        for (ValorSeleccionable<T> valorSeleccionable : valoresSeleccionables) {
            
            if (valorSeleccionable.isSeleccionado()) {
                
                valores.add(valorSeleccionable.getValor());
            }
        }
        
        return valores;
    }
}
