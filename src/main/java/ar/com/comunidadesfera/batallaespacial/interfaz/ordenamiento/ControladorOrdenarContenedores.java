package ar.com.comunidadesfera.batallaespacial.interfaz.ordenamiento;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.interfaz.util.AlternativaStringConverter;
import ar.com.comunidadesfera.batallaespacial.interfaz.util.GetSeleccionado;
import ar.com.comunidadesfera.batallaespacial.interfaz.util.PiezaSeleccionableStringConverter;
import ar.com.comunidadesfera.batallaespacial.interfaz.util.ValorSeleccionable;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.OrdenadorDeContenedores;
import ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.OrdenarContenedores;
import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;
import ar.com.comunidadesfera.dependencias.Alternativa;


public class ControladorOrdenarContenedores implements BatallaEspacial.Observador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    private BooleanProperty laboratorioHabilitado = new SimpleBooleanProperty(false);
    
    @FXML
    private Pane panel;
    
    @FXML
    private Label mensaje;

    @FXML
    private ListView<ValorSeleccionable<Contenedor>> listaParametros;
    
    @FXML
    private TableView<Contenedor> tablaResultado;

    @FXML
    private TableColumn<Contenedor, Integer> columnaNumero;
    
    @FXML
    private TableColumn<Contenedor, String> columnaContenedor;
    
    @FXML
    private TableColumn<Contenedor, Integer> columnaPuntos;
    
    @FXML
    private TableColumn<Contenedor, Byte> columnaCarga;

    @FXML
    private Label paqueteImplementacion;
    
    @FXML
    private Button botonOrdenar;
    
    @FXML
    private ComboBox<Alternativa<OrdenadorDeContenedores>> selectorImplementacion;
    
    private ObservableList<Contenedor> resultado = FXCollections.observableArrayList();

    private ObservableList<ValorSeleccionable<Contenedor>> parametros = FXCollections.observableArrayList();
    
    private final VisitanteDePiezas recolectarContenedores = new VisitanteDePiezas() {
        
        @Override
        public void visitar(Contenedor contenedor) {
            
            parametros.add(new ValorSeleccionable<Contenedor>(contenedor));
        }
        
        @Override
        public void visitar(Asteroide asteroide) {
        }
        
        @Override
        public void visitar(BaseEspacial base) {
        }
        
        @Override
        public void visitar(Nave nave) {
        }
        
    };

    private final ChangeListener<Alternativa<OrdenadorDeContenedores>> 
        seleccionarImplementacion = new ChangeListener<Alternativa<OrdenadorDeContenedores>>() {

            @Override
            public void changed(ObservableValue<? extends Alternativa<OrdenadorDeContenedores>> valor,
                                Alternativa<OrdenadorDeContenedores> anterior,
                                Alternativa<OrdenadorDeContenedores> nuevo) {

                nuevo.seleccionar();
            }
    };
    
    private final EventHandler<ActionEvent> ordenar = new EventHandler<ActionEvent>() {
            
        @Override
        public void handle(ActionEvent action) {
            
            resultado.clear();
            resultado.addAll(ordenarContenedores.ejecutar(ValorSeleccionable.seleccionados(parametros)));
        }
    };
    
    private final Callable<String> getPaqueteImplementacionSeleccionada = new Callable<String>() {

        @Override
        public String call() throws Exception {

            Alternativa<OrdenadorDeContenedores> alternativa = selectorImplementacion.getSelectionModel()
                                                                                     .getSelectedItem();
            
            return alternativa != null ? alternativa.getImplementacion().getPackage().getName() : null;
        }
    }; 

    @Inject
    private OrdenarContenedores ordenarContenedores;

    @FXML
    void initialize() {

        this.panel.visibleProperty().bind(this.laboratorioHabilitado);
        this.mensaje.visibleProperty().bind(Bindings.not(this.laboratorioHabilitado));
        
        this.listaParametros.setItems(this.parametros);
        
        this.listaParametros.setCellFactory(
                CheckBoxListCell.forListView(new GetSeleccionado<Contenedor>(),
                                             new PiezaSeleccionableStringConverter<Contenedor>()));

        this.tablaResultado.setItems(this.resultado);
        this.columnaNumero.setCellValueFactory(new PropertyValueFactory<Contenedor, Integer>("numero"));
        this.columnaContenedor.setCellValueFactory(new PropertyValueFactory<Contenedor, String>("identificacion"));
        this.columnaPuntos.setCellValueFactory(new PropertyValueFactory<Contenedor, Integer>("puntos"));
        this.columnaCarga.setCellValueFactory(new PropertyValueFactory<Contenedor, Byte>("nivelDeCarga"));

        this.selectorImplementacion.setItems(FXCollections.observableList(this.ordenarContenedores.getAlternativas()));
        this.selectorImplementacion.setConverter(AlternativaStringConverter.<OrdenadorDeContenedores>crear());
        this.selectorImplementacion.getSelectionModel()
            .selectedItemProperty().addListener(this.seleccionarImplementacion);

        this.paqueteImplementacion.textProperty()
            .bind(Bindings.createStringBinding(getPaqueteImplementacionSeleccionada, 
                                               this.selectorImplementacion.getSelectionModel().selectedItemProperty()));

        /* el botón solo se habilita cuando existe una implementación seleccionada */
        this.botonOrdenar.disableProperty()
            .bind(IntegerExpression.integerExpression(this.selectorImplementacion
                                                          .getSelectionModel()
                                                          .selectedIndexProperty())
                                   .lessThan(0));
        
        this.botonOrdenar.setOnAction(this.ordenar);
    }

    @Override
    public void iniciada(BatallaEspacial batallaEspacial) {
        
    }

    @Override
    public void jugando(BatallaEspacial batallaEspacial, Partida<? extends Participante> partida) {

        this.parametros.clear();
        this.resultado.clear();
        
        for (Pieza pieza : partida.getTablero()) {

            // TODO hay que usar NullValue
            if (pieza != null) {
                
                pieza.recibir(recolectarContenedores);
            }
        }
        
        this.laboratorioHabilitado.setValue(true);
    }

}
