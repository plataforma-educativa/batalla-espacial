package ar.com.comunidadesfera.plataformaeducativa;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.galaxias.vialactea.BatallaEspacialViaLactea;
import ar.com.comunidadesfera.dependencias.Alternativa;
import ar.com.comunidadesfera.dependencias.Selector;

@Alternative
public class IniciarBatallaEspacialInteractiva extends IniciarBatallaEspacial {

    @Inject
    private Selector selector;
    
    @Override
    protected void configurar() {

        super.configurar();

        // TODO simplificar la selección de la implementación a utilizar
        for (Alternativa<BatallaEspacial> alternativa : this.selector.getAlternativasBatallaEspacial()) {
            
            if (alternativa.getImplementacion().equals(BatallaEspacialViaLactea.class)) {
                
                alternativa.seleccionar();
            }
        }
    }
}
