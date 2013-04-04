package ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javax.inject.Inject;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.reporte.ItemReporte;


public class ControladorEficiencia {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @Inject
    private AdministradorDeMediciones administradorDeMediciones;
    
    @FXML
    private TableView<Modulo> tablaModulos;

    @FXML
    private TableColumn<Modulo, Boolean> columnaSeleccionado;

    @FXML
    private TableColumn<Modulo, Long> columnaId;
    
    @FXML
    private TableColumn<Modulo, String> columnaNombre;
    
    @FXML
    private TableColumn<Modulo, String> columnaDescripcion;

    @FXML
    private TableColumn<Modulo, Integer> columnaVersion;
    
    @FXML
    private ScatterChart<Long, Number> grafico;
    
    @FXML
    private PieChart graficoDistribucion;

    @FXML
    private Button botonBuscar;
    
    @FXML
    private TextField campoFiltro;
    
    @Inject
    private Contexto contexto;
    
    private StringProperty filtro;

    private Map<Modulo, BooleanProperty> seleccionados = Collections.emptyMap();
    
    private final ChangeListener<Modulo> moduloSeleccionado = new ChangeListener<Modulo>() {

        @Override
        public void changed(ObservableValue<? extends Modulo> moduloObservable,
                            Modulo anterior, Modulo nuevo) {

            if (nuevo != null) {
                
                seleccionados.get(nuevo).set(true);
            }
            
            cargarDistribucion(nuevo);
        }
    };

    private Callback<CellDataFeatures<Modulo, Boolean>, ObservableValue<Boolean>> 
        valorCeldaSeleccionado = new Callback<CellDataFeatures<Modulo, Boolean>, ObservableValue<Boolean>>() {
            
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Modulo, Boolean> celda) {

                return seleccionados.get(celda.getValue());
            }
    };

    private ChangeListener<Boolean> actualizarMediciones = new ChangeListener<Boolean>() {

        @Override
        public void changed(ObservableValue<? extends Boolean> observable,
                            Boolean anterior, Boolean nuevo) {

            cargarMediciones();
        }
    };
    
    @FXML 
    void initialize() {

        this.tablaModulos.getSelectionModel().selectedItemProperty().addListener(this.moduloSeleccionado);
        
        this.columnaSeleccionado.setCellFactory(CheckBoxTableCell.forTableColumn(this.columnaSeleccionado));
        this.columnaSeleccionado.setCellValueFactory(this.valorCeldaSeleccionado);
        this.columnaId.setCellValueFactory(new PropertyValueFactory<Modulo, Long>("id"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<Modulo, String>("nombre"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<Modulo, String>("descripcion"));
        this.columnaVersion.setCellValueFactory(new PropertyValueFactory<Modulo, Integer>("version"));  
        
        this.filtro = this.campoFiltro.textProperty();
        
        this.graficoDistribucion.visibleProperty()
            .bind(IntegerExpression.integerExpression(this.tablaModulos
                                                          .getSelectionModel()        
                                                          .selectedIndexProperty())
                                   .greaterThanOrEqualTo(0));
    }

    public void buscarModulos() {
        
        List<Modulo> modulos = this.administradorDeMediciones.buscarModulos(this.filtro.get());

        this.seleccionados = new HashMap<Modulo, BooleanProperty>();
        
        for (Modulo modulo : modulos) {
            
            BooleanProperty seleccionado = new SimpleBooleanProperty(false); 
            
            seleccionado.addListener(this.actualizarMediciones);
            
            this.seleccionados.put(modulo, seleccionado);
        }
        
        this.tablaModulos.setItems(FXCollections.observableList(modulos));
        this.grafico.getData().clear();
    }
    
    private void cargarMediciones() {
        
        this.grafico.getData().clear();

        for (Modulo modulo : this.tablaModulos.getItems()) {
            
            if (this.seleccionados.get(modulo).get()) {
            
                List<ItemReporte<Medicion>> items = this.administradorDeMediciones.calcularMediciones(modulo);
        
                XYChart.Series<Long, Number> serie = new XYChart.Series<>();
        
                serie.setName(modulo.getNombre() + " (versión " + modulo.getVersion() + ")");
                
                for (ItemReporte<Medicion> item : items) {
                    
                    // TODO Definir las dimensiones correctamente
                    long tamaño = item.getObjeto().getProblema().getDimension(0).getValor();
                    
                    XYChart.Data<Long, Number> datos = new XYChart.Data<>(tamaño,
                                                                          item.getValor());
                    
                    serie.getData().add(datos);
                }
                
                this.grafico.getData().add(serie);
            }
        }
    }
    
    private void cargarDistribucion(Discriminante discriminante) {
        
        this.graficoDistribucion.getData().clear();
        
        if (discriminante != null) {
            
            ItemReporte<Discriminante> itemCompuesto = this.administradorDeMediciones
                                                           .calcularMedicionesPorDiscriminante(discriminante);

            for (ItemReporte<Discriminante> item : itemCompuesto.getSubItems()) {
                
                PieChart.Data datos = new PieChart.Data(item.getNombre(), item.getProporcion());
                this.graficoDistribucion.getData().add(datos);
            }
        }
    }
    
}
