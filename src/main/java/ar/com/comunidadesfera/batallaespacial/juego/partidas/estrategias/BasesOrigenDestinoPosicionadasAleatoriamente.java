package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.Arrays;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

/**
 * Implementación de la Estrategia de Definicion de Bases Espaciales que asigna
 * a cada participante un par de Bases. Se denominan Base Origen y Base Destino,
 * posicionándose aleatoriamente.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class BasesOrigenDestinoPosicionadasAleatoriamente 
                extends DefinicionDeBasesAbstracta {
    
    public static final String NOMBRE_BASE_ORIGEN = "origen";
    public static final String NOMBRE_BASE_DESTINO = "destino";

    public List<BaseEspacial> asignar(Participante participante) {

        BaseEspacial origen = this.construirBaseEspacial(participante);
        origen.setNombre(NOMBRE_BASE_ORIGEN);
        
        BaseEspacial destino = this.construirBaseEspacial(participante);
        destino.setNombre(NOMBRE_BASE_DESTINO);

        /* posiciona aleatoriamente ambas bases */
        this.posicionarAleatoriamente(origen);
        this.posicionarAleatoriamente(destino);
        
        return Arrays.asList(origen, destino);
    }

}
