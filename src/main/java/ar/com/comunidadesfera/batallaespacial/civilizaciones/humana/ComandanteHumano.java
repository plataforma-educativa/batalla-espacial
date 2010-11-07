package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Comandante;
import ar.com.comunidadesfera.batallaespacial.Piloto;

/**
 * Comandante de la Civilización Humana.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class ComandanteHumano implements Comandante {

    private static final String NOMBRE_PILOTO = "Piloto Humano ";

    /**
     * Civilización a la que pertenece el Comandante.
     */
    private CivilizacionHumana civilizacion;

    private String nombre;

    /**
     * Lista con todos Subalternos del Comandante originados en la construcción
     * del escuadrón.
     */
    private List<PilotoSubalterno> subalternos = new LinkedList<PilotoSubalterno>();

    public ComandanteHumano(CivilizacionHumana civilizacion, String nombre) {

        this.civilizacion = civilizacion;
        this.nombre = nombre;
    }

    public Piloto[] construirEscuadron(int integrantes)
            throws IllegalArgumentException {

        Piloto[] escuadron = new Piloto[integrantes];

        for (int i = 0; i < integrantes; i++) {

            escuadron[i] = new PilotoSubalterno(new PilotoHumano(this, NOMBRE_PILOTO + i));
        }

        return escuadron;
    }

    public Civilizacion getCivilizacion() {

        return this.civilizacion;
    }

    public String getNombre() {

        return this.nombre;
    }
    
    /**
     * @post agrega el Piloto como Subalterno 
     * @param piloto
     */
    public void reportarSubalterno(PilotoSubalterno piloto) {
        
        this.subalternos.add(piloto);
    }

    public List<PilotoSubalterno> getSubalternos() {
     
        return this.subalternos;
    }
}
