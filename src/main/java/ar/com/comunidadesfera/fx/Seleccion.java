package ar.com.comunidadesfera.fx;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * 
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public class Seleccion<T> {

    private Map<T, Property<Boolean>> items;
    
    private ObservableSet<T> seleccionados;
    
    public Seleccion() {
        
        this.seleccionados = FXCollections.observableSet(new LinkedHashSet<T>());
        this.items = new HashMap<>();
    }
    
    public Property<Boolean> agregar(T disponible) {
        
        Property<Boolean> seleccionado = this.items.get(disponible);
        
        if (seleccionado == null) {
            
            seleccionado = new Seleccionado(disponible);

            this.items.put(disponible, seleccionado);
        }
                
        return seleccionado;
    }
    
    public ObservableSet<T> obtener() {

        return this.seleccionados;
    }

    public void limpiar() {

        this.items.clear();
        this.seleccionados.clear();
        
    }

    protected class Seleccionado extends BooleanPropertyBase {

        private T item;
        
        public Seleccionado(T item) {

            super(false);
            this.item = item;
        }

        @Override
        public T getBean() {

            return this.item;
        }

        @Override
        public String getName() {
            
            return "seleccionado";
        }
        
        @Override
        public void set(boolean value) {
            
            if (value) {
                
                Seleccion.this.seleccionados.add(this.item);
                
            } else {
                
                Seleccion.this.seleccionados.remove(this.item);
            }
            
            super.set(value);
        }
    }

}
