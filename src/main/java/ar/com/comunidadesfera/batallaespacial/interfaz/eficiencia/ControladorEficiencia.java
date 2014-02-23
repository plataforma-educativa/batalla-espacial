package ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia;

import static javafx.beans.binding.Bindings.*;

import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

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

    private BooleanProperty busquedaRealizada = new SimpleBooleanProperty(false);
    
    @Inject
    private AdministradorDeMediciones administradorDeMediciones;
    
    @FXML
    private Node mensajeSeleccionarModulo;
    
    @FXML
    private Node mensajeBuscarModulo;

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
    private TabPane panelGraficos;
    
    @FXML
    private TilePane panelDistribuciones;
    
    @FXML
    private SplitPane panelResultado;
    
    @FXML
    private BarChart<String, Number> graficoContribuciones;
    
    @Inject
    private Contexto contexto;
    
    private StringProperty filtro;
    
    private Seleccion<Modulo> seleccion;

    private final MessageFormat formatoLeyenda = new MessageFormat("{0}\n{1,number,percent}");
    
    private final MessageFormat formatoTitulo = new MessageFormat("{0}- {1} (versión {2})");
    
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
        
        this.panelGraficos.visibleProperty().bind( not( isEmpty( this.seleccion.obtener() )));
        this.mensajeSeleccionarModulo.visibleProperty().bind( isEmpty( this.seleccion.obtener() )); 

        this.panelResultado.visibleProperty().bind( this.busquedaRealizada );
        this.mensajeBuscarModulo.visibleProperty().bind( not( this.busquedaRealizada ));
    }

    public void buscar() {
               
        this.buscarModulos();
        
        this.busquedaRealizada.set(true);
    }
    
    public void limpiar() {

        this.busquedaRealizada.set(false);
        this.tablaModulos.getItems().clear();
        this.seleccion.limpiar();
        this.filtro.set("");
    }
    
    public void analizar() {
        
        this.cargarMediciones();
        this.cargarContribuciones();
        this.cargarDistribuciones();
    }
    
    /**
     * post: busca todos los Módulo que cumplan con lo indicado en <code>filtro</code> y los despliega
     *       en <code>tablaModulos</code>
     */
    private void buscarModulos() {
        
        List<Modulo> modulos = this.administradorDeMediciones.buscarModulos(this.filtro.get());

        this.tablaModulos.setItems(FXCollections.observableList(modulos));
    }
    
    /**
     * post: despliega las mediciones de todos los Módulos seleccionados (a través de <code>seleccion</code>)
     *       en <code>grafico</code>. 
     */
    private void cargarMediciones() {

        this.grafico.getData().clear();

        for (Modulo modulo : this.seleccion.obtener()) {

            Reporte<Medicion> reporte = this.administradorDeMediciones.buscarMediciones(modulo);
            XYChart.Series<Long, Number> serie = new XYChart.Series<>();
            serie.setName(this.escribirLeyenda(modulo));
            
            for (ItemReporte<Medicion> item : reporte.getItems()) {
                
                long dimension = item.getObjeto().getEjecucion().getDimension();
                XYChart.Data<Long, Number> datos = new XYChart.Data<>(dimension, item.getValor());
                serie.getData().add(datos);
            }
            
            this.grafico.getData().add(serie);
        }
    }
    
    /**
     * post: despliega las distribuciones de mediciones parciales para cada uno de los Módulos
     *       seleccionados creando PieCharts en <code>panelDistribuciones</code>
     */
    private void cargarDistribuciones() {
        
        this.panelDistribuciones.getChildren().clear();
        
        for (Modulo modulo : this.seleccion.obtener()) {
            
            PieChart grafico = new PieChart();
            Reporte<Discriminante> reporte = this.administradorDeMediciones.sumarizarMedicionesPorDiscriminante(modulo);
            
            for (ItemReporte<Discriminante> item : reporte.getItems()) {
          
                PieChart.Data datos = new PieChart.Data(this.escribirLeyenda(item), item.getProporcion());
                grafico.getData().add(datos);
            }
            
            grafico.setTitle(this.escribirTitulo(modulo));
            grafico.getStyleClass().add("distribucion");
            this.panelDistribuciones.getChildren().add(grafico);
        }
    }
    
    /**
     * post: despliega las contribuciones de mediciones parciales para cada uno de los Módulos
     *       seleccionados en <code>graficoContribuciones</code>
     */
    private void cargarContribuciones() {
        
        this.graficoContribuciones.getData().clear();

        for (Modulo modulo : this.seleccion.obtener()) {
                        
            this.agregarContribuciones(modulo);
        }     
     }

    /**
     * post: agrega las contribuciones para el Módulo indicado.
     */
    private void agregarContribuciones(Modulo modulo) {
        
        Reporte<Discriminante> reporte = this.administradorDeMediciones
                                                .promediarMedicionesPorDiscriminanteDimension(modulo);
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("xx");
        this.graficoContribuciones.getData().add(serie);

        for (ItemReporte<Discriminante> item : reporte.getItems()) {
            
            XYChart.Data<String, Number> datos = new XYChart.Data<>(String.valueOf(item.getClasificador()),
                                                                    item.getValor());
            Node pane = this.dibujarContribuciones(item);
            datos.setNode(pane);
            serie.getData().add(datos);
        }
        serie.setName(this.escribirLeyenda(modulo));
    }

    /**
     * post: construye la representación gráfica de las constribuciones parciales de <code>item</code>.
     * 
     * @return Node a ser insertado en la serie de datos.
     */
    private Node dibujarContribuciones(ItemReporte<Discriminante> item) {
        
        GridPane pane = new GridPane();

        ColumnConstraints columna = new ColumnConstraints();
        columna.setPercentWidth(100);
        
        double incrementoOpacidad = 0.8 / (item.getSubItems().size() + 2);
        double opacidad = 0.0;
        int nro = 0;
        
        for (ItemReporte<Discriminante> subItem : item.getSubItems()) {
            
            RowConstraints fila = new RowConstraints();
            fila.setPercentHeight(subItem.getProporcion() * 100);

            Pane seccion = new Pane();
            seccion.setStyle("-fx-background-color:#000000;");
            opacidad += incrementoOpacidad;
            seccion.setOpacity(opacidad);
            GridPane.setConstraints(seccion, 0, nro++);
            GridPane.setMargin(seccion, new Insets(0,5,0,5));

            pane.getChildren().add(seccion);
            pane.getRowConstraints().add(fila);
            
            this.agregarLeyenda(subItem, seccion);
        }
        pane.getColumnConstraints().add(columna);
        return pane;
    }

    private void agregarLeyenda(ItemReporte<Discriminante> item, Pane seccion) {
        
        Tooltip leyenda = new Tooltip(this.escribirLeyenda(item));
        
        seccion.setOnMouseMoved(new MostrarTooltipEventHandler(leyenda, seccion));
        seccion.setOnMouseEntered(new MostrarTooltipEventHandler(leyenda, seccion));
        seccion.setOnMouseExited(new OcultarTooltipEventHandler(leyenda));
    }
    
    private String escribirTitulo(Modulo modulo) {
        
        return this.formatoTitulo.format(new Object[] { modulo.getId(), modulo.getNombre(), modulo.getVersion() });
    }
    
    private String escribirLeyenda(Modulo modulo) {
        
        String nombreReducido = StringUtils.abbreviateMiddle(modulo.getNombre(), "...", 20);
        
        return this.formatoTitulo.format(new Object[] { modulo.getId(), nombreReducido, modulo.getVersion() });
    }
    
    private String escribirLeyenda(ItemReporte<Discriminante> item) {
        
        return this.formatoLeyenda.format(new Object[] { item.getObjeto().getNombre(), item.getProporcion() });
    }
}
