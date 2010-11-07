package ar.com.comunidadesfera.batallaespacial.ui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class FrameAplicacion extends javax.swing.JFrame implements VistaAplicacion {

    /**
     * 
     */
    private static final long serialVersionUID = 1963165432119123603L;

    /**
     * Barra de Menu
     */
    private JMenuBar menuBar;

    /**
     * Menu Partida 
     */
    private JMenu menuPartida;
    
    /**
     * Items del Menu Partida
     */
    private JMenuItem itemMenuCargarPartida; 

    /**
     * Panel Principal
     */
    private PanelJuego panelJuego;
    
    
    /**
     * Dialogo Selector de Archivos
     */
    private JFileChooser fileChooser;
    
    /**
     * Observadores
     */
    private List<Observador> observadores = Collections.synchronizedList(new LinkedList<Observador>());
    
    
    public FrameAplicacion() {
        
        super();
        initGUI();
        
        this.addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            
        });           
    }
    
    private void initGUI() {
        try {

            this.setTitle("Batalla Espacial");
            this.setSize(900,600);
            this.menuBar = new JMenuBar();
            setJMenuBar(this.menuBar);
            {
                this.menuPartida  = new JMenu();
                this.menuBar.add(this.menuPartida);
                this.menuPartida.setText("Partida");
                this.menuPartida.setName("Partida");
                {
                    this.itemMenuCargarPartida = new JMenuItem();
                    this.itemMenuCargarPartida.setText("Cargar");
                    this.menuPartida.add(this.itemMenuCargarPartida);
                    
                    this.itemMenuCargarPartida.addActionListener(
                      
                        new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                
                                FrameAplicacion.this.cargarPartida();
                            }
                        }
                    );
                }
            }

            this.panelJuego = new PanelJuego();
            this.getContentPane().setLayout(new BorderLayout());
            
            this.getContentPane().add(this.panelJuego, BorderLayout.CENTER);
            
            this.fileChooser = new JFileChooser();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    public void setPanelTablero(PanelTablero panelTablero) {
        
        this.setVisible(false);
        
        this.panelJuego.setPanelTablero(panelTablero);

        this.setVisible(true);
    }

    public void registrarObservador(Observador observador) {
        
        this.observadores.add(observador);
        this.panelJuego.registrarObservador(observador);
    }
    
    /**
     * @pre se ha seleccionado <code>archivo</code> como fuente de 
     *       configuración de partida.
     * @post notifica a todos sus observadores.
     * 
     * @param nombre
     */
    private void notificarObservadoresPartidaCargada(File archivo) {
        
        InputStream origenConfiguracion = null;
        
        try {

            for (Observador observador : this.observadores) {
                
                origenConfiguracion = new FileInputStream(archivo);
                observador.partidaCargada(origenConfiguracion);
            }
            
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
            
        } finally {
        
            try {
                
                if (origenConfiguracion != null) {
                    
                    origenConfiguracion.close();
                }
            } catch (IOException error) {
                
                
            }
            
        }
    }
    
    private void cargarPartida() {

        int resultado = this.getFileChooser().showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            
            File file = this.getFileChooser().getSelectedFile();
            System.setProperty("partida.config.path", file.getParent());
            this.notificarObservadoresPartidaCargada(file);
        }
        
        
    }

    public void setRonda(long ronda) {

        this.panelJuego.setRonda(ronda);
    }

    public void revalidate() {

        this.panelJuego.revalidate();
    }

    public void addInforme(Pieza pieza, String[] properties) {
     
        this.panelJuego.addInforme(pieza, properties);
        
    }
}
