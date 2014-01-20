package ar.com.comunidadesfera.batallaespacial.interfaz.eficiencia;

import javafx.beans.value.ObservableValueBase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;

@Deprecated
public class Leyenda extends ObservableValueBase<Leyenda>{
    
    private Modulo modulo;
    private ObservableList<String> estilo;

    private Node nodo;
    
    public Leyenda(Modulo modulo) {
        
        this.modulo = modulo;
//        this.estilo = FXCollections.observableArrayList();
    }

    public Modulo getModulo() {
        
        return this.modulo;
    }
    
    public void setNodo(Node nodo) {
        
        this.nodo = nodo;
    }
    
    public Node getNode() {
        
        return this.nodo;
    }
    
    @Override
    public Leyenda getValue() {

        return this;
    }

    public void setEstilo(ObservableList<String> estilo) {
        
        this.estilo = estilo;
//        this.estilo.addAll(estilo);
    }
    
    public ObservableList<String> getEstilo() {
        
        return estilo;
    }

}
