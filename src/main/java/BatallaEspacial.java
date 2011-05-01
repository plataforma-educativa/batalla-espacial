
import ar.com.comunidadesfera.batallaespacial.galaxias.andromeda.BatallaEspacialAndromeda;

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

    private ar.com.comunidadesfera.batallaespacial.BatallaEspacial implementacion;
    
    public BatallaEspacial() {
     
        this.implementacion = new BatallaEspacialAndromeda();
    }

    @Override
    public void iniciar() {

        this.implementacion.iniciar();
    }
}
