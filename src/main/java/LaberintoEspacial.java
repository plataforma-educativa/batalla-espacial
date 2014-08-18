import ar.com.comunidadesfera.plataformaeducativa.AplicacionBatallaEspacialLaberinto;
import ar.com.comunidadesfera.plataformaeducativa.JuegoInteractivo;



/**
 * Batalla Espacial en la Galaxia Magallanes, que se presenta como
 * un laberinto de Asteroides en el espacio desde la Base hasta el Contenedor
 * buscado.
 * 
 * Construida en el paquete default para facilitar su utilización en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class LaberintoEspacial extends JuegoInteractivo {
    
    public LaberintoEspacial() {
        
        super(AplicacionBatallaEspacialLaberinto.class);
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripción para que al ser usado desde un intérprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return "Laberinto Espacial";
    }
}
