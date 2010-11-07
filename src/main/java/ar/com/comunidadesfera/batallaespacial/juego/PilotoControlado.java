package ar.com.comunidadesfera.batallaespacial.juego;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.piezas.ComandoAbstracto;

public class PilotoControlado implements Piloto {

    private Piloto piloto;
    
    private boolean jugando;
    
    public PilotoControlado(Piloto piloto) {
        super();
        this.piloto = piloto;
        this.setJugando(true);
    }

    public Civilizacion getCivilizacion() {
        return piloto.getCivilizacion();
    }

    public String getNombre() {
        return piloto.getNombre();
    }

    public Comando proximoComando() {
        
        return this.isJugando() ? piloto.proximoComando() : ComandoAbstracto.ESPERAR;
    }

    public void setCabinaDeControl(CabinaDeControl cabina) {
        piloto.setCabinaDeControl(cabina);
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public Piloto getPiloto() {
        return piloto;
    }
}
