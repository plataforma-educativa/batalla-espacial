package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.piezas.nave.ControlNaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;


public class ControlNaveDeCombateTest extends ControlTest<ControlNaveDeCombate> {

    private NaveDeCombate nave;
    
    public ControlNaveDeCombateTest() {
     
        this.nave = new NaveDeCombate(100);
    }

    protected ControlNaveDeCombate instanciar() {
        
        return this.nave.getControl();
    }
    
    @Test
    public void getControlDesdeCabina() {
        
        Assert.assertThat(this.nave.getControl(), 
                          Matchers.sameInstance(this.nave.getCabinaDeControl()
                                                         .getControl()));
    }

}
