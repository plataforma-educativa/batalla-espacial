

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoSubalterno;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Reportar;

/**
 * El Radar es la herramienta que provee una Nave para conocer el contexto
 * inmediato de la misma.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Radar {
    
    private static final String DESCRIPCION = "Radar de la Nave";
    
    private PilotoSubalterno piloto;
    
    protected Radar(PilotoSubalterno piloto) {

        this.piloto = piloto;
    }
    
    private Reporte getReporte(Direccion direccion) {
        
        Reportar reportar = new Reportar(direccion);
        
        this.piloto.getOrdenes().add(reportar);
        
        return reportar.getResultado(); 
    }
    
    private Espectro escanear(Direccion direccion) {
        
        return Traductor.instancia()
                    .traducir(this.getReporte(direccion).getEspectro()); 
    }

    private int buscar(Sustancia sustancia, Direccion direccion) {
        
        return (int) this.getReporte(direccion)
                            .getCantidadDeSustancia(Traductor.instancia()
                                                        .traducir(sustancia));
    }
    
    /**
     * @post escanea el casillero contiguo en dirección ESTE y devuelve el Espectro
     *       encontrado.
     */
    public Espectro escanearEste() {

        return this.escanear(Direccion.ESTE); 
    }

    /**
     * @post escanea el casillero contiguo en dirección NORTE y devuelve el Espectro
     *       encontrado.
     */
    public Espectro escanearNorte() {

        return this.escanear(Direccion.NORTE);
    }

    /**
     * @post escanea el casillero contiguo en dirección OESTE y devuelve el Espectro
     *       encontrado.
     */
    public Espectro escanearOeste() {

        return this.escanear(Direccion.OESTE);
    }

    /**
     * @post escanea el casillero contiguo en dirección SUR y devuelve el Espectro
     *       encontrado.
     */
    public Espectro escanearSur() {

        return this.escanear(Direccion.SUR);
    }

    /**
     * @post busca en el casillero contiguo en dirección ESTE y devuelve la 
     *       cantidad de sustancia encontrada. 
     */
    public int buscarAlEste(Sustancia sustancia) {

        return this.buscar(sustancia, Direccion.ESTE);
    }

    /**
     * @post busca en el casillero contiguo en dirección NORTE y devuelve la 
     *       cantidad de sustancia encontrada. 
     */
    public int buscarAlNorte(Sustancia sustancia) {

        return this.buscar(sustancia, Direccion.NORTE);
    }

    /**
     * @post busca en el casillero contiguo en dirección OESTE y devuelve la 
     *       cantidad de sustancia encontrada. 
     */
    public int buscarAlOeste(Sustancia sustancia) {

        return this.buscar(sustancia, Direccion.OESTE);
    }

    /**
     * @post busca en el casillero contiguo en dirección SUR y devuelve la 
     *       cantidad de sustancia encontrada. 
     */
    public int buscarAlSur(Sustancia sustancia) {

        return this.buscar(sustancia, Direccion.SUR);
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return DESCRIPCION;
    }
}
