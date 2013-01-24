package ar.com.comunidadesfera.plataformaeducativa;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class FXMLLoaderProducer {
    
    @Inject
    private Instance<Object> instance;
    
    @Produces
    public FXMLLoader createLoader() {
        
        FXMLLoader loader = new FXMLLoader();
        
        // TODO hacer configurable esta URL
        loader.setLocation(getClass().getResource("/ar/com/comunidadesfera/batallaespacial/interfaz/"));
        
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            
            @Override
            public Object call(Class<?> param) {
                
                return instance.select(param).get();
            }
        });
        
        return loader;
    }
}
