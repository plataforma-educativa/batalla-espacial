package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.aplicacion.ParticipanteExtendido;


public class FabricaDeImagenes {

    /**
     * Mapa de con las imágenes creadas, identificadas por un nombre. 
     */
    private Map<String,Image> imagenes = Collections.synchronizedMap(new HashMap<String,Image>());
    
    public FabricaDeImagenes(){
        super();
    }
    
    /**
     * @pre ninguna.
     * @post crea la imagen de un Nave y la asocia a <code>nombre</code>. 
     * 
     * @param nombre : identificador de la imagen.
     * @param dimension : tamaño (ancho y alto) de la imagen.
     * @param color : color de la nave.
     */
    public void crearImagenNave(String nombre, int dimension, Color color) {

        BufferedImage imagen = new BufferedImage(dimension,
                                                 dimension,
                                                 BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics = (Graphics2D) imagen.getGraphics().create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setColor(color);
        
        Polygon poligono = new Polygon();
        poligono.addPoint(dimension / 2,0);
        poligono.addPoint(dimension - 1,dimension - 1);
        poligono.addPoint(0,dimension - 1);
        poligono.addPoint(dimension / 2,0);
        graphics.fill(poligono);
        
        graphics.dispose();
        
        this.imagenes.put(nombre,imagen);
    }
    
    /**
     * @pre ninguna.
     * @post crea la imagen de un Contenedor y la asocia a <code>nombre</code>. 
     * 
     * @param nombre : identificador de la imagen.
     * @param dimension : tamaño (ancho y alto) de la imagen.
     * @param color : color del contenedor.
     */
    public void crearImagenContenedor(String nombre, int dimension, Color color) {
        
        BufferedImage imagen = new BufferedImage(dimension,
                                                 dimension,
                                                 BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics = (Graphics2D) imagen.getGraphics().create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setColor(color);
        graphics.fill(new Ellipse2D.Double(0,0,dimension, dimension));
        
        graphics.dispose();
        
        this.imagenes.put(nombre,imagen);
    }

    /**
     * @pre ninguna.
     * @post crea la imagen de un Contenedor y la asocia a <code>nombre</code>. 
     * 
     * @param nombre : identificador de la imagen.
     * @param dimension : tamaño (ancho y alto) de la imagen.
     * @param color : color del contenedor.
     */
    public void crearImagenBase(String nombre, int dimension, Color color) {
        
        BufferedImage imagen = new BufferedImage(dimension,
                                                 dimension,
                                                 BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics = (Graphics2D) imagen.getGraphics().create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setColor(color);
        graphics.fill(new RoundRectangle2D.Double(0,0,
                                                  dimension - 1, dimension - 1,
                                                  dimension / 5,
                                                  dimension / 5));
        
        graphics.dispose();
        
        this.imagenes.put(nombre,imagen);
    }

    /**
     * @pre ninguna.
     * @post crea la imagen de un Asteroide y la asocia a <code>nombre</code>. 
     * 
     * @param nombre : identificador de la imagen.
     * @param dimension : tamaño (ancho y alto) de la imagen.
     * @param color : color del asteriode.
     */
    public void crearImagenAsteroide(String nombre, int dimension, Color color) {
        
        BufferedImage imagen = new BufferedImage(dimension,
                                                 dimension,
                                                 BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics = (Graphics2D) imagen.getGraphics().create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setColor(color);
        
        GeneralPath path = new GeneralPath();
        
        
        /* coordenadas de los puntos de control de la curva cúbica */
        double[] coordenadas = { 0.0595, 0.4650,
                                 0.1427, 0.5926, -0.0220, 0.4964,
                                 0.1816, 0.6296, 
                                 0.3499, 0.7425, 0.1298, 0.8628, 
                                 0.4017, 0.9238,
                                 0.5626, 0.9627, 1.0141, 1.0422,
                                 0.9752, 0.7240,
                                 0.9604, 0.5852, 0.7514, 0.6556,
                                 0.8013, 0.4668,
                                 0.8500, 0.2856, 1.0067, 0.0414,
                                 0.7162, 0.0173,
                                 0.4258, -0.0086, 0.5201, 0.2541,
                                 0.2759, 0.2800,
                                 0.0201, 0.2900, 0.0595, 0.4650,
                                 0.0595, 0.4650}; 

        CubicCurve2D curva = new CubicCurve2D.Double();
        
        /* aplica el factor de escala */
        for (int indice = 0; indice < coordenadas.length; indice++) {
            coordenadas[indice] *= dimension;
        }
        
        for (int offset = 0; offset < (coordenadas.length - 6); offset += 6) {
            
            curva.setCurve(coordenadas, offset);
            
            path.append(curva,true);
        }
        
        graphics.fill(path);
        graphics.dispose();
        
        this.imagenes.put(nombre,imagen);
    }    
    
    
    /**
     * @pre ninguna.
     * @post crea la imagen de un Agujer oNegro y la asocia a <code>nombre</code>. 
     * 
     * @param nombre : identificador de la imagen.
     * @param dimension : tamaño (ancho y alto) de la imagen.
     * @param color : color del asteriode.
     */
    public void crearImagenAgujeroNegro(String nombre, int dimension, Color color) {

        BufferedImage imagen = new BufferedImage(dimension,
                                                 dimension,
                                                 BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = (Graphics2D) imagen.getGraphics().create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setColor(color);
        graphics.fill(new Ellipse2D.Double(0,0,dimension, dimension));
        
        graphics.dispose();
        
        this.imagenes.put(nombre,imagen);        
    }   

    protected Map<String,Image>  getImagenes() {
        return this.imagenes;
    }
    
    public Image getImagen(String nombre) {
        return this.getImagenes().get(nombre);
    }
    
    private Color getColorContenedor(Reporte reporte) {
        
        Color color;
        if (reporte.getCantidadDeSustancia(Sustancia.ANTIMATERIA) > 0) {
            
            color = new Color(255,254,183);
            
        } else if (reporte.getCantidadDeSustancia(Sustancia.CRISTAL) > 0) {
            
            color = new Color(127, 198, 192);
            
        } else if (reporte.getCantidadDeSustancia(Sustancia.METAL) > 0) {
            
            color = new Color(206, 151, 152);
            
        } else {
            
            color = new Color(162, 116, 165);
        }
        
        return color;
    }
    
    /**
     * Crea una imagen para el Reporte de Pieza dado.
     * @param nombre
     * @param dimension
     * @param reporte
     */
    public void crearImagen(String nombre, int dimension, Reporte reporte,
                            ParticipanteExtendido participante) {
       
        Color colorParticipante = participante != null ? 
                                    participante.getColor() : 
                                    new Color(255, 149, 0); 
        
        switch (reporte.getEspectro()) {
        
            case ASTEROIDE : this.crearImagenAsteroide(nombre, dimension, 
                                                       new Color(153,130,86));
                break;
                
            case CONTENEDOR : this.crearImagenContenedor(nombre, dimension,
                                                         this.getColorContenedor(reporte));
                break;
           
            case BASE : this.crearImagenBase(nombre, dimension, colorParticipante);
                break;
                
            case NAVE : this.crearImagenNave(nombre, dimension, colorParticipante);
                break;
                
            case DESCONOCIDO : this.crearImagenAgujeroNegro(nombre, dimension, 
                                                            new Color(0,0,0));
                break;
        }
    }
}
