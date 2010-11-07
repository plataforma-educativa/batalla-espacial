package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TableroLayout implements LayoutManager2 {

    /**
     * Cantidad de filas.
     */
    private int filas;
    
    /**
     * Cantidad de columnas.
     */
    private int columnas;

    /**
     * Posiciones de los componentes del Layout
     */
    private List<PosicionComponente> posiciones = new LinkedList<PosicionComponente>();

    
    public TableroLayout(int filas, int columnas) {
        
        this.filas = filas;
        this.columnas = columnas;
    }
    
    public void addLayoutComponent(Component comp, Object constraints) {
        
        synchronized (comp.getTreeLock()) {
            
            if (constraints instanceof Posicion) {
                
                addLayoutComponent((Posicion)constraints, comp);
                
            } else {
                
                throw new IllegalArgumentException("no se puede agregar al layout: constraint debe ser una Posicion");
            }
        }
    }

    public Dimension maximumLayoutSize(Container target) {
        return target.getMaximumSize();
    }

    public float getLayoutAlignmentX(Container target) {
        return target.getAlignmentX();
    }

    public float getLayoutAlignmentY(Container target) {
        return target.getAlignmentY();
    }

    public void invalidateLayout(Container target) {
        
    }

    public void addLayoutComponent(String name, Component comp) {
        throw new IllegalArgumentException("no se puede agregar al layout: constraint debe ser una Posicion");
    }

    public void removeLayoutComponent(Component comp) {
        this.posiciones.remove(new PosicionComponente(comp,null));
    }

    public Dimension preferredLayoutSize(Container parent) {
        
        return parent.getPreferredSize();
    }

    public Dimension minimumLayoutSize(Container parent) {

        return parent.getMinimumSize();
    }

    public void layoutContainer(Container parent) {
        
        synchronized (parent.getTreeLock()) {
            
            int width = parent.getWidth() / this.columnas;
            int height = parent.getHeight() / this.filas;
            
            ListIterator<PosicionComponente> it = this.posiciones.listIterator();
            while (it.hasNext()) {
                
                PosicionComponente posicionComponente = it.next();
                Posicion posicion = posicionComponente.getPosicion();
                
                /* remueve el componente si la posición fue anulada */
                if (posicion.isAnulada()) {

                    if (posicionComponente.getComponente() != null) {
                        
                        posicionComponente.getComponente().setVisible(false);
                        //parent.remove(posicionComponente.getComponente());
                        posicionComponente.setComponente(null);
                    }  
                    //it.remove();
                    
                } else {
                    
                    posicionComponente.getComponente()
                        .setBounds(posicion.getColumna() * width,
                                   posicion.getFila() * height,
                                   width,height);
                }
            }
        }
    }
    
    protected void addLayoutComponent(Posicion posicion, Component comp) {
        
        this.posiciones.add(new PosicionComponente(comp,posicion));
    }
    
    public Posicion crearRestriccionPosicion(int fila, int columna) {
        return new Posicion(fila,columna);
    }
    

    /**
     * La posición define el constraint a utilizar.
     *  
     *
     */
    public static class Posicion {
        
        private int fila;
        private int columna;
        private boolean anulada;
        
        protected Posicion(int fila, int columna) {
            this.setColumna(columna);
            this.setFila(fila);
            this.setAnulada(false);
        }

        public int getColumna() {
            return columna;
        }

        public void setColumna(int columna) {
            this.columna = columna;
        }

        public int getFila() {
            return fila;
        }

        public void setFila(int fila) {
            this.fila = fila;
        }

        public boolean isAnulada() {
            return anulada;
        }

        public void setAnulada(boolean anulada) {
            this.anulada = anulada;
        }
        
        public void anular(){
            this.setAnulada(true);
        }
    }

    /**
     * Posición asociada a un Componente. 
     *
     */
    protected static class PosicionComponente {
        
        /**
         * Componente asociado a una posición.
         */
        private Component componente;
        
        /**
         * Posición del componente.
         */
        private Posicion posicion;
        
        public PosicionComponente(Component componente, Posicion posicion) {
            this.posicion = posicion;
            this.componente = componente;
        }
        
        public Component getComponente() {
            return this.componente;
        }
        
        public Posicion getPosicion() {
            return this.posicion;
        }

        public void setComponente(Component componente) {
            this.componente = componente;
        }
        /**
         * 
         */
        public boolean equals(Object object) {
            
            boolean iguales = false;
            
            if (object instanceof PosicionComponente) {
                iguales = this.componente.equals(((PosicionComponente) object).componente);
            }
            
            return iguales;
        }
        
    }
}
