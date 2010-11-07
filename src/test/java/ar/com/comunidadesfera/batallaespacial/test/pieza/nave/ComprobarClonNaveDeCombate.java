package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor.ComprobarClonContenedor;

public class ComprobarClonNaveDeCombate<T extends NaveDeCombate> 
    extends ComprobarClonNave<T> {
    
    public ComprobarClonNaveDeCombate(String descripcion) {
        
        super(descripcion);
    }
    
    public void ejecutar(T pieza, T clon) {
        
        super.ejecutar(pieza, clon);
        
        this.procesar("cantidadDeTorpedos", clon.getCantidadDeTorpedos(),
                      Matchers.equalTo(pieza.getCantidadDeTorpedos()));
        
        this.procesar(new ComprobarClonContenedor<Contenedor>("bodega"), 
                      pieza.getBodega(), clon.getBodega());
    }

}
