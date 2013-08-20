package ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;

import javax.inject.Inject;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;
import ar.com.comunidadesfera.eficiencia.reportes.Reporte;
import ar.com.comunidadesfera.fx.Seleccion;


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
    private Button botonBuscar;
    
    @FXML
    private TextField campoFiltro;
    
    @FXML
    private TilePane panelDistribucionesPromedio;
    
    @FXML
    private BarChart<String, Number> graficoDistribucionComparativa;
    
    @Inject
    private Contexto contexto;
    
    private StringProperty filtro;
    
    private Seleccion<Modulo> seleccion;

    private final Callback<CellDataFeatures<Modulo, Boolean>, ObservableValue<Boolean>> 
        valorCeldaSeleccionado = new Callback<CellDataFeatures<Modulo, Boolean>, ObservableValue<Boolean>>() {
            
            @Override
            public ObservableValue<Boolean> call(final CellDataFeatures<Modulo, Boolean> celda) {

                return seleccion.agregar(celda.getValue());
            }
    };

    private final SetChangeListener<Modulo> actualizar = new SetChangeListener<Modulo>() {

        @Override
        public void onChanged(SetChangeListener.Change<? extends Modulo> cambio) {

            analizar();
        }
      
    };
  
    @FXML 
    void initialize() {

        this.columnaSeleccionado.setCellFactory(CheckBoxTableCell.forTableColumn(this.columnaSeleccionado));
        this.columnaSeleccionado.setCellValueFactory(this.valorCeldaSeleccionado);
        this.columnaId.setCellValueFactory(new PropertyValueFactory<Modulo, Long>("id"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<Modulo, String>("nombre"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<Modulo, String>("descripcion"));
        this.columnaVersion.setCellValueFactory(new PropertyValueFactory<Modulo, Integer>("version"));  
        
        this.filtro = this.campoFiltro.textProperty();
    
        this.seleccion = new Seleccion<>();
        this.seleccion.obtener().addListener(this.actualizar);
    }

    public void buscar() {
               
        this.buscarModulos();
    }
    
    public void limpiar() {

        this.tablaModulos.getItems().clear();
        this.seleccion.limpiar();
    }
    
    public void analizar() {
        
        this.cargarMediciones();
        this.cargarDistribuciones();
        this.cargarDistribucionesPromedio();
    }
    
    private void buscarModulos() {
        
        List<Modulo> modulos = this.administradorDeMediciones.buscarModulos(this.filtro.get());

        this.tablaModulos.setItems(FXCollections.observableList(modulos));
    }
    
    private void cargarMediciones() {
        
        this.grafico.getData().clear();

        for (Modulo modulo : this.seleccion.obtener()) {
            
            Reporte<Medicion> reporte = this.administradorDeMediciones.buscarMediciones(modulo);
            
            XYChart.Series<Long, Number> serie = new XYChart.Series<>();
            
            serie.setName(this.getTitulo(modulo));
            
            for (ItemReporte<Medicion> item : reporte.getItems()) {
                
                long dimension = item.getObjeto().getEjecucion().getDimension();
                
                XYChart.Data<Long, Number> datos = new XYChart.Data<>(dimension, item.getValor());
                
                serie.getData().add(datos);
            }
            
            this.grafico.getData().add(serie);
        }
        

    }
    
    private void cargarDistribucionesPromedio() {
        
        this.panelDistribucionesPromedio.getChildren().clear();
        
        for (Modulo modulo : this.seleccion.obtener()) {
            
            PieChart grafico = new PieChart();
            
            Reporte<Discriminante> reporte = this.administradorDeMediciones
                                                                .calcularMedicionesPorDiscriminante(modulo);
            
            for (ItemReporte<Discriminante> item : reporte.getItems()) {
          
                PieChart.Data datos = new PieChart.Data(item.getNombre(), item.getProporcion());
                grafico.getData().add(datos);
            }
            
            grafico.setTitle(this.getTitulo(modulo));
            this.panelDistribucionesPromedio.getChildren().add(grafico);
        }
    }
    
    private void cargarDistribuciones() {
        
        this.graficoDistribucionComparativa.getData().clear();

        for (Modulo modulo : this.seleccion.obtener()) {
            
            Reporte<Medicion> reporte = this.administradorDeMediciones.buscarMediciones(modulo);
            
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            
            serie.setName(this.getTitulo(modulo));
            
            for (ItemReporte<Medicion> item : reporte.getItems()) {
                
                long tamaño = item.getObjeto().getEjecucion().getDimension();
                
                XYChart.Data<String, Number> datos = new XYChart.Data<>(String.valueOf(tamaño),
                                                                        item.getValor());
                
                GridPane pane = new GridPane();
                
                RowConstraints fila1 = new RowConstraints();
                fila1.setPercentHeight(50);
                
                RowConstraints fila2 = new RowConstraints();
                fila2.setPercentHeight(50);
                
                ColumnConstraints columna = new ColumnConstraints();
                columna.setPercentWidth(100);
                
                Pane nodo1 = new Pane();
                nodo1.setStyle("-fx-background-color:#000000;");
                nodo1.setOpacity(0.30);
                GridPane.setConstraints(nodo1, 0, 0);
                GridPane.setMargin(nodo1, new Insets(0,5,0,5));
                
                Pane nodo2 = new Pane();
                nodo2.setStyle("-fx-background-color:#000000;");
                nodo2.setOpacity(0.60);
                GridPane.setConstraints(nodo2, 0, 1);
                GridPane.setMargin(nodo2, new Insets(0,5,0,5));

                pane.getChildren().addAll(nodo1, nodo2);
                pane.getRowConstraints().addAll(fila1, fila2);
                pane.getColumnConstraints().add(columna);
                
                datos.setNode(pane);
                serie.getData().add(datos);
            }
            
            this.graficoDistribucionComparativa.getData().add(serie);
        }
    }
    
    public String getTitulo(Modulo modulo) {
        
        return modulo.getNombre() + " (versión " + modulo.getVersion() + ")";
    }
    
    public class Persona extends ObservableValueBase<String> {

        private String nombre; 
        
        public void setNombre(String nombre) {
            
            this.nombre = nombre;
            
            this.fireValueChangedEvent();
        }
        
        public String getNombre() {
            
            return this.nombre;
        }
        
        @Override
        public String getValue() {

            return this.getNombre();
        }
        
    }
}
