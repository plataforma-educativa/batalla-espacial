
import ar.com.comunidadesfera.batallaespacial.galaxias.andromeda.BatallaEspacialAndromeda;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * Batalla Espacial en la Galaxia Andrómeda.
 * Construida en el paquete default para facilitar su utilización en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial 
    implements ar.com.comunidadesfera.batallaespacial.BatallaEspacial {

    private static final String DESCRIPCION = "Batalla Espacial iniciada";
    
    private ar.com.comunidadesfera.batallaespacial.BatallaEspacial implementacion;
    
    public BatallaEspacial() {
     
        this.implementacion = new BatallaEspacialAndromeda();
    }

    @Override
    public void iniciar() {

        this.implementacion.iniciar();
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return DESCRIPCION;
    }
    
    @Override
    public void agregarObservador(Observador observador) {
     
        this.implementacion.agregarObservador(observador);
    }
    
    @Override
    public <P extends Participante> Partida<P> jugar(Configuracion<P> configuracion) {
     
        return this.implementacion.jugar(configuracion);
    }
    
    public static void main(String[] args) {
        
        new BatallaEspacial().iniciar();
    }
}
