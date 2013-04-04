package ar.com.comunidadesfera.batallaespacial.galaxias.vialactea;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

public class BatallaEspacialViaLactea implements BatallaEspacial {

    public BatallaEspacialViaLactea() {
      System.out.println("BatallaEspacialViaLactea.BatallaEspacialViaLactea()");
    }
    
    @Override
    public void iniciar() {
        
    }

    @Override
    public void agregarObservador(Observador observador) {
        
    }

    @Override
    public <P extends Participante> Partida<P> jugar(Configuracion<P> configuracion) {

        return null;
    }

}
