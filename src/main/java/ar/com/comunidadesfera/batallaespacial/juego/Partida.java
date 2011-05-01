package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ar.com.comunidadesfera.batallaespacial.Comandante;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.BaseUnicaCentral;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeBases;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeNaves;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.NavesDeCombatePosicionadasEnBase;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.Reglamentacion;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.ReglamentacionBasica;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public class Partida<T extends Participante> {

    /**
     * Tablero en el que se desarrolla la Partida.
     */
    private Tablero tablero;

    /**
     * Lista de Pilotos.
     */
    private List<PilotoControlado> pilotos;

    /**
     * Bases Espaciales de los Participantes.
     */
    private List<BaseEspacial> bases;

    /**
     * Naves de los Participantes. 
     */
    private List<Nave> naves;
    
    private Set<Pieza> piezasDestruidas = new TreeSet<Pieza>();
    
    /**
     * Configuración de la Partida.
     */
    private Configuracion<T> configuracion;
    
    /**
     * Control del Radar de las Naves
     */
    private ControlDeRadar controlDeRadar;
    
    /**
     * Motor de la Partida
     */
    private Motor motor;
    
    /**
     * Thread del Motor en ejecución 
     */
    private Thread threadMotor;
    
    /**
     * Reglamentación a utilizar sobre las Piezas de la Partida.
     */
    private Reglamentacion reglamentacion; 
    
    /**
     * Estrategia utilizada para definir las Bases Espaciales de cada
     * Participante.
     */
    private DefinicionDeBases estrategiaDeDefinicionDeBases;
    
    /**
     * Estrategia utilizada para definir las Naves para los Piloto de cada
     * Participante.
     */
    private DefinicionDeNaves estrategiaDeDefinicionDeNaves;

    /**
     * Crea una Partida a partir de la Configuración dada.
     * 
     * @param configuracion
     */
    public Partida(Configuracion<T> configuracion) {
        
        super();
        
        this.setConfiguracion(configuracion);
        
        // TODO Leer desde la configuración las estrategias utilizadas.
        this.setReglamentacion(new ReglamentacionBasica());
        this.setEstrategiaDeDefinicionDeBases(new BaseUnicaCentral());
        this.setEstrategiaDeDefinicionDeNaves(new NavesDeCombatePosicionadasEnBase());
        
        this.setTablero(configuracion.getTablero());
        this.setControlDeRadar(new ControlDeRadar(this.getTablero()));
        
        this.setBases(new LinkedList<BaseEspacial>());
        this.setNaves(new LinkedList<Nave>());
        this.setPilotos(new LinkedList<PilotoControlado>());
        
        
        this.asignarPiezas();
        
        this.motor = new Motor(this.getPilotos(), this, configuracion.getRondas());
        this.motor.setTimeout(configuracion.getTimeout());
    }

    public void comenzar() {
        
        this.threadMotor = new Thread(this.motor);
        
        this.threadMotor.start();
        
    }

    public Tablero getTablero() {
        return tablero;
    }

    private void setTablero(Tablero tablero) {
        this.tablero = tablero;
        
        this.procesarTablero();
    }

    private void procesarTablero() {

        /* para todas las piezas existentes en el tablero asigna el reglamento */
        for (Pieza pieza : this.tablero) {
            
            if (pieza != null) {
                
                pieza.agregarObservador(this.getReglamentacion().getReglamento(pieza));
            }
            
        }
    }
    
    public List<PilotoControlado> getPilotos() {
        return this.pilotos;
    }
    
    private void setPilotos(List<PilotoControlado> pilotos) {
        this.pilotos = pilotos;
    }
    
    /**
     * @pre se han asignado todas las Estrategias de ensamblaje de la Partida.   
     * @post asigna a cada participante las Piezas con las que competirá.
     *       
     * @return
     */
    private void asignarPiezas() {
        
        for (Participante participante : this.getConfiguracion().getParticipantes()) {
            
            /* asigna las bases para el participante */
            List<BaseEspacial> bases = this.getEstrategiaDeDefinicionDeBases()
                                                .asignar(participante);

            /* registra las Bases */
            this.getBases().addAll(bases);

            /* solicita un Comandante */
            Comandante comandante = participante.getCivilizacion().construirComandante();

            int cantidadDeNaves = participante.getCantidadNaves();
            
            List<PilotoControlado> pilotos = new LinkedList<PilotoControlado>();
            for (Piloto piloto : comandante.construirEscuadron(cantidadDeNaves)) {
                
                /* construye el wrapper del Piloto */
                pilotos.add(new PilotoControlado(piloto));
            }
            
            List<Nave> naves = this.getEstrategiaDeDefinicionDeNaves()
                                        .asignar(participante, bases, pilotos);

            /* registra las Naves */
            this.getNaves().addAll(naves);
            
            /* registra los Pilotos */
            this.getPilotos().addAll(pilotos);
        }
    }
    
    public Configuracion<T> getConfiguracion() {
        return configuracion;
    }

    private void setConfiguracion(Configuracion<T> configuracion) {
        this.configuracion = configuracion;
    }

    public ControlDeRadar getControlDeRadar() {
        return controlDeRadar;
    }

    private void setControlDeRadar(ControlDeRadar controlDeRadar) {
        this.controlDeRadar = controlDeRadar;
    }

    public List<BaseEspacial> getBases() {
        return this.bases;
    }

    private void setBases(List<BaseEspacial> bases) {
        this.bases = bases;
    }
    
    public List<Nave> getNaves() {
        return this.naves;
    }

    private void setNaves(List<Nave> naves) {
        this.naves = naves;
    }
    
    /**
     * 
     * @return Motor que ejecuta la Partida, null si la Partida no comenzó. 
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * 
     * @return Thread que esta ejecutando el Motoir de la Partida, null si aún no se
     *         comenzó la ejecución.
     */
    public Thread getThreadMotor() {
        return threadMotor;
    }

    public Set<Pieza> getPiezasDestruidas() {
        return this.piezasDestruidas;
    }
    
    /**
     * @post configura la reglamentación a utilizar por la Partida.
     */
    private void setReglamentacion(Reglamentacion reglamentacion) {
        
        this.reglamentacion = reglamentacion;

        this.reglamentacion.setPartida(this);

    }
    
    /**
     * @return Reglamentacion a utilizar por la Partida.
     */
    public Reglamentacion getReglamentacion() {
        
        return this.reglamentacion;
    }

    /**
     * @return Estrategia utilizada para asignar Bases Espaciales a los
     *         Participantes.
     */
    public DefinicionDeBases getEstrategiaDeDefinicionDeBases() {
        
        return estrategiaDeDefinicionDeBases;
    }

    /**
     * @post cambia la Estrategia utilizada para asignar Bases Espaciales a los
     *       Participantes.
     * @param definicionDeBases
     */
    private void setEstrategiaDeDefinicionDeBases(DefinicionDeBases definicionDeBases) {

        this.estrategiaDeDefinicionDeBases = definicionDeBases;
        
        this.estrategiaDeDefinicionDeBases.setPartida(this);
    }

    /**
     * @return Estrategia utilizada para asignar Naves a los Pilotos de los
     *         Participantes.
     */
    public DefinicionDeNaves getEstrategiaDeDefinicionDeNaves() {
        
        return estrategiaDeDefinicionDeNaves;
    }

    /**
     * @post cambia la Estrategia utilizada para asignar Naves a los Pilotos
     *       de los Participantes.
     * @param definicionDeNaves
     */
    private void setEstrategiaDeDefinicionDeNaves(DefinicionDeNaves definicionDeNaves) {
        
        this.estrategiaDeDefinicionDeNaves = definicionDeNaves;
        
        this.estrategiaDeDefinicionDeNaves.setPartida(this);
    }
}
