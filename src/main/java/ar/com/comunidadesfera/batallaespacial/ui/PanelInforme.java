package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.BorderLayout;
import java.awt.Dimension; 
import java.awt.Font;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelInforme extends javax.swing.JPanel {
    
    private JTable tabla;
    private JPanel titulo;
    private TableModel informe;

    /**
     * Crea el panel para un informe dado.
     * @param reporte
     */
    public PanelInforme(TableModel informe, JPanel titulo) {
        super();
        this.informe = informe;
        this.titulo = titulo;
        initGUI();
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);

            {
                this.tabla = new JTable(this.informe);
                this.tabla.setCellSelectionEnabled(false);
                this.tabla.setFont(new Font("Verdana",Font.PLAIN,11));
                this.tabla.setRowSelectionAllowed(false);
                this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                this.tabla.setEnabled(false);
                this.add(this.tabla, BorderLayout.CENTER);
                JTableHeader header = this.tabla.getTableHeader();
                
                TableColumnModel columnModel = header.getColumnModel();
                header.setFont(new Font("Verdana",Font.PLAIN,8));
                
                for (int nro = 0; nro < columnModel.getColumnCount(); nro++) {
                    
                    columnModel.getColumn(nro).setHeaderValue("···");
                }
                this.add(header, BorderLayout.SOUTH);
            }
            {
                this.add(this.titulo, BorderLayout.NORTH);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

}


