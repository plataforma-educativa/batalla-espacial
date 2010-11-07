package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;


public class PanelJuego extends javax.swing.JPanel implements VistaAplicacion  {

    /**
     * 
     */
    private static final long serialVersionUID = 1131618522615745203L;

    /**
     * Panel de Botones de control
     */
    private JPanel panelControl;
    
    /**
     * Panel central
     */
    private JSplitPane panelCentral;

    /**
     * Botones de ControlAplicacion
     */
    private Map<TipoControl, JButton> controles = new EnumMap<TipoControl, JButton>(TipoControl.class);
    
    /**
     * Informacion sobre la Ronda
     */
    private JLabel labelNroRonda;
    
    
    /**
     * Tablero
     */
    private JScrollPane scrollPanelTablero;
    private PanelTablero panelTablero;

    /**
     * Observadores del panel.
     */
    private List<Observador> observadores = Collections.synchronizedList(new LinkedList<Observador>());
    
    private JPanel panelInformes;
    
    public PanelJuego() {
        super();
        initGUI();
    }
    
    /**
     * 
     *
     */
    private void initGUI() {
        
        try {
            
            this.setLayout(new BorderLayout());
            
            this.panelCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            //this.panelCentral.setDividerLocation(100);
            this.add(this.panelCentral, BorderLayout.CENTER);
            
            /* Informes */
            {
                GridLayout layout = new GridLayout(0,1);
//                layout.setVgap(2);

                this.panelInformes = new JPanel();
                this.panelInformes.setLayout(layout);
                JScrollPane scrollInformes = new JScrollPane(this.panelInformes);
                this.panelCentral.setRightComponent(scrollInformes);
            }
            
            /* Control */
            this.panelControl = new JPanel();
            this.panelControl.setLayout(new GridLayout(1,0,5,5));
            this.panelControl.setPreferredSize(new Dimension(100,25));
            this.panelControl.setBorder(BorderFactory.createEtchedBorder());
            this.add(this.panelControl, BorderLayout.SOUTH);
            {
                JButton boton;
                boton = new JButton();
                boton.setText("||");
                boton.addActionListener(new ControlActionListener(TipoControl.DETENER));
                this.controles.put(TipoControl.DETENER, boton);
                this.panelControl.add(boton);
                
                boton = new JButton();
                boton.setText(">");
                boton.addActionListener(new ControlActionListener(TipoControl.JUGAR));
                this.controles.put(TipoControl.JUGAR, boton);
                this.panelControl.add(boton);
                
                boton = new JButton();
                boton.setText(">|");
                boton.addActionListener(new ControlActionListener(TipoControl.PROXIMA_MOVIDA));
                this.panelControl.add(boton);
                
            }
            {
                Font font = new Font("Verdana",Font.ITALIC,11);
                JPanel panelInfo = new JPanel();
                panelInfo.setFont(font);

                JLabel label = new JLabel("Ronda :");
                label.setFont(null);
                panelInfo.add(label);

                this.labelNroRonda = new JLabel("0");
                labelNroRonda.setFont(null);
                panelInfo.add(this.labelNroRonda);

                this.panelControl.add(panelInfo);
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param panelTablero
     */
    public void setPanelTablero(PanelTablero panelTablero) {

        this.panelTablero = panelTablero;
        
        if (this.scrollPanelTablero != null) {
            
            this.remove(this.scrollPanelTablero);
        }
        
        if (panelTablero != null) {

            this.scrollPanelTablero = new JScrollPane(this.panelTablero);
            this.panelCentral.setDividerLocation(0.8);
            this.panelCentral.setLeftComponent(this.scrollPanelTablero);
        }
            
        this.panelInformes.removeAll();
        
        this.repaint();
    }

    
    /**
     * @pre ninguna.
     * @post agrega el Observador al panel.
     * 
     * @param observador
     */
    public void registrarObservador(Observador observador) {

        this.observadores.add(observador);
    }
    
    /**
     * @pre se ha producido un evento de activacion de control.
     * @post notifica a todos los observadores.
     * 
     * @param control
     */
    protected void notificarObservadoresControlActivado(TipoControl control) {

        for (Observador observador : this.observadores) {
      
            observador.controlActivado(control);
        }
    }
    
    JComponent getControl(TipoControl tipo) {
        
        return this.controles.get(tipo);
    }

    /**
     * 
     *
     */
    class ControlActionListener implements ActionListener {

        private TipoControl tipo;
        
        private ControlActionListener(TipoControl tipo) {
            this.tipo = tipo;
        }
        
        public void actionPerformed(ActionEvent e) {
            
            PanelJuego.this.notificarObservadoresControlActivado(this.tipo);
        }
    }

    public void setRonda(long ronda) {

        this.labelNroRonda.setText(String.valueOf(ronda));
    }

    /**
     * @pre <code>pieza</code> se encuentra dentro del Panel Tablero del Juego.
     * @post agrega un informe de la properties enumeradas por
     *       <code>properties</code> de la Pieza <code>pieza</code>.
     */
    public void addInforme(Pieza pieza, String[] properties) {
        
        
        Informe informe = new InformePieza(pieza);
        
        for (String property : properties) {
            
            informe.addItem(property,property);
        }
 
        
        /* busca el label que identifica la pieza y crea el título */
        LabelPieza imagen = this.panelTablero.getLabelPieza(pieza).clone();
        JLabel texto = new JLabel(pieza.getIdentificacion());
        texto.setFont(new Font("Verdana",Font.BOLD,10));
        
        JPanel titulo = new JPanel(new BorderLayout(5,5));
        titulo.setBorder(BorderFactory.createEtchedBorder());
        titulo.add(imagen, BorderLayout.WEST);
        titulo.add(texto, BorderLayout.CENTER);
        
        /* crea el Panel Informe */
        this.panelInformes.add(new PanelInforme(informe, titulo));
        this.panelCentral.setDividerLocation(0.8);
        
        this.panelInformes.revalidate();
    }
    
    public void revalidate() {
        
        if (this.panelTablero != null) {
            this.panelTablero.revalidate();
        }

        if (this.panelInformes != null) {
            this.panelInformes.revalidate();
        }
        super.revalidate();
    }
    
    
}
