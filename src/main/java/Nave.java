import java.util.List;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.CivilizacionHumana;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ComandanteHumano;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoSubalterno;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Atacar;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Avanzar;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Orden;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.TransferirCarga;

/**
 * Nave de la Civilizacion Humana.
 * Brinda acceso inmediato al control de una Nave piloteada por Humanos.
 * Construida en el paquete default para facilitar su utilización en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Nave {

    private static final String DESCRIPCION = "Nave a la espera de comandos";
    
    private static int navesCreadas = 0;

    private PilotoSubalterno piloto;
    
    private Radar radar;
    
    private Monitor monitor;

    /**
     * @pre se debe haber creado una Batalla Espacial en la que participe
     * la Civilización Humana.
     * @post perteneciente a la Batalla Espacial en ejecución.
     */
    public Nave() {

        CivilizacionHumana civilizacion = CivilizacionHumana.getInstancia();
        ComandanteHumano comandante = civilizacion.getComandante();

        List<PilotoSubalterno> subalternos = comandante.getSubalternos();

        if (navesCreadas < subalternos.size()) {

            this.piloto = subalternos.get(navesCreadas);
            
            this.radar = new Radar(this.piloto);
            
            this.monitor = new Monitor(this.piloto);
            
            navesCreadas++;

        } else {

            throw new IllegalStateException("No existen más naves disponibles");
        }
    }

    private void ordenar(Orden orden) {

        this.piloto.getOrdenes().offer(orden);
    }
    
    /**
     * @post ordena el despegue desde la Base.
     */
    public void despegar() {

        /* se autoriza el Plan de Vuelo del Piloto */
        this.piloto.getPlanDeVuelo().autorizar();    
    }

    private void avanzar(Direccion direccion) {

        this.ordenar(new Avanzar(direccion));
    }

    /**
     * @pre no existe otra Pieza ocupando el casillero destino. 
     * @post mueve la Nave al casillero contiguo en dirección NORTE.
     */
    public void avanzarAlNorte() {

        this.avanzar(Direccion.NORTE);
    }

    /**
     * @pre no existe otra Pieza ocupando el casillero destino. 
     * @post mueve la Nave al casillero contiguo en dirección SUR.
     */
    public void avanzarAlSur() {

        this.avanzar(Direccion.SUR);
    }

    /**
     * @pre no existe otra Pieza ocupando el casillero destino. 
     * @post mueve la Nave al casillero contiguo en dirección ESTE.
     */
    public void avanzarAlEste() {

        this.avanzar(Direccion.ESTE);
    }

    /**
     * @pre no existe otra Pieza ocupando el casillero destino. 
     * @post mueve la Nave al casillero contiguo en dirección OESTE.
     */
    public void avanzarAlOeste() {

        this.avanzar(Direccion.OESTE);
    }

    private void atacar(Direccion direccion) {

        this.ordenar(new Atacar(direccion));
    }

    /**
     * @pre existe otra Pieza ocupando el casillero destinatario del ataque. 
     * @post ataca a la Pieza en el casillero contiguo en dirección NORTE.
     */
    public void atacarAlNorte() {

        this.atacar(Direccion.NORTE);
    }

    /**
     * @pre existe otra Pieza ocupando el casillero destinatario del ataque. 
     * @post ataca a la Pieza en el casillero contiguo en dirección SUR.
     */
    public void atacarAlSur() {

        this.atacar(Direccion.SUR);
    }

    /**
     * @pre existe otra Pieza ocupando el casillero destinatario del ataque. 
     * @post ataca a la Pieza en el casillero contiguo en dirección ESTE.
     */
    public void atacarAlEste() {

        this.atacar(Direccion.ESTE);
    }

    /**
     * @pre existe otra Pieza ocupando el casillero destinatario del ataque. 
     * @post ataca a la Pieza en el casillero contiguo en dirección OESTE.
     */
    public void atacarAlOeste() {

        this.atacar(Direccion.OESTE);
    }

    private void transferir(Direccion origen, Direccion destino, 
                            Sustancia sustancia, int carga) {
        
        this.ordenar(new TransferirCarga(origen, destino, 
                                         Traductor.instancia().traducir(sustancia), 
                                         carga));
    }
    
    private void cargarDesde(Direccion direccion, Sustancia sustancia, int cantidad) {
        
        this.transferir(direccion, Direccion.ORIGEN, sustancia, cantidad);
    }
    
    /**
     * @pre existe otra Pieza ocupando el casillero destino con
     *      más sustancia que la cantidad indicada y existe capacidad
     *      disponible en la bodega de la Nave.  
     * @post traslada la cantidad de sustancia desde el casillero contiguo
     *       en dirección NORTE hasta la Nave.
     */
    public void cargarDesdeNorte(Sustancia sustancia, int cantidad) {
        
        this.cargarDesde(Direccion.NORTE, sustancia, cantidad);
    }

    /**
     * @pre existe otra Pieza ocupando el casillero destino con
     *      más sustancia que la cantidad indicada y existe capacidad
     *      disponible en la bodega de la Nave.  
     * @post traslada la cantidad de sustancia desde el casillero contiguo
     *       en dirección SUR hasta la Nave.
     */
    public void cargarDesdeSur(Sustancia sustancia, int cantidad) {
        
        this.cargarDesde(Direccion.SUR, sustancia, cantidad);
    }
    
    /**
     * @pre existe otra Pieza ocupando el casillero destino con
     *      más sustancia que la cantidad indicada y existe capacidad
     *      disponible en la bodega de la Nave.  
     * @post traslada la cantidad de sustancia desde el casillero contiguo
     *       en dirección ESTE hasta la Nave.
     */
    public void cargarDesdeEste(Sustancia sustancia, int cantidad) {
        
        this.cargarDesde(Direccion.ESTE, sustancia, cantidad);
    }
    
    /**
     * @pre existe otra Pieza ocupando el casillero destino con
     *      más sustancia que la cantidad indicada y existe capacidad
     *      disponible en la bodega de la Nave.  
     * @post traslada la cantidad de sustancia desde el casillero contiguo
     *       en dirección OESTE hasta la Nave.
     */
    public void cargarDesdeOeste(Sustancia sustancia, int cantidad) {
        
        this.cargarDesde(Direccion.OESTE, sustancia, cantidad);
    }
    
    private void descargarEn(Direccion direccion, Sustancia sustancia, int cantidad) {
        
        this.transferir(Direccion.ORIGEN, direccion, sustancia, cantidad);
    }
    
    /**
     * @pre existe otra Pieza ocupando el casillero destino con capacidad
     *      disponible.  
     * @post traslada la cantidad de sustancia desde la Nave hasta el casillero 
     *       contiguo en dirección NORTE hasta la Nave.
     */
    public void descargarEnNorte(Sustancia sustancia, int cantidad) {
        
        this.descargarEn(Direccion.NORTE, sustancia, cantidad);
    }

    /**
     * @pre existe otra Pieza ocupando el casillero destino con capacidad
     *      disponible.  
     * @post traslada la cantidad de sustancia desde la Nave hasta el casillero 
     *       contiguo en dirección SUR hasta la Nave.
     */
    public void descargarEnSur(Sustancia sustancia, int cantidad) {
        
        this.descargarEn(Direccion.SUR, sustancia, cantidad);
    }
    
    /**
     * @pre existe otra Pieza ocupando el casillero destino con capacidad
     *      disponible.  
     * @post traslada la cantidad de sustancia desde la Nave hasta el casillero 
     *       contiguo en dirección ESTE hasta la Nave.
     */
    public void descargarEnEste(Sustancia sustancia, int cantidad) {
        
        this.descargarEn(Direccion.ESTE, sustancia, cantidad);
    }
    
    /**
     * @pre existe otra Pieza ocupando el casillero destino con capacidad
     *      disponible.  
     * @post traslada la cantidad de sustancia desde la Nave hasta el casillero 
     *       contiguo en dirección NORTE hasta la Nave.
     */
    public void descargarEnOeste(Sustancia sustancia, int cantidad) {
        
        this.descargarEn(Direccion.OESTE, sustancia, cantidad);
    }
    
    /**
     * @post devuelve el Radar de la Nave que permite conocer el contexto
     *       del Tablero.
     */
    public Radar obtenerRadar() {
        
        return this.radar;
    }

    /**
     * @post devuelve el Monitor de la Nave que permite conocer el estado
     *       general del la Nave.
     */
    public Monitor obtenerMonitor() {
        
        return this.monitor;
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return DESCRIPCION;
    }
}
