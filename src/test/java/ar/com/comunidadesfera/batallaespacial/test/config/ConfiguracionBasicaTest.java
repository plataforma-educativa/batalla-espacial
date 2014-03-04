package ar.com.comunidadesfera.batallaespacial.test.config;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionBasica;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeBases;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeNaves;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.Reglamentacion;

public class ConfiguracionBasicaTest {

    private ConfiguracionBasica configuracion;

    @Before
    public void crear() {
        
        this.configuracion = new ConfiguracionBasica();
    }
    
    @Test
    public void estrategiaDeDefinicionDeBasesPorDefecto() {
        
        assertThat(configuracion.getDefinicionDeBases(),
                   instanceOf(DefinicionDeBases.class));
    }
    
    @Test
    public void estrategiaDeDefinicionDeNavesPorDefecto() {

        assertThat(configuracion.getDefinicionDeNaves(),
                   instanceOf(DefinicionDeNaves.class));
    }

    @Test
    public void estrategiaDeReglamentacionPorDefecto() {
        
        assertThat(configuracion.getReglamentacion(),
                   instanceOf(Reglamentacion.class));
    }
    
    @Test
    public void cambiarDefinicionNaves() {
        
        DefinicionDeNaves estrategia = mock(DefinicionDeNaves.class);
        
        configuracion.setDefinicionDeNaves(estrategia);
        
        assertThat(configuracion.getDefinicionDeNaves(), sameInstance(estrategia));
    }
    
    @Test
    public void cambiarDefinicionBases() {
        
        DefinicionDeBases estrategia = mock(DefinicionDeBases.class);
        
        configuracion.setDefinicionDeBases(estrategia);
        
        assertThat(configuracion.getDefinicionDeBases(), sameInstance(estrategia));
    }
    
    @Test
    public void cambiarReglamentacion() {
        
        Reglamentacion estrategia = mock(Reglamentacion.class);
        
        configuracion.setReglamentacion(estrategia);
        
        assertThat(configuracion.getReglamentacion(), sameInstance(estrategia));
    }
}
