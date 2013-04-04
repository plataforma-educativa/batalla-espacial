package ar.com.comunidadesfera.batallaespacial.piezas.contenedor;

import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class RecolectorDeContenedores implements VisitanteDePiezas {

    private List<Contenedor> contenedor = new LinkedList<Contenedor>();
    
    @Override
    public void visitar(Nave nave) {

    }

    @Override
    public void visitar(BaseEspacial base) {

    }

    @Override
    public void visitar(Asteroide asteroide) {

    }

    @Override
    public void visitar(Contenedor contenedor) {

        this.contenedor.add(contenedor);
    }

    public List<Contenedor> getContenedores() {
        
        return this.contenedor;
    }
}
