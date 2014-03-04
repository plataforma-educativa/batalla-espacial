package ar.com.comunidadesfera.batallaespacial.galaxias.andromeda;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.aplicacion.FabricaDeTableros;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.CivilizacionHumana;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionBasica;
import ar.com.comunidadesfera.batallaespacial.galaxias.BatallaEspacialBasica;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;

public class BatallaEspacialAndromeda extends BatallaEspacialBasica {

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
        configuracion.setTimeout(TimeUnit.SECONDS.toMillis(1));
        Participante participante = new Participante(civilizacion, 10, this.fabricaDePiezas, "0x00AA22");
        configuracion.getParticipantes().add(participante );
        configuracion.setTablero(this.fabricaDeTableros.crearTablero(configuracion, this.getClass().getResourceAsStream("/ar/com/comunidadesfera/batallaespacial/galaxias/andromeda/config/andromeda.tablero")));
        
        return configuracion;
    }
}
