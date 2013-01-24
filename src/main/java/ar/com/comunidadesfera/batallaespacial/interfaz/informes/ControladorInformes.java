package ar.com.comunidadesfera.batallaespacial.interfaz.informes;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.InsetsBuilder;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.interfaz.Controlador;
import ar.com.comunidadesfera.batallaespacial.interfaz.Vista;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class ControladorInformes implements Controlador, VisitanteDePiezas {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox panel;
    
    @Inject 
    private Instance<FXMLLoader> fxmlLoader;
    
    @Inject
    private Instance<ControladorInformePieza<?>> controlador; 

    private Map<Pieza, ControladorInformePieza<?>> controladores;
    
    public ControladorInformes() {

        this.controladores = new HashMap<>();
    }
    
    @Override
    public void seleccionar(Pieza pieza, Vista origen) {

        pieza.recibir(this);

    }

    @Override
    public void visitar(Nave nave) {
        
        this.cargarInforme(nave, ControladorInformeNave.class, "informe-nave.fxml");
    }

    @Override
    public void visitar(BaseEspacial base) {
        
        this.cargarInforme(base, ControladorInformeBase.class, "informe-base.fxml");
    }
    
    @Override
    public void visitar(Asteroide asteroide) {
        
    }

    @Override
    public void visitar(Contenedor contenedor) {
        
    }

    private <T extends Pieza> void cargarInforme(T pieza, Class<? extends ControladorInformePieza<T>> claseControlador, 
                                                 String recurso) {
        
        if (! this.controladores.containsKey(pieza)) {
            
            ControladorInformePieza<T> controlador = this.controlador.select(claseControlador).get();
            
            controlador.setPieza(pieza);
            
            this.controladores.put(pieza, controlador);
            
            this.cargarInforme(controlador, recurso);
        }
        
        this.controladores.get(pieza).seleccionar(pieza, null);
    }
    
    private void cargarInforme(ControladorInformePieza<?> controlador, String recurso) {
        
        URL urlRecurso = getClass().getResource(recurso);
        
        try {
            
            FXMLLoader loader = this.fxmlLoader.get();
            loader.setResources(this.resources);
            loader.setLocation(urlRecurso);
            loader.setController(controlador);
            Node informe = (Node) loader.load(); 
            
            panel.getChildren().add(informe);
            VBox.setMargin(informe, InsetsBuilder.create().right(5).top(5).build());
            
        } catch (IOException e) {
            
            // TODO Reportar el error.
            e.printStackTrace();
        }
    }
}
