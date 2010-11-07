package ar.com.comunidadesfera.batallaespacial.test.pieza.base;

import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClon;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClonPieza;
import ar.com.comunidadesfera.batallaespacial.test.pieza.PiezaTest;

public class BaseEspacialTest extends PiezaTest<BaseEspacial> {

    @Override
    protected int cambiarParaObservar(BaseEspacial base) {

        Nave naveA = new NaveDeCombate(200);
        Nave naveB = new NaveDeCombate(200);
        
        base.agregarNave(naveA);
        base.agregarNave(naveB);
        base.retirarNave(naveB);
        
        return 3;
    }

    @Override
    protected BaseEspacial instanciar() {

        return new BaseEspacial(100);
    }

    @Override
    protected ComprobarClon<BaseEspacial> comprobarClon() {

        return new ComprobarClonPieza<BaseEspacial>("Base Espacial");
    }
    
}