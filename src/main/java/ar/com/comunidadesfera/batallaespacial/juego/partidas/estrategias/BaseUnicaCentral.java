package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.Arrays;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

/**
 * Implementación de la estrategia de Definición de Bases que asigna una única
 * BaseEspacial para cada Participante.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BaseUnicaCentral extends DefinicionDeBasesAbstracta {
    
    public static final String NOMBRE = "Central";
    
    public List<BaseEspacial> asignar(Participante participante) {

        BaseEspacial base = this.construirBaseEspacial(participante);
        base.setNombre(NOMBRE);

        this.posicionarCentro(base);
        
        return Arrays.asList(base);
    }

}
