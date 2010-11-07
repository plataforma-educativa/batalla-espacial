package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Queue;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Control;
import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada.CivilizacionSimulada;
import ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada.ComandanteSimulado;
import ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada.PilotoSimulado;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClon;
import ar.com.comunidadesfera.batallaespacial.test.pieza.EscenarioSimulado;

@RunWith( Suite.class )
@SuiteClasses({ 
    ControlNaveDeCombateTest.class       
})
public class NaveDeCombateTest extends NaveTest<NaveDeCombate> {

    private CivilizacionSimulada civilizacion;
    private ComandanteSimulado comandante;
    private PilotoSimulado[] escuadron;
    private PilotoSimulado piloto;
    
    public NaveDeCombateTest() {

        this.civilizacion = new CivilizacionSimulada();
        this.comandante = civilizacion.construirComandante();
        this.escuadron = comandante.construirEscuadron(5);
        this.piloto = this.escuadron[0];
    }
    
    @Override
    protected void comprobarEstadoInicial(NaveDeCombate nave) {
     
        super.comprobarEstadoInicial(nave);
        
        assertThat("Torpedos", nave.getCantidadDeTorpedos(), 
                   equalTo(NaveDeCombate.getCapacidadTorpedos()));
        assertThat("Bodega", nave.getBodega(), notNullValue());
        assertThat("Cabina de Control", nave.getCabinaDeControl(), notNullValue());
        assertThat("Monitor", nave.getMonitor(), notNullValue());
        assertThat("Control", nave.getControl(), notNullValue());
        assertThat("Radar", nave.getRadar(), nullValue());
        assertThat("Notificaciones", nave.getNotificaciones(), notNullValue());
        assertThat("Piloto", nave.getPiloto(), nullValue());

        assertThat("Puntos", nave.getPuntos(), equalTo(100));
    }
    
    @Override
    protected NaveDeCombate instanciar() {

        return new NaveDeCombate(100);
    }

    @Override
    protected int cambiarParaObservar(NaveDeCombate pieza) {

        pieza.setCantidadDeTorpedos(50);
        pieza.setCantidadDeTorpedos(80);

        return 2;
    }
    
    @Override
    protected ComprobarClon<NaveDeCombate> comprobarClon() {

        return new ComprobarClonNaveDeCombate<NaveDeCombate>("naveDeCombate");
    }
    
    @Test
    public void torpedos() {
        
        NaveDeCombate nave = this.instanciar();
        
        Assert.assertThat("cantidad de torpedos", nave.getCantidadDeTorpedos(),
                          Matchers.equalTo(NaveDeCombate.getCapacidadTorpedos()));
        
        nave.setCantidadDeTorpedos(100);
        Assert.assertThat("cantidad de torpedos", nave.getCantidadDeTorpedos(),
                          Matchers.equalTo(100));
    }
    
    @Test
    public void pilotear() {
     
        NaveDeCombate nave = this.instanciar();
        nave.setPiloto(this.piloto);
        
        Assert.assertThat("piloto asignado", nave.getPiloto(), 
                          Matchers.sameInstance((Piloto) this.piloto));
        
        Assert.assertThat("civilizacion", nave.getCivilizacion(),
                Matchers.sameInstance(this.piloto.getCivilizacion()));

        CabinaDeControl cabina = this.piloto.getCabinaDeControl();
        Assert.assertThat("cabina dada al piloto", cabina, 
                          Matchers.sameInstance(nave.getCabinaDeControl()));

        Assert.assertThat("control", cabina.getControl(), 
                          Matchers.sameInstance((Control) nave.getControl()));
        Assert.assertThat("monitor", cabina.getMonitor(), 
                          Matchers.sameInstance(nave.getMonitor()));
        Assert.assertThat("radar", cabina.getRadar(),
                          Matchers.sameInstance(nave.getRadar()));
    }
    
    @Test
    public void notificar() {
        
        NaveDeCombate nave = this.instanciar();
        
        Queue<Notificacion> notificaciones = nave.getNotificaciones();
        
        Assert.assertThat("sin notificaciones", notificaciones.isEmpty(),
                          Matchers.equalTo(true));
        
        nave.modificarPuntos(- nave.getPuntos(), 
                             new EscenarioSimulado(Notificacion.ATAQUE));
        
        Assert.assertThat("notificaciones", notificaciones.poll(),
                          Matchers.equalTo(Notificacion.ATAQUE));
        
        nave.modificarPuntos(nave.getPuntos(), 
                             new EscenarioSimulado(Notificacion.ATAQUE,
                                                   Notificacion.CHOQUE));

        Assert.assertThat("notificaciones", notificaciones,
                          Matchers.hasItems(Notificacion.ATAQUE, 
                                            Notificacion.CHOQUE));
        
    }
}
