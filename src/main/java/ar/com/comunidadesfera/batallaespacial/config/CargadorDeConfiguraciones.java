package ar.com.comunidadesfera.batallaespacial.config;

import java.nio.file.Path;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.aplicacion.FabricaDeTableros;
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
    
    Configuracion cargar(Path ruta) throws ConfiguracionInvalidaException;

    void setFabricaDeTableros(FabricaDeTableros fabrica);

    void setFabricaDePiezas(FabricaDePiezas fabrica);

}