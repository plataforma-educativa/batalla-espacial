package ar.com.comunidadesfera.batallaespacial.interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class ControladorDocumentacion {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webView;
    
    
    @FXML
    void initialize() throws NoSuchMethodException {

        URL url = this.getClass().getResource("documentacion/BatallaEspacial-EstructurasDeControl-Documentacion.html");
        
        this.webView.getEngine().load(url.toString());
    }
}
