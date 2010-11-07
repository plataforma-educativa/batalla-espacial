package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.batallaespacial.Control;
import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.comandos.Atacar;
import ar.com.comunidadesfera.batallaespacial.comandos.Avanzar;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.comandos.TransferirCarga;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.ControlNaveDeCombate;

@RunWith(Theories.class)
public abstract class ControlTest<T extends Control> {

    @DataPoints
    public static final Direccion[] direcciones = Direccion.values();
    @DataPoints
    public static final Sustancia[] sustancias = Sustancia.values();
    @DataPoints
    public static final long[] cargas = new long[] { 0, 10, 20, 100, 400 };

    /**
     * Control utilizado para hacer las pruebas.
     */
    private T control;

    public ControlTest() {
        super();
    }

    /**
     * @return nueva instancia de Control a probar.
     */
    protected abstract T instanciar();

    @Before
    public void inicializar() {

        this.control = this.instanciar();
    }

    protected T getControl() {

        return this.control;
    }

    @Test
    @Theory
    public void atacar(Direccion direccion) {

        Atacar atacar = this.control.atacar(direccion);

        Assert.assertThat(atacar, Matchers.notNullValue());
        Assert.assertThat("dirección", atacar.getDireccion(), 
                          Matchers.equalTo(direccion));
    }

    @Test
    @Theory
    public void avanzar(Direccion direccion) {
        
        Avanzar avanzar = control.avanzar(direccion);

        Assert.assertThat(avanzar, Matchers.notNullValue());
        Assert.assertThat("direccion", avanzar.getDireccion(), Matchers.equalTo(direccion));
        Assert.assertThat("impulso", avanzar.getImpulso(), Matchers.equalTo(1));
    }

    @Test
    @Theory
    public void esperar() {
        
        Comando esperar = control.esperar();
        
        Assert.assertThat(esperar, Matchers.notNullValue());
    }

    @Test
    @Theory
    public void transferirDesde(Direccion desde, Sustancia sustancia, long carga) {
        
        TransferirCarga transferir = 
            control.transferirCarga(desde, Direccion.ORIGEN, sustancia, carga);
        
        comprobarTransferir(transferir, desde, Direccion.ORIGEN, carga);
    }

    @Test
    @Theory
    public void transferirHacia(Direccion hacia, Sustancia sustancia, long carga) {

        TransferirCarga transferir = 
            control.transferirCarga(Direccion.ORIGEN, hacia, sustancia, carga);
        
        comprobarTransferir(transferir, Direccion.ORIGEN, hacia, carga);
    }

    protected void comprobarTransferir(TransferirCarga transferir, Direccion origen, 
                                       Direccion destino, long carga) {
        
        Assert.assertThat("origen", transferir.getDireccionOrigen(), Matchers.equalTo(origen));
        Assert.assertThat("destino", transferir.getDireccionDestino(), Matchers.equalTo(destino));
        Assert.assertThat("carga", transferir.getCarga(), Matchers.equalTo(carga));
    }

    @Test
    @Theory
    @Ignore
    public void transferirDesdeCargaNegativa(Direccion desde, Sustancia sustancia) {
        
        transferirDesde(desde, sustancia, -80L);
    }

    @Test
    @Theory
    @Ignore
    public void transferirHaciaCargaNegativa(Direccion hacia, Sustancia sustancia) {
        
        transferirHacia(hacia, sustancia, -1L);
    }

    @Test
    @Theory
    public void transferirDesdeCargaNula(Direccion desde, Sustancia sustancia) {
        
        transferirDesde(desde, sustancia, 0L);
    }

    @Test
    @Theory
    public void transferirHaciaCargaNula(Direccion hacia, Sustancia sustancia) {
        
        transferirHacia(hacia, sustancia, 0L);
    }
}
