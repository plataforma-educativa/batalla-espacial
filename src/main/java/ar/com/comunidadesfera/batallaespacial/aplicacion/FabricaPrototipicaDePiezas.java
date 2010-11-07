package ar.com.comunidadesfera.batallaespacial.aplicacion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.piezas.AgujeroNegro;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

public class FabricaPrototipicaDePiezas implements FabricaDePiezas {

    public static final String PROTOTIPOS_PROPERTIES = "/ar/uba/fi/algo3/batallaespacial/config/prototipos.properties";
    public static final String VALOR_DEFAULT = "1";
    public static final String NAVE = "nave";
    public static final String BASE = "base";
    
    private Map<String, Pieza> prototipos = new TreeMap<String, Pieza>();

    
    
    public FabricaPrototipicaDePiezas() {
        
        super();
        this.cargarPrototipos(PROTOTIPOS_PROPERTIES);
        
    }

    public Pieza crearPieza(String origen) {

        /* obtiene el protitpo asociado a la cadena de origen */
        Pieza prototipo = this.prototipos.get(origen);
        
        /* si existe, clona el prototipo y lo devuelve */
        return prototipo != null ? prototipo.clone() : null;
    }

    /**
     * @pre ninguna.
     * @post agrega el prototipo asociándolo al nombre dado.
     * 
     * @param nombre
     * @param pieza
     */
    public void addPrototipo(String nombre, Pieza pieza) {
        
        this.prototipos.put(nombre, pieza);
    }
    
    /**
     * 
     * @param propertiesResource
     */
    public void cargarPrototipos(String propertiesResource) {
        
        InputStream resource = null;
        
        try {

            /* properties de configuración */
            Properties properties = new Properties();
            resource = this.getClass().getResourceAsStream(propertiesResource);  
            properties.load(resource);
        
            int intValue;
            long longValue;
            String simbolo;
            
            /* Nave */
            simbolo = properties.getProperty("nave.simbolo");            
            intValue = Integer.parseInt(properties.getProperty("nave.puntos",
                                                               VALOR_DEFAULT));
            NaveDeCombate.setCapacidadTorpedos(intValue);
            intValue = Integer.parseInt(properties.getProperty("nave.torpedos",
                                                               VALOR_DEFAULT));
            longValue = Long.parseLong(properties.getProperty("nave.carga",
                                                              VALOR_DEFAULT));
            NaveDeCombate.setCapacidadDeCarga(longValue);
            NaveDeCombate nave = new NaveDeCombate(intValue);
            this.prototipos.put(simbolo, nave);
            this.prototipos.put(NAVE, nave);
            
            /* Base */ 
            simbolo = properties.getProperty("base.simbolo");            
            intValue = Integer.parseInt(properties.getProperty("base.puntos",
                                                                VALOR_DEFAULT));
            BaseEspacial base = new BaseEspacial(intValue);
            this.prototipos.put(simbolo, base);
            this.prototipos.put(BASE, base);
            
            /* Asteroide */
            intValue = Integer.parseInt(properties.getProperty("asteroide.puntos",
                                                               VALOR_DEFAULT));
            simbolo = properties.getProperty("asteroide.simbolo");
            Asteroide asteroide = new Asteroide(intValue);
            this.prototipos.put(simbolo, asteroide);
            
            /* Contenedor */
            intValue = Integer.parseInt(properties.getProperty("contenedor.puntos",
                                                               VALOR_DEFAULT));
            simbolo = properties.getProperty("contenedor.simbolo");
            Contenedor contenedor = new Contenedor(intValue, Integer.MAX_VALUE);
            
            longValue = Long.parseLong(properties.getProperty("contenedor.sustancia",
                                                              VALOR_DEFAULT));
            contenedor.agregarSustancia(Sustancia.ANTIMATERIA, longValue);
            
            this.prototipos.put(simbolo, contenedor);
            
            /* Contenedor de Cristal */
            intValue = Integer.parseInt(properties.getProperty("contenedor.cristal.puntos",
                                                               VALOR_DEFAULT));
            simbolo = properties.getProperty("contenedor.cristal.simbolo");
            Contenedor contenedorCristal = new Contenedor(intValue, Integer.MAX_VALUE);
            
            longValue = Long.parseLong(properties.getProperty("contenedor.cristal.sustancia",
                                                              VALOR_DEFAULT));
            contenedorCristal.agregarSustancia(Sustancia.CRISTAL, longValue);

            this.prototipos.put(simbolo, contenedorCristal);
            
            /* Contenedor de Metal */
            intValue = Integer.parseInt(properties.getProperty("contenedor.metal.puntos",
                                                               VALOR_DEFAULT));
            simbolo = properties.getProperty("contenedor.metal.simbolo");
            Contenedor contenedorMetal = new Contenedor(intValue, Integer.MAX_VALUE);
            
            longValue = Long.parseLong(properties.getProperty("contenedor.metal.sustancia",
                                                              VALOR_DEFAULT));
            contenedorMetal.agregarSustancia(Sustancia.METAL, longValue);

            this.prototipos.put(simbolo, contenedorMetal);
            
            /* Agujero Negro */
            simbolo = properties.getProperty("agujeroNegro.simbolo");
            AgujeroNegro agujeroNegro = new AgujeroNegro();
            this.prototipos.put(simbolo, agujeroNegro);
            
        } catch (IOException e) {

            e.printStackTrace();
            
        } finally {
            
            try {
                
                if (resource != null) {
                    resource.close();
                }
                
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }
        
    }

    public NaveDeCombate crearNaveDeCombate() {

        return (NaveDeCombate) this.crearPieza(NAVE);
    }

    public BaseEspacial crearBaseEspacial() {

        return (BaseEspacial) this.crearPieza(BASE);
    }
}
