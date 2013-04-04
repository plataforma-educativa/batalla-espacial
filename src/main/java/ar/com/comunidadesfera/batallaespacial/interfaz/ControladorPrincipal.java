package ar.com.comunidadesfera.batallaespacial.interfaz;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooserBuilder;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.aplicacion.ParticipanteExtendido;
import ar.com.comunidadesfera.batallaespacial.calificadores.Dinamica;
import ar.com.comunidadesfera.batallaespacial.config.CargadorDeConfiguraciones;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionInvalidaException;
import ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia.ControladorEficiencia;
import ar.com.comunidadesfera.batallaespacial.interfaz.informes.ControladorInformes;
import ar.com.comunidadesfera.batallaespacial.interfaz.ordenamiento.ControladorOrdenarContenedores;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.eficiencia.Contexto;


public class ControladorPrincipal implements Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ControladorInformes panelInformesController;
    
    @FXML
    private ControladorEficiencia panelEficienciaController;
    
    @FXML
    private ControladorOrdenarContenedores panelOrdenamientoController;
    
    @FXML
    private ScrollPane panelMarcoTablero; 

    @FXML
    private CheckMenuItem menuMetricasRegistrar;
    
    @Inject @Dinamica
    private BatallaEspacial batallaEspacial;
    
    @Inject
    private CargadorDeConfiguraciones cargador;

    private Partida<ParticipanteExtendido> partida;
    
    @Inject
    private DibujanteDePiezas dibujante;

    @Inject
    private Contexto contexto;
    
    @FXML
    void nuevaPartida(ActionEvent event) {

        ExtensionFilter filtro = new ExtensionFilter(this.cargador.getDescripcion(),
                                                     "*.properties");

        FileChooser selector = FileChooserBuilder
                                .create()
                                .title("Configuración")
                                .extensionFilters(filtro)
                                .build();
        
        File archivo = selector.showOpenDialog(null);
        
        if (archivo != null) {
            
            this.jugar(archivo.toPath());
        }
    }
    
    @FXML
    void initialize() throws NoSuchMethodException {

      this.menuMetricasRegistrar.selectedProperty().bindBidirectional(JavaBeanBooleanPropertyBuilder
                                                                          .create()
                                                                          .bean(this.contexto)
                                                                          .name("persistente")
                                                                          .build());
      
      this.batallaEspacial.agregarObservador(this.panelOrdenamientoController);
    }
    
    private void jugar(Path rutaConfiguracion) {
        
        try {
            
            this.panelMarcoTablero.setContent(null);
            
            this.partida = this.batallaEspacial.jugar(this.cargador.cargar(rutaConfiguracion));
            
            this.dibujante.setConfiguracion(this.partida.getConfiguracion());
            
            PanelTablero panelTablero = new PanelTablero();
            panelTablero.setTablero(this.partida.getTablero());
            panelTablero.setDibujanteDePiezas(this.dibujante);
            panelTablero.setControlador(this);
            panelTablero.disponerPiezas();
            
            this.panelMarcoTablero.setContent(panelTablero);
            this.partida.comenzar();
            
        } catch (ConfiguracionInvalidaException e) {
            
            // TODO 
            e.printStackTrace();
        }
    }

    @Override
    public void seleccionar(Pieza pieza, Vista origen) {

        this.panelInformesController.seleccionar(pieza, origen);
    }
    
}
