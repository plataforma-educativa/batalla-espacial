package ar.com.comunidadesfera.batallaespacial.test.juego;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.juego.Detectable;
import ar.com.comunidadesfera.batallaespacial.test.matchers.ConfrontadorDeReporte;

/**
 * Test básico para toda implementación de Detectable.
 * Cada implementación debe extender esta clase.
 * 
 * @author Mariano Tugnarelli
 *
 * @param <T>
 */
public abstract class DetectableTest<T extends Detectable> {

    private T detectable;
    
    @Before
    public void inicializar() {
        
        this.detectable = this.instanciar();
    }

    @Test
    public final void reportar() {
        
        Reporte reporte = this.detectable.reportar();
        
        Assert.assertThat(reporte, this.getConfrontador());
    }

    /**
     * @return instancia a probar.
     */
    protected abstract T instanciar();

    
    /**
     * @post crea y devuelve el Matcher para el reporte.
     * 
     * @param reporte
     */
    protected ConfrontadorDeReporte<T> getConfrontador() {

        return new ConfrontadorDeReporte<T>(this.detectable);
    }
}
