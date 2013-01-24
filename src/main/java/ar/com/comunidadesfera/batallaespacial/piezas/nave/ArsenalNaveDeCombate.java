package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import ar.com.comunidadesfera.batallaespacial.piezas.Arma;
import ar.com.comunidadesfera.batallaespacial.piezas.Arsenal;

public class ArsenalNaveDeCombate implements Arsenal, Cloneable {

    private Map<Arma, Sector> sectores;
    
    public ArsenalNaveDeCombate() {
        
        this.sectores = new EnumMap<Arma, Sector>(Arma.class);
        
        this.agregar(Arma.LASER, Integer.MAX_VALUE, 
                                 Integer.MAX_VALUE);

        this.agregar(Arma.TORPEDO_DE_FOTONES, NaveDeCombate.getCapacidadTorpedos(), 
                                              NaveDeCombate.getCapacidadTorpedos());
    }
    
    public void agregar(Arma arma, int capacidad, int cantidad) {
        
        this.sectores.put(arma, new Sector(capacidad, cantidad));
    }
    
    @Override
    public Set<Arma> getArmamento() {

        return this.sectores.keySet();
    }

    @Override
    public int getCapacidad(Arma arma) {
        
        Sector municiones = this.sectores.get(arma);
        
        return municiones != null ? municiones.getCapacidad() : 0;
    }

    @Override
    public int getMuniciones(Arma arma) {
     
        Sector municiones = this.sectores.get(arma);
        
        return municiones != null ? municiones.getCantidad() : 0;
    }

    public void setMuniciones(Arma arma, int cantidad) {
        
        Sector municiones = this.sectores.get(arma);
        
        if (municiones == null) {

            throw new IllegalArgumentException("Arma no soportada en el Arsenal: " + arma);
        }
        
        municiones.setCantidad(cantidad);
    }
    
    @Override
    public ArsenalNaveDeCombate clone() {
        
        ArsenalNaveDeCombate clon;
        
        try {

            clon = (ArsenalNaveDeCombate) super.clone();

            clon.sectores = new EnumMap<Arma, ArsenalNaveDeCombate.Sector>(Arma.class);
            
            for (Map.Entry<Arma, Sector> armaSector : this.sectores.entrySet()) {
                
                clon.sectores.put(armaSector.getKey(), armaSector.getValue().clone());
            }

        } catch(CloneNotSupportedException error) {

            throw new IllegalStateException(error);
        }

        return clon;
    }
    
    /**
     * Compartimento del Arsenal en el que se almacenan las municiones para un Arma particular.
     *
     */
    private static class Sector implements Cloneable {
        
        private int capacidad;
        
        private AtomicInteger cantidad = new AtomicInteger();
        
        public Sector(int capacidad, int cantidad) {
            
            this.capacidad = capacidad;
            this.setCantidad(cantidad);
        }
        
        public int getCapacidad() {
            
            return this.capacidad;
        }
        
        public int getCantidad() {
            
            return this.cantidad.get();
        }
        
        public void setCantidad(int cantidad) {
            
            if ((cantidad < 0) || (cantidad > this.capacidad)) {
                
                throw new IllegalArgumentException("La cantidad de municiones debe estar entre " + 0 + 
                                                   " y " + this.capacidad);
            }
            
            this.cantidad.set(cantidad);
        }
        
        @Override
        public Sector clone() {

            Sector clon;

            try {

                clon = (Sector) super.clone();

                clon.capacidad = this.capacidad;
                clon.cantidad = new AtomicInteger(this.cantidad.get());

            } catch(CloneNotSupportedException error) {

                throw new IllegalStateException(error);
            }

            return clon;
        }
    }
}
