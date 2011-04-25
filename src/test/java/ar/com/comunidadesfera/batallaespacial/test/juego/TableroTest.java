package ar.com.comunidadesfera.batallaespacial.test.juego;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Casillero;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Iterador;
import ar.com.comunidadesfera.batallaespacial.test.pieza.PiezaSimulada;

@RunWith(Parameterized.class)
public class TableroTest {

    private static final Random random = new Random();

    /**
     * @return dimensiones utilizadas para ejecutar las pruebas
     */
    @Parameters
    public static final Collection<Integer[]> getDimensiones(){
        
       return Arrays.asList(new Integer[][]{
                                             {1, 1},
                                             {1, 13},
                                             {1, 100},
                                             {3, 1},
                                             {13, 1},
                                             {13, 13},
                                             {23, 47},                                             
                                             {102, 25},
                                            });
    }

    /**
     * Instancia de Tablero sobre la que se ejecutan las pruebas.
     */
    private Tablero tablero;
    
    private final int filas;
    
    private final int columnas;

    private List<Pieza> piezas;
    
    
    public TableroTest(int filas, int columnas) {

        this.filas = filas;
        this.columnas = columnas;
    }
    
    @Before
    public void inicializar() {
        this.piezas = this.crearPiezas();
    }
    
    @Test
    public void crearTableroDefinido() {

        tablero = new Tablero(filas, columnas);
        
        Assert.assertThat("definido", tablero.estaDefinido(), Matchers.is(true));
        Assert.assertThat("filas", tablero.getFilas(), Matchers.equalTo(filas));
        Assert.assertThat("columnas", tablero.getColumnas(), Matchers.equalTo(columnas));
        
        Iterador iterador = tablero.iterator();
        
        int cantidad = 0;
        while (iterador.hasNext()) {
            
            Pieza pieza = iterador.get();
            Assert.assertThat(pieza, Matchers.sameInstance(iterador.next()));
            cantidad++;
        }
        
        Assert.assertThat("casilleros", cantidad,
                          Matchers.equalTo(filas * columnas));
    }

    @Test
    public void crearTablero() throws CasilleroInvalidoException {    
     
        tablero = new Tablero();
        
        this.comprobarPosicionamientoDePiezas();
        
        tablero.definir();
        
        Assert.assertThat("definido", tablero.estaDefinido(),
                          Matchers.is(true));
    }

    @Test(expected = CasilleroInvalidoException.class)
    public void moverHaciaPosicionInvalida() throws CasilleroInvalidoException {
        
        tablero = new Tablero();
        
        this.comprobarPosicionamientoDePiezas();
        
        tablero.definir();
        
        Assert.assertThat("definido", tablero.estaDefinido(),
                          Matchers.is(true));
        
        tablero.iterator().move(filas, columnas);
    }
    
    @Test
    public void recorrer() throws CasilleroInvalidoException {
        
        tablero = new Tablero();
        
        this.comprobarPosicionamientoDePiezas();
        
        tablero.definir();
        
        Iterador iterador = tablero.iterator();
        
        while (iterador.hasNext()) {
         
            Pieza pieza = iterador.next();
            
            Assert.assertThat("pieza", pieza, Matchers.notNullValue());
        }
    }
    
    @Test
    public void retirarPieza() throws CasilleroInvalidoException {
        
        tablero = new Tablero();
        
        this.posicionarPiezas();
        
        tablero.definir();
       
        List<Pieza> removidas = new LinkedList<Pieza>();
        removidas.add(this.piezas.get(0));

        Pieza pieza = this.piezaAlAzar();
        
        Assert.assertThat(tablero, Matchers.hasItem(pieza));
        
        tablero.retirarPieza(pieza);

        Assert.assertThat(tablero, Matchers.not(Matchers.hasItem(pieza)));
        
    }
    
    @Test
    public void retirarTodasLasPieza() throws CasilleroInvalidoException {
        
        tablero = new Tablero();
        
        this.posicionarPiezas();
        
        tablero.definir();
       
        List<Pieza> removidas = new LinkedList<Pieza>();
        removidas.add(this.piezas.get(0));

        List<Pieza> piezas = new LinkedList<Pieza>(this.piezas);
        Collections.shuffle(piezas);
        
        for (Pieza pieza : piezas) {
        
            Assert.assertThat(tablero, Matchers.hasItem(pieza));
            
            tablero.retirarPieza(pieza);
    
            Assert.assertThat(tablero, Matchers.not(Matchers.hasItem(pieza)));
        }
    }
    
    private Pieza piezaAlAzar() {

        return this.piezas.get(random.nextInt(this.piezas.size()));
    }
    
    private List<Pieza> crearPiezas() {
        
        List<Pieza> piezas = new LinkedList<Pieza>();
        
        for (int i = 0; i < (filas * columnas); i++) {
            
            piezas.add(new PiezaSimulada(100));
        }
        
        return piezas;
    }
    
    private void posicionarPiezas() throws CasilleroInvalidoException {

        Iterador iterador = tablero.iterator();
        
        int fila = 0;
        int columna = 0;
        
        for(Pieza pieza : piezas)  {
            
            iterador.move(fila, columna);
            
            tablero.colocarPieza(pieza, iterador);
     
            columna++;
            
            if (columna >= columnas) {
                
                fila++;
                columna = 0;
            }
        }
    }
    
    private void comprobarPosicionamientoDePiezas()
        throws CasilleroInvalidoException {
        
        Assert.assertThat("definido", tablero.estaDefinido(), Matchers.is(false));
        Assert.assertThat("cantidad de casilleros", tablero.getCantidadDeCasilleros(),
                          Matchers.equalTo(1L));
        
        this.posicionarPiezas();
        
        Assert.assertThat("definido",
                          tablero.estaDefinido(), Matchers.is(false));
        Assert.assertThat("cantidad de casilleros", tablero.getCantidadDeCasilleros(), 
                          Matchers.equalTo((long) filas * columnas));
        Assert.assertThat("filas", tablero.getFilas(),
                          Matchers.equalTo(filas));
        Assert.assertThat("columnas", tablero.getColumnas(),
                          Matchers.equalTo(columnas));

        
        Iterador iterador = tablero.iterator();
        
        for (Pieza pieza : tablero) {
            
            Casillero casillero = pieza.getCasillero(); 
            
            Assert.assertThat("casillero", casillero,
                              Matchers.notNullValue());
            Assert.assertThat("pieza", casillero.getPieza(),
                              Matchers.equalTo(pieza));
            
            iterador.move(casillero.getFila(), casillero.getColumna());
            
            Assert.assertThat("pieza",
                              pieza, Matchers.sameInstance(iterador.get()));
        }
    }

}
