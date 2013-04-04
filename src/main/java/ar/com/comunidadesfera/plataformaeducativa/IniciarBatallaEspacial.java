package ar.com.comunidadesfera.plataformaeducativa;

import java.util.Map;
import java.util.Properties;

import javax.enterprise.inject.Produces;

import ar.com.comunidadesfera.fx.Iniciar;
import ar.com.comunidadesfera.persistencia.Configuracion;
import ar.com.comunidadesfera.persistencia.Configuracion.Tipo;

public class IniciarBatallaEspacial extends Iniciar {

    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_NAME)
    public final String PERSISTENCE_UNIT_NAME = "eficienciaPersistenceUnit";
    
    @Produces @Configuracion(Tipo.PERSISTENCE_UNIT_PROPERTIES)
    public final Map<Object, Object> PERSISTENCE_UNIT_PROPERTIES = new Properties();

    
    @Override
    protected String getFxmlLocation() {

        return "/ar/com/comunidadesfera/batallaespacial/interfaz/principal.fxml";
    }
}
