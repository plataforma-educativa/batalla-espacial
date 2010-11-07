package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

public class PanelTablero extends javax.swing.JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5450546578929651717L;

    /**
     * Cantidad de filas que tiene el Tablero.
     */
    private int filas;
  
    /**
     * Cantidad de columnas que tiene el Tablero.
     */
    private int columnas;
  
    /**
     * Dimesión (alto y ancho) de todas los casilleros del
     * Tablero.
     */    
    private int dimensionCasilleros;
    
    /**
     * Estilo de las líneas de separación.
     */
    private Stroke estiloLineaCasilleros;

    private Color colorLineaCasilleros;
    
    private Color colorCasilleros;
    
    
    /**
     * Asignaciones Pieza -> Label
     */
    private Map<Pieza, LabelPieza> piezas = new TreeMap<Pieza, LabelPieza>();
    
    /**
     * Construye un Tablero para las filas y columnas indicadas. 
     * 
     * @param filas : cantidad de filas
     * @param columnas : cantidad de columnas
     * @param dimensionCasilleros : ancho y alto cada casillero del Tablero.
     */
    public PanelTablero(int filas, int columnas, int dimensionCasilleros) {
        super();
        this.setFilas(filas);
        this.setColumnas(columnas);
        this.setDimensionCasilleros(dimensionCasilleros);
        this.initGUI();

    }
    
    private void initGUI() {
        try {

            this.setColorCasilleros(new Color(0,0,0));
            this.setColorLineaCasilleros(new Color(1.0f,1.0f,1.0f,0.4f));
            
            /* valores por defecto */
            this.setEstiloLineaCasilleros(new BasicStroke(1.0f,
                                                          BasicStroke.CAP_SQUARE,
                                                          BasicStroke.JOIN_BEVEL,
                                                          1.0f,
                                                          new float[] { 3.0f },
                                                          0f));
            
            TableroLayout layout = new TableroLayout(this.getFilas(),
                                                     this.getColumnas());
            
            
            this.setLayout(layout);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (g instanceof Graphics2D) {
            
            Graphics2D g2D = (Graphics2D) g;

            /* activa el antialiasign*/
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
    
            /* activa el estilo y color de las líneas de los casilleros */
            g2D.setStroke(this.getEstiloLineaCasilleros());
            g2D.setColor(this.getColorLineaCasilleros());
            
            int w = this.getWidth();
            int h = this.getHeight();
  
            /* líneas verticales */
            for (int indice = 1; indice < this.columnas; indice++) {
      
                int y = ((w / this.columnas) * indice) - 1;
                g2D.draw(new Line2D.Double(y,0,y,h));
            }
    
            /* líneas horizontales */
            for (int indice = 1; indice < this.filas; indice++) {
      
                int x = ((h / this.filas) * indice) - 1;
                g2D.draw(new Line2D.Double(0,x,w,x));
            }
        }
    }

    protected int normalizar(int size, int cantidad) {

        return ((int)(size/ cantidad)) * cantidad;
    }

    public int getDimensionCasilleros() {
        return dimensionCasilleros;
    }

    public void setDimensionCasilleros(int dimensionCasilleros) {
        this.dimensionCasilleros = dimensionCasilleros;
      
        /* reajusta el tamaño */
        this.ajustarDimension();
    }
  
    public Dimension getSize() {
        
        return new Dimension(this.getDimensionCasilleros() * this.getColumnas(), 
                             this.getDimensionCasilleros() * this.getFilas());
    }
    
    protected void ajustarDimension() {

        Dimension dimension = this.getSize();
        this.setPreferredSize(dimension);
    }

    public int getColumnas() {
        return columnas;
    }

    protected void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }
  
    protected void setFilas(int filas) {
        this.filas = filas;
    }

    public Color getColorCasilleros() {
        return colorCasilleros;
    }
    
    public void setColorCasilleros(Color colorCasilleros) {
        this.colorCasilleros = colorCasilleros;
        
        /* actualiza el color de los casilleros */
        this.setBackground(this.colorCasilleros);
    }
    
    public Color getColorLineaCasilleros() {
        return colorLineaCasilleros;
    }

    public void setColorLineaCasilleros(Color colorLineaCasilleros) {
        this.colorLineaCasilleros = colorLineaCasilleros;
    }

    public Stroke getEstiloLineaCasilleros() {
        return estiloLineaCasilleros;
    }

    public void setEstiloLineaCasilleros(Stroke estiloLineaCasilleros) {
        this.estiloLineaCasilleros = estiloLineaCasilleros;
    }
    
    public TableroLayout getLayout() {
        return (TableroLayout) super.getLayout();
    }

    public TableroLayout.Posicion getPosicion(Tablero.Casillero casillero) {
        
        return this.getLayout().crearRestriccionPosicion(casillero.getFila(),
                                                         casillero.getColumna());
    }
    
    public void add(Pieza pieza, LabelPieza labelPieza) {
        
        this.piezas.put(pieza, labelPieza);
        super.add(labelPieza, labelPieza.getPosicion());
    }

    /**
     * @pre ninguna.
     * @post devuelve el label asociado a la Pieza <code>pieza</code>.
     * 
     * @param pieza
     * @return 
     */
    public LabelPieza getLabelPieza(Pieza pieza) {
        
        return this.piezas.get(pieza);
    }
    
}
 
