package ar.com.comunidadesfera.batallaespacial.plugins.complejidad;

/**
 * La Complejidad posee los resultados arrojados por el Evaluador de 
 * Complejidad tras evaluar una Partida.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Complejidad {

    /**
     * Nivel de complejidad de la Partida, comprendido entre 0.0 y 100.0
     */
    private float nivel;
    
    /**
     * Cantidad de Contenedores por el total Casilleros totales en el Tablero.
     * La disminución de la densidad de Contenedores aumenta el nivel de 
     * complejidad.
     */
    private float densidadContenedores;
    
    /**
     * Cantidad de Asteriores por Casilleros totales en el Tablero.
     * El aumento en la densidad de Asteroides aumenta el nivel de complejidad. 
     */
    private float densidadAsteroides;
    
    /**
     * Promedio de las distancias (medido en Casilleros) entre los Contenedores
     * y las Bases.
     * El aumento en la distancia promedio entre los Contenedores y las Bases
     * aumenta el nivel de complejidad.
     */
    private float distanciaPromedioContenedor;
    
    /**
     * Promedio del nivel de contigüidad de los Asteroides.
     * El nivel de contigüidad de un Asteroides se calcula como el número
     * de Asteroides que se encuentran contiguos.
     * El aumento en el nivel de contigüidad promedio de Asteroides aumenta
     * el nivel de complejidad.
     */
    private float nivelContiguidadPromedioAsteroides;

    public float getNivel() {
        return nivel;
    }

    public void setNivel(float nivel) {
        this.nivel = nivel;
    }

    public float getDensidadContenedores() {
        return densidadContenedores;
    }

    public void setDensidadContenedores(float densidadContenedores) {
        this.densidadContenedores = densidadContenedores;
    }

    public float getDensidadAsteroides() {
        return densidadAsteroides;
    }

    public void setDensidadAsteroides(float densidadAsteroides) {
        this.densidadAsteroides = densidadAsteroides;
    }

    public float getDistanciaPromedioContenedor() {
        return distanciaPromedioContenedor;
    }

    public void setDistanciaPromedioContenedor(float distanciaPromedio) {
        this.distanciaPromedioContenedor = distanciaPromedio;
    }

    public float getNivelContiguidadPromedioAsteroides() {
        return nivelContiguidadPromedioAsteroides;
    }

    public void setNivelContiguidadPromedioAsteroides(
            float nivelContiguidad) {
        this.nivelContiguidadPromedioAsteroides = nivelContiguidad;
    }
    
}

