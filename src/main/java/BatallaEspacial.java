
import ar.com.comunidadesfera.plataformaeducativa.AplicacionBatallaEspacialInteractiva;
import ar.com.comunidadesfera.plataformaeducativa.JuegoInteractivo;

/**
 * Batalla Espacial en la Galaxia Andrómeda.
 * Construida en el paquete default para facilitar su utilización en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial extends JuegoInteractivo{

    public BatallaEspacial() {

        super(AplicacionBatallaEspacialInteractiva.class);
    }

    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return "Batalla Espacial";
    }
}
