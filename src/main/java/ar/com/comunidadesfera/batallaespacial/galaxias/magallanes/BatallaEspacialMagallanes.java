package ar.com.comunidadesfera.batallaespacial.galaxias.magallanes;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.aplicacion.FabricaDeTableros;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.CivilizacionHumana;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionBasica;
import ar.com.comunidadesfera.batallaespacial.galaxias.BatallaEspacialBasica;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.BaseUnica;

public class BatallaEspacialMagallanes extends BatallaEspacialBasica {

    @Inject
    private FabricaDeTableros fabricaDeTableros;
    
    @Inject
    private FabricaDePiezas fabricaDePiezas;
    
    @Inject
    private CivilizacionHumana civilizacion;
    
    @Override
    public void iniciar() {

        super.iniciar();
        this.jugar(this.crearConfiguracion());
        
    }
    
    protected Configuracion crearConfiguracion() {
        
        ConfiguracionBasica configuracion = new ConfiguracionBasica();
        
        configuracion.setRondas(Integer.MAX_VALUE);
        configuracion.setTimeout(TimeUnit.MILLISECONDS.toMillis(250));
        Participante participante = new Participante(civilizacion, 1, this.fabricaDePiezas, "0x00629A");
        configuracion.getParticipantes().add(participante );
        
        InputStream tablero = this.leerTablero("/laberinto.tablero", "config/magallanes.tablero");
        configuracion.setTablero(this.fabricaDeTableros.crearTablero(configuracion, tablero));
        configuracion.setDefinicionDeBases(new BaseUnica("Laberinto", 0, 0));
        return configuracion;
    }
}
