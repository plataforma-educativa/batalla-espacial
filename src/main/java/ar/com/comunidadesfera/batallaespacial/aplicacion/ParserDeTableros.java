package ar.com.comunidadesfera.batallaespacial.aplicacion;

import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Pattern;

import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

/**
 * Implementación de una Fábrica de Tableros a partir de un origen de datos.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ParserDeTableros implements FabricaDeTableros {

    /**
     * Origen de datos para la construcción de Tableros.
     */
    private Reader origen;
    
    /**
     * Cadena de separación entre columnas.
     */
    private Pattern delimitador = Pattern.compile("\\|");
    
    /**
     * Instancia utilizada para fabricar las Piezas.
     */
    private FabricaDePiezas fabricaDePiezas;
    
    
    public ParserDeTableros(Reader origen, String delimitador) {
     
        super();
        this.setOrigen(origen);
        this.setDelimitador(delimitador);
    }


    /**
     * @pre ha sido asignado el Reader origen de datos y la Fabrica de 
     *       Piezas a utilizar.
     * @post crea el Tablero a partir del origen especificado.
     */
    public Tablero crearTablero() {
        
        Tablero tablero = new Tablero();
        Tablero.Iterador iterador = tablero.iterator();
        
        Scanner scanner = new Scanner(this.getOrigen());

        try {
            
            int nroFila = 0;

            /* cada línea es una fila del tablero */
            while (scanner.hasNextLine()) {

                String fila = scanner.nextLine();
                
                int nroColumna = 0;
                
                /* utiliza el delimitador para encontrar casilleros */
                for (String casillero : this.delimitador.split(fila)) {
                    
                    Pieza pieza = this.getFabricaDePiezas().crearPieza(casillero);
                    
                    /* agrega la pieza al tablero */
                    if (pieza != null) {
                        
                        /* aplica un factor de aleatoriedad a la Pieza */
                        pieza.distorsionar();
                        
                        iterador.move(nroFila, nroColumna);
                        
                        tablero.colocarPieza(pieza, iterador);
                    }
                
                    nroColumna++;
                }
                
                nroFila++;
            }
            
        } catch (CasilleroInvalidoException error) {
            
            /* no debería suceder porque el tablero no tiene las dimensiones
             * aún fijas */
            error.printStackTrace();
            
        } finally {
         
            scanner.close();
        }

        /* fija las dimensiones del Tablero */
        tablero.definir();
        
        return tablero;
    }


    public String getDelimitador() {
        return this.delimitador.pattern();
    }


    public void setDelimitador(String delimitador) {
        this.delimitador = Pattern.compile(delimitador);
    }


    public FabricaDePiezas getFabricaDePiezas() {
        return fabricaDePiezas;
    }


    public void setFabricaDePiezas(FabricaDePiezas fabricaDePiezas) {
        this.fabricaDePiezas = fabricaDePiezas;
    }


    public Reader getOrigen() {
        return origen;
    }


    public void setOrigen(Reader origen) {
        this.origen = origen;
    }

}
