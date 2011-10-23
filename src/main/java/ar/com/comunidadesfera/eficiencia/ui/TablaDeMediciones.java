package ar.com.comunidadesfera.eficiencia.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;

public class TablaDeMediciones extends AbstractTableModel {

    private static final int CANTIDAD_DE_COLUMNAS = 4;

    private static final long serialVersionUID = -4541715098454669468L;

    private List<Medicion> mediciones;
    
    public TablaDeMediciones(List<Medicion> mediciones) {

        this.mediciones = mediciones;
    }
    
    @Override
    public int getRowCount() {

        return this.mediciones.size();
    }

    @Override
    public int getColumnCount() {

        return CANTIDAD_DE_COLUMNAS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Medicion medicion = this.mediciones.get(rowIndex);
        
        Object valor;
        
        switch (columnIndex) {
            
            case 0 : 
                valor = medicion.getProblema().getInicio();
                break;
                
            case 1 : 
                valor = medicion.getProblema().getFin();
                break;
                
            case 2 : 
                valor = medicion.getProblema().getDimensiones();
                break;
                
            case 3 : 
                valor = medicion.getResultado();
                break;
                
            default:
                throw new IllegalArgumentException("No existe la columna con índice: " + 
                                                   columnIndex);
        }
        
        return valor;
    }

}
