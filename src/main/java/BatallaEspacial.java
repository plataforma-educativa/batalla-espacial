
import ar.com.comunidadesfera.batallaespacial.galaxias.andromeda.BatallaEspacialAndromeda;

/**
 * Batalla Espacial en la Galaxia Andrómeda.
 * Construida en el paquete default para facilitar su utilización en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial {

    @SuppressWarnings("unused")
    private ar.com.comunidadesfera.batallaespacial.BatallaEspacial implementacion;
    
    /**
     * @post Inicia la ejecución de la batalla espacial.
     */
    public BatallaEspacial() {
     
        this.implementacion = new BatallaEspacialAndromeda();
    }
}
