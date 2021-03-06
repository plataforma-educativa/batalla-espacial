package ar.com.comunidadesfera.batallaespacial.interfaz;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.CubicCurveToBuilder;
import javafx.scene.shape.LineToBuilder;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.MoveToBuilder;
import javafx.scene.shape.Path;
import javafx.scene.shape.RectangleBuilder;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class DibujanteDePiezas implements VisitanteDePiezas {

    protected Node dibujo;
    
    protected final double dimension = 30.0;
    
    protected final double margen = dimension * 0.1;
    
    protected Configuracion configuracion;

    public DibujanteDePiezas() {
     
    }
    public void setConfiguracion(Configuracion configuracion) {
        
        this.configuracion = configuracion;
    }
    
    public double getDimension() {
        
        return this.dimension;
    }
    
    @Override
    public void visitar(Nave nave) {
        
        Path dibujoNave = new Path();
        
        Participante participante = this.configuracion.getParticipante(nave.getCivilizacion());
        
        dibujoNave.setStroke(new Color(0.0, 0.0, 0.0, 1.0));
        dibujoNave.setFill(participante.getPintura());
        dibujoNave.setStrokeWidth(1);

        dibujoNave.getElements().addAll(
                MoveToBuilder.create().x(this.dimension / 2).y(this.margen).build(),
                LineToBuilder.create().x(this.dimension - this.margen).y(this.dimension - this.margen).build(),
                LineToBuilder.create().x(this.margen).y(this.dimension - this.margen).build(),
                LineToBuilder.create().x(this.dimension / 2).y(this.margen).build());
        
        this.dibujo = dibujoNave;

    }

    @Override
    public void visitar(BaseEspacial base) {
        
        Canvas dibujoContenedor = new Canvas(this.dimension, this.dimension);
        GraphicsContext grafico = dibujoContenedor.getGraphicsContext2D();

        Participante participante = this.configuracion.getParticipante(base.getCivilizacion());

        grafico.setStroke(new Color(0.0, 0.0, 0.0, 1.0));
        
        grafico.setFill((participante != null) ? participante.getPintura() : Color.WHITE);

        grafico.fillRoundRect(this.margen, this.margen, 
                              this.dimension - 2 * this.margen, this.dimension - 2 * this.margen,
                              this.dimension * 0.1, this.dimension * 0.1);
        grafico.strokeRoundRect(this.margen, this.margen, 
                                this.dimension - 2 * this.margen, this.dimension - 2 * this.margen,
                                this.dimension * 0.1, this.dimension * 0.1);

        this.dibujo = dibujoContenedor;
        
    }

    @Override
    public void visitar(Asteroide asteroide) {

        Path dibujoAsteroide = new Path();
        
        dibujoAsteroide.setStroke(new Color(0.0, 0.0, 0.0, 1.0));
        dibujoAsteroide.setFill(new Color(0.6, 0.5, 0.33, 1.0));
        dibujoAsteroide.setStrokeWidth(1);

        MoveTo inicio = MoveToBuilder.create().x(this.dimension * 0.0595).y(this.dimension * 0.4650).build();
        dibujoAsteroide.getElements().add(inicio);

        dibujoAsteroide.getElements().add(this.crearCurva(0.1427,0.5926,-0.0220,0.4964,0.1816,0.6296));
        dibujoAsteroide.getElements().add(this.crearCurva(0.3499, 0.7425, 0.1298, 0.8628, 0.4017, 0.9238));
        dibujoAsteroide.getElements().add(this.crearCurva(0.5626, 0.9627, 1.0141, 1.0422, 0.9752, 0.7240));
        dibujoAsteroide.getElements().add(this.crearCurva(0.9604, 0.5852, 0.7514, 0.6556, 0.8013, 0.4668));
        dibujoAsteroide.getElements().add(this.crearCurva(0.8500, 0.2856, 1.0067, 0.0414, 0.7162, 0.0173));
        dibujoAsteroide.getElements().add(this.crearCurva(0.4258, -0.0086, 0.5201, 0.2541, 0.2759, 0.2800));
        dibujoAsteroide.getElements().add(this.crearCurva(0.0201, 0.2900, 0.0595, 0.4650, 0.0595, 0.4650)); 
        
        this.dibujo = dibujoAsteroide;

    }

    protected CubicCurveTo crearCurva(double controlX1, double controlY1, 
                                      double controlX2, double controlY2, 
                                      double x, double y) {
        
        return CubicCurveToBuilder.create()
                .controlX1(this.dimension * controlX1).controlY1(this.dimension * controlY1)
                .controlX2(this.dimension * controlX2).controlY2(this.dimension * controlY2)
                .x(this.dimension * x).y( this.dimension * y).build();

    }
    
    @Override
    public void visitar(Contenedor contenedor) {

        Canvas dibujoContenedor = new Canvas(this.dimension, this.dimension);
        
        GraphicsContext grafico = dibujoContenedor.getGraphicsContext2D();
        
        grafico.setStroke(new Color(0.0, 0.0, 0.0, 1.0));
        grafico.setFill(new Color(1.0, 0.99, 0.7, 1.0));
        grafico.fillOval(this.margen, this.margen, 
                         this.dimension - 2 * this.margen, this.dimension - 2 * this.margen);
        grafico.strokeOval(this.margen, this.margen, 
                           this.dimension - 2 * this.margen, this.dimension - 2 * this.margen);

        this.dibujo = dibujoContenedor;
    }

    @Override
    public void visitar(Pieza pieza) {
        
        /* como la pieza no es distinguible, crea un panel en vacio */
        this.dibujo = PaneBuilder.create().prefHeight(this.dimension).prefWidth(this.dimension).build();
    }
    
    /**
     * @post construye un dibujo que representa la Pieza dada.
     * 
     * @param pieza Pieza a dibujar
     * @return representación de la pieza
     */
    public Node dibujar(Pieza pieza) {

        this.dibujo = null;
        
        pieza.recibir(this);

        return this.dibujo;
    }
    
    public Node dibujarMargen() {
        
        return RectangleBuilder.create()
                    .width(this.dimension)
                    .height(this.dimension)
                    .fill(Color.GRAY)
                    .stroke(Color.GRAY)
                    .build();
    }
    
    public Node dibujarCasillero(int fila, int columna) {
        
        return  RectangleBuilder.create()
                    .width(this.dimension)
                    .height(this.dimension)
                    .fill(Color.TRANSPARENT)
                    .stroke(Color.TRANSPARENT)
                    .build();
    }
}
