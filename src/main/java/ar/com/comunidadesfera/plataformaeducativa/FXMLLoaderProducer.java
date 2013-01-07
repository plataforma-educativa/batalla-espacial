package ar.com.comunidadesfera.plataformaeducativa;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.*;
import javax.inject.Inject;

public class FXMLLoaderProducer {
    
    @Inject 
    private Instance<Object> instance;
    
    @Produces
    public FXMLLoader createLoader() {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            
            @Override
            public Object call(Class<?> param) {
                
                return instance.select(param).get();
            }
        });
        
        return loader;
    }
}
