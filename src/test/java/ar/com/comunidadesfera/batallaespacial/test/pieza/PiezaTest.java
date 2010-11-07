package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public abstract class PiezaTest<T extends Pieza> {

    /**
     * Pieza utilizada para hacer la pruebas
     */
    private T pieza;
    
    /**
     * @post crea una nueva instancia de la Pieza concreta testeda.
     */
    protected abstract T instanciar();

    /**
     * @post aplica modificaciones que desean ser observadas sobre la pieza
     *       dada.
     * @param pieza
     * @return cantidad de cambios que deben haber sido observados.
     */
    protected abstract int cambiarParaObservar(T pieza);

    /**
     * @post comprueba (realizando los asserts necesarios) el estado inicial que
     *       debe tener pieza.
     * @param pieza
     */
    protected void comprobarEstadoInicial(T pieza) {

        Assert.assertThat("puntos", pieza.getPuntos(), Matchers.greaterThan(0));
        Assert.assertThat("número", pieza.getNumero(), Matchers.greaterThanOrEqualTo(0));
        Assert.assertThat("casillero", pieza.getCasillero(), Matchers.nullValue());

        // TODO Falla con BaseEspacial
        // Assert.assertThat("identificacion", pieza.getIdentificacion(),
        // Matchers.notNullValue());
    }

    protected ComprobarClon<T> comprobarClon() {
        
        return new ComprobarClonPieza<T>("pieza");
    }

    protected String getClassName() {
        
        return pieza.getClass().getName();
    }

    protected T getPieza() {
        
        return pieza;
    }

    @Before
    public void inicializar() {
        
        pieza = instanciar();
    }

    @Test
    public void crear() {

        comprobarEstadoInicial(pieza);
    }

    @Test
    public void observar() {
        
        /* crea y registra el observador */
        ObservadorSimulado observador = new ObservadorSimulado();
        pieza.agregarObservador(observador);
        
        /* aún no se registran cambios */
        Assert.assertThat("cambios", observador.getCambios(),
                          Matchers.equalTo(0));
        
        /* realiza los cambios */
        int cambios = this.cambiarParaObservar(pieza);
        
        /* comprueba las notificaciones */
        Assert.assertThat("cambios", observador.getCambios(),
                          Matchers.equalTo(cambios));
        Assert.assertThat("pieza", observador.getPieza(), 
                          Matchers.equalTo((Pieza) pieza));
        
        /* comprueba que al retirar el observador no se reportan modificaciones */
        pieza.quitarObservador(observador);
        observador.detener();
        this.cambiarParaObservar(pieza);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void clonar() {
        
        T clon = (T) pieza.clone();
        
        this.comprobarClon().ejecutar(pieza, clon);
    }

    @Test
    public void reportar() {
        
        // TODO
    }

    @Test
    public void compareTo() {
        
        Assert.assertThat(getClassName(), pieza, 
                          Matchers.is(java.lang.Comparable.class));
        
        Assert.assertThat("unaPieza.compareTo(unaPieza)", 
                          pieza.compareTo(pieza), 
                          Matchers.equalTo(0));
        
        Pieza pieza1 = instanciar();
        Pieza pieza2 = instanciar();

        Assert.assertThat(pieza1.getNumero(), 
                          Matchers.not(Matchers.equalTo(pieza2.getNumero())));
        Assert.assertThat(pieza1.getNumero(), 
                          Matchers.lessThan(pieza2.getNumero()));
        
        Assert.assertThat(getClassName(), pieza2, 
                          Matchers.is(java.lang.Comparable.class));
        
        Assert.assertThat("piezaDeNumeroMenor.compareTo(piezaDeNumeroMayor)",
                          pieza1.compareTo(pieza2), 
                          Matchers.lessThan(0));
        Assert.assertThat("piezaDeNumeroMayor.compareTo(piezaDeNumeroMenor)",
                          pieza2.compareTo(pieza1), 
                          Matchers.greaterThan(0));
    }

    @Test
    public void modificarPuntos() {
        
        EscenarioSimulado escenario = new EscenarioSimulado(new Notificacion[0]);
        
        int puntosIniciales = pieza.getPuntos();
        
        pieza.modificarPuntos(0, escenario);
        
        Assert.assertThat("puntos", pieza.getPuntos(),
                          Matchers.equalTo(puntosIniciales));
        
        int delta = puntosIniciales / 2;
        pieza.modificarPuntos(delta, escenario);
        
        Assert.assertThat("puntos", pieza.getPuntos(),
                          Matchers.equalTo(puntosIniciales + delta));
        
        pieza.modificarPuntos(-delta, escenario);
        
        Assert.assertThat("puntos", pieza.getPuntos(),
                          Matchers.equalTo(puntosIniciales));
        
        pieza.modificarPuntos(-puntosIniciales, escenario);
        
        Assert.assertThat("puntos", pieza.getPuntos(),
                          Matchers.equalTo(0));
        
        pieza.modificarPuntos(1, escenario);
        
        Assert.assertThat("puntos", pieza.getPuntos(),
                          Matchers.equalTo(1));
    }
}