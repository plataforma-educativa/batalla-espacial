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
                                                                .sumarizarMedicionesPorDiscriminante(modulo);
            
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
                        
            Reporte<Discriminante> 
                reporte = this.administradorDeMediciones.promediarMedicionesPorDiscriminanteDimension(modulo);
            
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            
            serie.setName(this.getTitulo(modulo));
            
            for (ItemReporte<Discriminante> item : reporte.getItems()) {
                
                XYChart.Data<String, Number> datos = new XYChart.Data<>(String.valueOf(item.getClasificador()),
                                                                        item.getValor());
                
                GridPane pane = new GridPane();
                
                ColumnConstraints columna = new ColumnConstraints();
                columna.setPercentWidth(100);
                
                double incrementoOpacidad = 0.8 / (item.getSubItems().size() + 2);
                double opacidad = 0.0;
                
                int nro = 0;
                
                for (ItemReporte<Discriminante> subItem : item.getSubItems()) {
                    
                    Discriminante discriminante = subItem.getObjeto();
                    
                    RowConstraints fila = new RowConstraints();
                    fila.setPercentHeight(subItem.getProporcion() * 100);

                    Pane nodo = new Pane();
                    nodo.setStyle("-fx-background-color:#000000;");
                    opacidad += incrementoOpacidad;
                    nodo.setOpacity(opacidad);
                    GridPane.setConstraints(nodo, 0, nro++);
                    GridPane.setMargin(nodo, new Insets(0,5,0,5));
                    
                    pane.getChildren().add(nodo);
                    pane.getRowConstraints().add(fila);
                }
                
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
