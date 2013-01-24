package ar.com.comunidadesfera.batallaespacial.test.pieza.nave;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.comunidadesfera.batallaespacial.piezas.Arma;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.ArsenalNaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

public class ArsenalNaveDeCombateTest {

    private ArsenalNaveDeCombate arsenal;

    @Before
    public void crear() {
        
        NaveDeCombate.setCapacidadTorpedos(15);
        
        this.arsenal = new ArsenalNaveDeCombate();
    }
    
    @Test
    public void inicializar() {
        
        
        assertThat("armamento", 
                   this.arsenal.getArmamento(), hasItems(Arma.LASER, Arma.TORPEDO_DE_FOTONES));
        
        assertThat("capacidad de LASER", 
                   this.arsenal.getCapacidad(Arma.LASER), is(Integer.MAX_VALUE));
        
        assertThat("capacidad de TORPEDO_DE_FOTONES", 
                   this.arsenal.getCapacidad(Arma.TORPEDO_DE_FOTONES), is(NaveDeCombate.getCapacidadTorpedos()));
        
        assertThat("municiones de LASER", 
                   this.arsenal.getMuniciones(Arma.LASER), is(Integer.MAX_VALUE));

        assertThat("municiones de TORPEDO_DE_FOTONES", 
                   this.arsenal.getMuniciones(Arma.TORPEDO_DE_FOTONES), is(NaveDeCombate.getCapacidadTorpedos()));
        
    }
    
    @Test
    public void setMuniciones() {
        
        int capacidad = this.arsenal.getCapacidad(Arma.TORPEDO_DE_FOTONES);
        
        this.arsenal.setMuniciones(Arma.TORPEDO_DE_FOTONES, capacidad - 2);
        
        assertThat("municiones", this.arsenal.getMuniciones(Arma.TORPEDO_DE_FOTONES), is(capacidad - 2));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void setMunicionesNegativas() {
        
        this.arsenal.setMuniciones(Arma.TORPEDO_DE_FOTONES, -5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void setMunicionesSobreCapacidad() {
        
        int capacidad = this.arsenal.getCapacidad(Arma.TORPEDO_DE_FOTONES);
        
        this.arsenal.setMuniciones(Arma.TORPEDO_DE_FOTONES, capacidad + 1);
    }
    
    @Test
    public void clonar() {
        
        final int torpedos = 10;
        final int laser = 50;
        
        this.arsenal.setMuniciones(Arma.TORPEDO_DE_FOTONES, torpedos);
        this.arsenal.setMuniciones(Arma.LASER, laser);
        
        ArsenalNaveDeCombate clon = this.arsenal.clone();
        
        assertThat("municiones de TORPEDO_DE_FOTONES", clon.getMuniciones(Arma.TORPEDO_DE_FOTONES), is(torpedos));
        assertThat("municiones de LASER", clon.getMuniciones(Arma.LASER), is(laser));
        
        /* cambia el original para comprobar que son instancias diferentes */
        this.arsenal.setMuniciones(Arma.TORPEDO_DE_FOTONES, 1);
        this.arsenal.setMuniciones(Arma.LASER, 4);
        
        assertThat("municiones de TORPEDO_DE_FOTONES", clon.getMuniciones(Arma.TORPEDO_DE_FOTONES), is(torpedos));
        assertThat("municiones de LASER", clon.getMuniciones(Arma.LASER), is(laser));
    }
}
