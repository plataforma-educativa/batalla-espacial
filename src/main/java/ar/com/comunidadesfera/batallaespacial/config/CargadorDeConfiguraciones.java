package ar.com.comunidadesfera.batallaespacial.config;

import java.nio.file.Path;
import java.util.List;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.aplicacion.FabricaDeTableros;
import ar.com.comunidadesfera.batallaespacial.aplicacion.ParticipanteExtendido;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;

public interface CargadorDeConfiguraciones {
    
    /**
     * @return Descripción del tipo de archivo de configuración que utiliza.
     */
    String getDescripcion();
    
    /**
     * @return Filtro a ser utilizado para buscar archivos de configuración. 
     *         Por ejemplo: *.properties
     */
    List<String> getFiltros();
    
    Configuracion<ParticipanteExtendido> cargar(Path ruta) throws ConfiguracionInvalidaException;

    @Inject
    void setFabricaDeTableros(FabricaDeTableros fabrica);

    @Inject
    void setFabricaDePiezas(FabricaDePiezas fabrica);

}