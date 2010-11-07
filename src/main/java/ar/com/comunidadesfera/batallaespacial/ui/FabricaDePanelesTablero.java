package ar.com.comunidadesfera.batallaespacial.ui;

import java.awt.Image;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.aplicacion.ParticipanteExtendido;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

public class FabricaDePanelesTablero {

    public static final int DIMENSION_CASILLERO = 15;
    
    private FabricaDeImagenes fabricaDeImagenes;
    
    public FabricaDePanelesTablero() {
        
        super();
        this.fabricaDeImagenes = new FabricaDeImagenes();
    }

    public PanelTablero crearTablero(Configuracion<ParticipanteExtendido> configuracion) {
        
        Tablero tablero = configuracion.getTablero(); 
        
        /* crea el panel */
        PanelTablero panel = new PanelTablero(tablero.getFilas(),
                                              tablero.getColumnas(),
                                              DIMENSION_CASILLERO);
        
        /* recorre el Tablero creando la representación de las Piezas */
        Tablero.Iterador it = tablero.iterator();
        
        while (it.hasNext()) {
            
            Pieza pieza = it.next();
            
            if (pieza != null) {
     
                this.crearPieza(pieza, panel, configuracion);
                    
            }
        }
        
        return panel;
    }
    
    
    public void crearPieza(Pieza pieza, PanelTablero panel, 
                           Configuracion<ParticipanteExtendido> configuracion) {
        
        Reporte reporte = pieza.reportar();
        
        String idImagen = this.getIdImagen(reporte);
        
        Image imagen = this.fabricaDeImagenes.getImagen(idImagen);
        
        /* si la imagen no existe la crea */
        if (imagen == null) {

            /* busca el participante de la Civilización correspondiente */
            ParticipanteExtendido participante = configuracion.getParticipante(reporte.getCivilizacion());
            
            this.fabricaDeImagenes.crearImagen(idImagen, DIMENSION_CASILLERO - 2, 
                                               reporte, participante);
            imagen = this.fabricaDeImagenes.getImagen(idImagen);
        }
        
        /* crea la vista de la Pieza y las vincula como
         * Observado - Observador*/
        LabelPieza vistaPieza = new LabelPieza(imagen, 
                                               panel.getPosicion(pieza.getCasillero()));
        pieza.agregarObservador(vistaPieza);

        panel.add(pieza, vistaPieza);

    }
    
    protected String getIdImagen(Reporte reporte) {
        
        StringBuffer idImagen = new StringBuffer();
        
        if (reporte.getEspectro() != null) {
            
            idImagen.append(reporte.getEspectro().name())
                    .append(".");
            
            if (reporte.getCivilizacion() != null) {
                
                idImagen.append(reporte.getCivilizacion().getNombre());
                
            }
            
            if (reporte.getEspectro().equals(Espectro.CONTENEDOR)) {
                
                
                for (Sustancia sustancia : Sustancia.values()) {
                    
                    if (reporte.getCantidadDeSustancia(sustancia) > 0) {
                        
                        idImagen.append(".")
                                .append(sustancia);
                    }
                }
            }
        }
        
        return idImagen.toString();
    }
}
