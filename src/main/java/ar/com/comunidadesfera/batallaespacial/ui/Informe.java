package ar.com.comunidadesfera.batallaespacial.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Informe extends AbstractTableModel {

    private static final long serialVersionUID = -2183735754177746850L;
    public static final int COLUMNAS = 2;

    /**
     * Items en el Informe
     */
    private List<Item> filas = new ArrayList<Item>();

    /**
     * Objeto reportado
     */
    private Object objeto;

    /**
     * Crea el Informe para el Object <code>objeto</code>.
     * @param objeto
     */
    public Informe(Object objeto) {
        
        this.objeto = objeto;
    }
    
    /**
     * @see TableModel#getRowCount() 
     */
    public int getRowCount() {

        return this.filas.size();
    }

    /**
     * @see TableModel#getColumnCount() 
     */
    public int getColumnCount() {

        return COLUMNAS;
    }
    
    /**
     * @see TableModel#isCellEditable(int,int) 
     */
    public boolean isCellEditable(int row, int col) { 
        return false;
    }

    /**
     * @see TableModel#getValueAt(int,int) 
     */
    public Object getValueAt(int rowIndex, int columnIndex) {

        Item item = this.filas.get(rowIndex);
        
        Object value = null;

        try {
            /* invoca el mÃ©todo para actualizar el valor de la property */
            value = (columnIndex == 0) ? 
                        item.getNombre() : item.getGetter().invoke(this.objeto);
                        
        } catch (IllegalArgumentException error) {

            throw new UnsupportedOperationException(error);
            
        } catch (IllegalAccessException error) {
            
            throw new UnsupportedOperationException(error);

        } catch (InvocationTargetException error) {

            throw new UnsupportedOperationException(error);
        }

        return value;
    }

    /**
     * Agrega la property <code>property</code> con el título dado.
     * 
     * @param titulo
     * @param property
     */
    public void addItem(String titulo, String property) {

        try {
            Method getter = this.objeto.getClass().getMethod(this.getGetter(property));        
        
            this.filas.add(new Item(titulo, getter));
            
        } catch (NoSuchMethodException error) {
            
            throw new IllegalArgumentException(error);
        }
    }
    
    protected String getGetter(String property) {

        return "get" + property.substring(0,1).toUpperCase() +
                       property.substring(1);
    }
    
    /**
     * Item del Reporte
     *
     */
    private class Item {
        
        private Method getter;
        private String nombre;
        
        public Item(String nombre, Method getter) {
            
            this.getter = getter;
            this.nombre = nombre;
        }

        public Method getGetter() {
            return getter;
        }

        public String getNombre() {
            return nombre;
        }
    }
    
}
