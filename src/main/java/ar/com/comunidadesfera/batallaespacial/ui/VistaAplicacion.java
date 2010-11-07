package ar.com.comunidadesfera.batallaespacial.ui;

import java.io.InputStream;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;


public interface VistaAplicacion {

    void setPanelTablero(PanelTablero panelTablero);
        
    void setRonda(long ronda);

    void addInforme(Pieza pieza, String[] properties);
    
    void setVisible(boolean visible);
    
    void revalidate();
    
    void registrarObservador(Observador observador);

    interface Observador {
        
        void controlActivado(TipoControl tipoControl);
        
        void partidaCargada(InputStream origenConfiguracion);
    }
}
