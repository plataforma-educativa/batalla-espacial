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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooserBuilder;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.config.CargadorDeConfiguraciones;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionInvalidaException;
import ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia.ControladorEficiencia;
import ar.com.comunidadesfera.batallaespacial.interfaz.informes.ControladorInformes;
import ar.com.comunidadesfera.batallaespacial.interfaz.ordenamiento.ControladorOrdenarContenedores;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.clasificadores.Dinamica;
import ar.com.comunidadesfera.eficiencia.Contexto;


public class ControladorPrincipal implements Controlador, BatallaEspacial.Observador {

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
    private TabPane secciones;
    
    @FXML
    private Tab seccionPartida;
    
    @FXML
    private Tab seccionLaboratorio;
    
    @FXML
    private Tab seccionRegistros;
    
    @FXML
    private ScrollPane panelMarcoTablero; 

    @FXML
    private CheckMenuItem menuMetricasRegistrar;
    
    @Inject @Dinamica
    private BatallaEspacial batallaEspacial;
    
    @Inject
    private CargadorDeConfiguraciones cargador;

    private Partida partida;
    
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
    void usarLaboratorio() {
        
        this.seccionLaboratorio.getTabPane().getSelectionModel().select(this.seccionLaboratorio);
    }
    
    @FXML
    void verRegistros() {
        
        this.seccionRegistros.getTabPane().getSelectionModel().select(this.seccionRegistros);
    }
    
    @FXML
    void initialize() throws NoSuchMethodException {

        this.secciones.getTabs().remove(seccionPartida);
        this.menuMetricasRegistrar.selectedProperty().bindBidirectional(JavaBeanBooleanPropertyBuilder
                                                                          .create()
                                                                          .bean(this.contexto)
                                                                          .name("persistente")
                                                                          .build());
  
        this.batallaEspacial.agregarObservador(this.panelOrdenamientoController);
        this.batallaEspacial.agregarObservador(this);
    }
    
    private void jugar(Path rutaConfiguracion) {
        
        try {
            
            this.panelMarcoTablero.setContent(null);
            
            this.batallaEspacial.jugar(this.cargador.cargar(rutaConfiguracion));
            
        } catch (ConfiguracionInvalidaException e) {
            
            // TODO 
            e.printStackTrace();
        }
    }

    private void configurarTablero() {
        
        this.dibujante.setConfiguracion(this.partida.getConfiguracion());
        
        PanelTablero panelTablero = new PanelTablero();
        panelTablero.setTablero(this.partida.getTablero());
        panelTablero.setDibujanteDePiezas(this.dibujante);
        panelTablero.setControlador(this);
        panelTablero.disponerPiezas();
        
        this.panelMarcoTablero.setContent(panelTablero);
        this.partida.comenzar();
    }

    /**
     * post: Hace visible el panel del Tablero.
     */
    private void visualizarTablero() {
        
        if (! this.secciones.getTabs().contains(this.seccionPartida)) {
            this.secciones.getTabs().add(1, this.seccionPartida);
        }
        
        this.secciones.getSelectionModel().select(this.seccionPartida);
    }
    
    @Override
    public void seleccionar(Pieza pieza, Vista origen) {

        this.panelInformesController.seleccionar(pieza, origen);
    }

    @Override
    public void jugando(BatallaEspacial batallaEspacial, Partida partida) {

        this.partida = partida;
        this.configurarTablero();
        this.visualizarTablero();
    }

}
