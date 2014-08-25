package ar.com.comunidadesfera.batallaespacial.interfaz;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class DibujanteProfesionalDePiezas extends DibujanteDePiezas {

    private static final Paint COLOR_MARGEN = Color.web("4e4d50");
    private static final Paint COLOR_CASILLERO_PAR = Color.web("e6e7e8");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("FFFFFF");
    
    
    private static final Paint[] COLOR_ASTEROIDE_SUPERFICIE = { 
        Color.web("504100"), 
        Color.web("403100"), 
        Color.web("594909"), 
        Color.web("605120"), 
        Color.web("554110")
    };
   
    private static final Paint[] COLOR_ASTEROIDE_CRATER = { 
        Color.web("333333"),
        Color.web("444444"),
        Color.web("555555"),
        Color.web("666666"),
        Color.web("777777"),
    };
    
    private static final Paint COLOR_CONTENEDOR = Color.web("555555");
    private static final Color COLOR_CONTENEDOR_INDICADOR_VACIO = Color.WHITE;
    private static final Color COLOR_CONTENEDOR_INDICADOR_ANTIMATERIA = Color.web("ffe35b");
    private static final Color COLOR_CONTENEDOR_INDICADOR_METAL = Color.web("dd1539");
    private static final Color COLOR_CONTENEDOR_INDICADOR_CRISTAL = Color.web("3daeb6");
    
    private Random aleatorio = new Random();
    
    public DibujanteProfesionalDePiezas() {
        
    }
    
    @Override
    public void visitar(Nave nave) {

        Participante participante = this.configuracion.getParticipante(nave.getCivilizacion());

        SVGPath casco = new SVGPath();
        casco.setFill(participante.getPintura());
        casco.setContent("M4.477,24.277c0,0,1.084,5.091,1.402,5.489c0.126,0.158,0.368,0.345,0.802,0.345c0.214,0,2.511-0.548,2.511-0.548l7.611,2.938c0,0,1.316-0.008,1.504,0l7.611-2.915c0,0,2.08,0.523,2.291,0.523c0.455,0,0.69-0.216,0.791-0.346c0.308-0.403,1.521-5.506,1.521-5.506s0.312,0.212,0.5,0.212c0.084,0,0.158-0.017,0.219-0.038c0.135-0.021,0.296-0.079,0.449-0.226c0.408-0.388,0.867-1.457,0.804-6.313l-0.002-0.231c0-2.223-0.286-11.386-1.654-11.386c-0.313,0-0.594,0.121-0.812,0.344c-0.489,0.509-0.559,8.034-0.546,8.7l-3.126-0.498c-0.169-0.396-0.329-0.781-0.479-1.148c-0.933-2.298-3.7-8.389-4.397-8.812c-0.117-0.07-3.86-2.359-4.024-2.359c-0.154,0-0.293-0.006-0.32,0c-0.031,0.008-3.663,2.308-3.692,2.322c-0.257,0.116-3.299,5.317-4.789,8.989c-0.151,0.374-0.314,0.767-0.485,1.169L5.521,15.32c0.011-0.645-0.062-8.125-0.539-8.622C4.763,6.473,4.48,6.353,4.163,6.353c-1.367,0-1.654,9.14-1.654,11.346l-0.003,0.23c-0.062,4.843,0.396,5.907,0.804,6.293c0.155,0.148,0.317,0.207,0.453,0.23C3.957,24.512,4.477,24.277,4.477,24.277z");
        
        DropShadow sombra = new DropShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);
        casco.setEffect(sombra);
        
        SVGPath puente = new SVGPath();
        puente.setFill(participante.getPintura());
        puente.setBlendMode(BlendMode.MULTIPLY);
        puente.setContent("M12.164,14.809c0.021-0.063,0.532-1.553,1.709-4.24c1.104-2.518,3.054-2.712,3.625-2.712c0.14,0,0.22,0.011,0.22,0.011c3.629,0.05,5.071,6.703,5.131,6.985c0.026,0.125-0.009,0.255-0.098,0.347c-0.088,0.103-0.223,0.157-0.361,0.157l0,0l0,0h-9.777l0,0c-0.149,0-0.289-0.062-0.377-0.173C12.146,15.075,12.12,14.937,12.164,14.809z");
        
        SVGPath escotilla = new SVGPath();
        escotilla.setFill(participante.getPintura());
        escotilla.setBlendMode(BlendMode.MULTIPLY);
        escotilla.setContent("M18.036,18.005v5.423c0,0.276-0.241,0.502-0.536,0.502c-0.296,0-0.536-0.226-0.536-0.502v-5.423c0-0.279,0.24-0.505,0.536-0.505C17.795,17.5,18.036,17.728,18.036,18.005z");
        
        this.dibujo = new Group(casco, puente, escotilla);
    }
    
    @Override
    public void visitar(BaseEspacial base) {

        Participante participante = this.configuracion.getParticipante(base.getCivilizacion());
        Paint pinturaBase = (participante != null) ? participante.getPintura() : Color.DARKGRAY;

        SVGPath casco = new SVGPath();
        casco.setFill(pinturaBase);
        casco.setContent("M34.761,4.977l-4.74-4.738C29.879,0.086,29.679,0,29.469,0H5.529c-0.208,0-0.41,0.086-0.552,0.239L0.239,4.977C0.087,5.121,0,5.322,0,5.531v23.936c0,0.211,0.087,0.41,0.239,0.557l4.738,4.736C5.122,34.912,5.324,35,5.532,35h23.937c0.21,0,0.41-0.088,0.555-0.24l4.737-4.74C34.913,29.877,35,29.678,35,29.467V5.529C35,5.32,34.913,5.119,34.761,4.977z");

        SVGPath cubierta = new SVGPath();
        cubierta.setFill(pinturaBase);
        cubierta.setBlendMode(BlendMode.MULTIPLY);
        cubierta.setContent("M26.242,18.541c-0.424,0-0.763,0.338-0.763,0.76v4.766l-1.411,1.41H19.26c-0.421,0-0.76,0.34-0.76,0.762v3.953h-2V26.24c0-0.422-0.339-0.762-0.76-0.762h-4.805l-1.415-1.412V19.26c0-0.422-0.339-0.76-0.76-0.76H4.485v-2h4.275c0.42,0,0.76-0.34,0.76-0.76v-4.807l1.415-1.414h4.805c0.42,0,0.76-0.338,0.76-0.76V4.807h2V8.76c0,0.422,0.339,0.76,0.76,0.76h4.809l1.408,1.414v4.807c0,0.42,0.34,0.76,0.763,0.76h4.275v2.041H26.242z");
        
        InnerShadow sombra = new InnerShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);
        cubierta.setEffect(sombra);
        
        this.dibujo = new Group(casco, cubierta);
    }
    
    @Override
    public void visitar(Asteroide asteroide) {

        SVGPath cuerpo = new SVGPath();
        cuerpo.setFill(COLOR_ASTEROIDE_SUPERFICIE[ this.aleatorio.nextInt(COLOR_ASTEROIDE_SUPERFICIE.length) ]);
        cuerpo.setContent("M32.495,16.372c-0.096-1.128-0.555-2.351-1.214-3.592c-0.711-1.338-4.192-6.084-4.845-6.978c-0.974-1.333-1.615-2.363-1.439-2.868c0.259-0.751-2.625-0.417-5.776,0.155c-1.218,0.222-3.503-0.41-3.071,1.267c0.433,1.675-4.446,0.34-4.446,0.34s-5.501,1.718-4.503,2.75c0.609,0.633-4.748,7.37-4.7,8.921c0.139,4.239,2.907,10.175,3.008,8.913c0.098-1.26,3.011,3.024,4.492,4.523c1.693,1.712,10.246,1.347,10.093,2.396s6.058-0.899,4.905-2.396c-1.306-1.689,4.431-5.439,3.537-6.6C27.641,22.05,32.682,18.657,32.495,16.372z");
        
        Ellipse crater1 = new Ellipse(7.759,20.256,0.859,0.886);
        crater1.setFill(COLOR_ASTEROIDE_CRATER[ this.aleatorio.nextInt(COLOR_ASTEROIDE_CRATER.length) ]);
        crater1.setBlendMode(BlendMode.COLOR_DODGE);

        Ellipse crater2 = new Ellipse(19.623,25.974,2.518,2.603);
        crater2.setFill(COLOR_ASTEROIDE_CRATER[ this.aleatorio.nextInt(COLOR_ASTEROIDE_CRATER.length) ]);
        crater2.setBlendMode(BlendMode.COLOR_DODGE);
        
        SVGPath crater3 = new SVGPath();
        crater3.setFill(COLOR_ASTEROIDE_CRATER[ this.aleatorio.nextInt(COLOR_ASTEROIDE_CRATER.length) ]);
        crater3.setContent("M14.699,19.507c-0.791,0-1.432-0.66-1.432-1.48c0-0.817,0.641-1.478,1.432-1.478c0.787,0,1.427,0.66,1.427,1.478S15.486,19.507,14.699,19.507z");
        crater3.setBlendMode(BlendMode.COLOR_DODGE);

        SVGPath crater4 = new SVGPath();
        crater4.setFill(COLOR_ASTEROIDE_CRATER[ this.aleatorio.nextInt(COLOR_ASTEROIDE_CRATER.length) ]);
        crater4.setContent("M12.876,13.559c-0.942,1.17-2.292,1.611-3.011,0.991c-0.724-0.624-0.542-2.078,0.399-3.246c0.946-1.17,2.296-1.614,3.016-0.991C14.004,10.936,13.822,12.389,12.876,13.559z");
        crater4.setBlendMode(BlendMode.COLOR_DODGE);
        
        SVGPath crater5 = new SVGPath();
        crater5.setFill(COLOR_ASTEROIDE_CRATER[ this.aleatorio.nextInt(COLOR_ASTEROIDE_CRATER.length) ]);
        crater5.setContent("M26.819,15.82c0.577,0,1.047,0.486,1.047,1.082c0,0.602-0.47,1.087-1.047,1.087s-1.049-0.489-1.049-1.087C25.771,16.306,26.242,15.82,26.819,15.82z");
        crater5.setBlendMode(BlendMode.COLOR_DODGE);
        
        SVGPath crater6 = new SVGPath();
        crater6.setFill(COLOR_ASTEROIDE_CRATER[ this.aleatorio.nextInt(COLOR_ASTEROIDE_CRATER.length) ]);
        crater6.setContent("M20.778,11.917c0-1.1,1.126-1.992,2.518-1.992c1.385,0,2.517,0.892,2.517,1.992c0,1.096-1.132,1.988-2.517,1.988C21.904,13.905,20.778,13.013,20.778,11.917z");
        crater6.setBlendMode(BlendMode.COLOR_DODGE);
        
        this.dibujo = new Group(cuerpo, crater1, crater2, crater3, crater4, crater5, crater6);
        this.dibujo.rotateProperty().set(Math.random() * 360);
    }
    
    @Override
    public void visitar(Contenedor contenedor) {

        SVGPath estructura = new SVGPath();
        estructura.setFill(COLOR_CONTENEDOR);
        estructura.setContent("M28,4H8C6.9,4,6,4.9,6,6v23.001c0,1.1,0.9,2,2,2h20c1.101,0,2-0.9,2-2V6C30,4.9,29.101,4,28,4z");

        Color colorIndicador = COLOR_CONTENEDOR_INDICADOR_VACIO;
        
        long antimateria = contenedor.getCantidad(Sustancia.ANTIMATERIA);
        long metal = contenedor.getCantidad(Sustancia.METAL);
        long cristal = contenedor.getCantidad(Sustancia.CRISTAL);
        
        if ((antimateria > 0) || (metal > 0) || (cristal > 0)) {
            
            if ((antimateria >= metal) && (antimateria >= cristal)) {
                
                colorIndicador = COLOR_CONTENEDOR_INDICADOR_ANTIMATERIA;
                
            } else if ((metal >= antimateria) && (metal >= cristal)) {
                
                colorIndicador = COLOR_CONTENEDOR_INDICADOR_METAL;
                
            } else {
                
                colorIndicador = COLOR_CONTENEDOR_INDICADOR_CRISTAL;
            }
        }
        
        Rectangle indicador1 = new Rectangle(10, 22.001, 16, 2);
        Rectangle indicador2 = new Rectangle(10, 11, 16, 2);
        Rectangle indicador3 = new Rectangle(10, 16.5, 16, 2);
        indicador1.setFill(colorIndicador);
        indicador2.setFill(colorIndicador);
        indicador3.setFill(colorIndicador);
        
        this.dibujo = new Group(estructura, indicador1, indicador2, indicador3);
    }
    
    @Override
    public Node dibujarMargen() {

        Rectangle margen = new Rectangle(this.dimension, this.dimension);
        margen.setFill(COLOR_MARGEN);
        margen.setStroke(COLOR_MARGEN);
        margen.setStrokeWidth(6);

        return margen;
    }
    
    @Override
    public Node dibujarCasillero(int fila, int columna) {

        Rectangle casillero = new Rectangle(this.dimension, this.dimension);
        
        Paint pinturaCasillero = (fila + columna) % 2 == 0 ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;
        casillero.setFill(pinturaCasillero);
        casillero.setStroke(pinturaCasillero);
        casillero.setStrokeWidth(6);

        return casillero;
    }
}
