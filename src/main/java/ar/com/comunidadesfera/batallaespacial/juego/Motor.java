package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

public class Motor implements Runnable {

    /**
     * Cantidad de rondas ejecutadas.
     */
    private AtomicLong rondas = new AtomicLong(0); 
    
    /**
     * Tiempo de espera entre rondas en milisegundos. 
     */
    private AtomicLong timeout = new AtomicLong(0);
    
    /**
     * Lista Pilotos participantes de la Partida.
     */
    private List<? extends Piloto> pilotos;
    
    /**
     * Partida del juego. 
     */
    private Partida partida;

    /**
     * Lista de Observadores del Motor. 
     */
    private List<Observador> observadores;
    
    
    /**
     * Semáforo que controla la ejecución de las rondas del Motor.
     */
    private Semaphore semaforo; 


    public Motor(List<? extends Piloto> pilotos, Partida partida, int rondas) {
        super();

        this.setPilotos(pilotos);
        this.setPartida(partida);
        this.semaforo = new Semaphore(rondas, true);
    }


    /**
     * Comienza la ejecución de las rondas asignadas.
     */
    public void run() {

        try {

            while (! Thread.interrupted()) {
            
                /* espera hasta adquirir un turno de ronda */
                this.getSemaforo().acquire();
                
                /* espera el tiempo definido para el motor */
                Thread.sleep(this.timeout.get());
                
                /* ejecuta una ronda */
                this.runRonda();
            }
            
        } catch (InterruptedException e) {

            /* se ha interrumpido el thread y termina la ejecución del motor */
            this.notificarObservadoresFinalizacion();
            
        }
    }

    /**
     * Ejecuta una ronda.
     *
     */
    private void runRonda() {

        /* reordena aleatoriamente la lista */
        Collections.shuffle(this.pilotos);
        
        /* itera por todos los pilotos */
        for (Piloto piloto : this.getPilotos()) {

            this.runTurno(piloto);
        }
        
        /* notifica a todos los observadores */
        this.notificarObservadoresRondaTerminada(this.rondas.incrementAndGet());
    }
    
    /**
     * Ejecuta un turno para el Piloto dado.
     * 
     * @param piloto
     */
    public void runTurno(Piloto piloto) {
        
        Comando comando = null;
        
        try {
            
            /* solicita el próximo comando a ejecutar */
            comando = piloto.proximoComando();
            
            /* ejecuta el comando en el contexto de la Partida */
            comando.ejecutar(this.getPartida());

        } catch (Exception error) {
            
            this.notificarObservadoresException(piloto, comando, error);
            
        }
    }
    
    private List<? extends Piloto> getPilotos() {
        return this.pilotos;
    }
    
    private void setPilotos(List<? extends Piloto> pilotos) {
        this.pilotos = pilotos;
    }
    
    private Partida getPartida() {
        return this.partida;
    }
    
    private void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de rondas ejecutadas por el Motor.
     * 
     * @return cantidad de rondas.
     */
    public long getRondas() {
        
        return this.rondas.get();
    }
    
    private List<Observador> getObservadores() {
        return this.observadores;
    }
    
    /**
     * @pre ninguna.
     * @post agrega el Observador al Motor, para ser notificado
     *       en las etapas del Motor.
     * 
     * @param observador
     */
    public void agregarObservador(Observador observador) {

        if (this.observadores == null) {
            
            this.observadores = new LinkedList<Observador>();
        }
        
        this.observadores.add(observador); 
    }

    /**
     * @pre se ha terminado de ejecutar una ronda.
     * @post notifica a todos los Observadores. 
     *
     */
    private void notificarObservadoresRondaTerminada(long ronda) {
        
        if (this.getObservadores() != null) {
            
            for (Observador observador : this.observadores) {
                
                observador.rondaTerminada(this, ronda);
            }
        }
    }
    
    /**
     * @pre se ha producido una excepción en la ejecución de un Comando.
     * @post notifica a todos los Observadores. 
     *
     */
    private void notificarObservadoresException(Piloto piloto, Comando comando,
                                                Exception exception) {
        
        if (this.getObservadores() != null) {
            
            for (Observador observador : this.observadores) {
                
                observador.exception(this, this.getRondas(),
                                     piloto, comando, exception);
            }
        }
    }

    /**
     * @pre terminó la ejecución del Motor.
     * @post notifica a todos los Observadores.
     * 
     */
    private void notificarObservadoresFinalizacion() {
        
        if (this.getObservadores() != null) {
            
            for (Observador observador : this.getObservadores()) {
                
                observador.finalizacion(this);
            }
        }
    }
    
    public long getTimeout() {
        
        return this.timeout.get();
    }
    
    public void setTimeout(long timeout) {
        
        this.timeout.set(timeout);
    }
    
    public Semaphore getSemaforo() {
        return this.semaforo;
    }
    
    /**
     * Interfaz utilizada para notificar a los Observadores
     * del Motor las etapas por la cuales se transcurre. 
     *
     */
    public interface Observador {
        
        /**
         * @pre el motor ha finalizado la ejecución de una ronda (todos
         *       los participantes han realizado su propia ronda).
         *
         */
        void rondaTerminada(Motor motor, long ronda);
        
        /**
         * @pre se ha provocado una excepción en la ejecución de un Comando
         *       de un Piloto, en una ronda dada.
         * 
         * @param motor
         * @param ronda : número de ronda en la que se provocó el error. 
         * @param piloto : Piloto que creó el Comando.
         * @param comando : Comando en ejecución. 
         * @param exception : Excepción producida.
         */
        void exception(Motor motor, long ronda, Piloto piloto, 
                       Comando comando, Exception exception);
        
        /**
         * @pre el  Motor ha terminado su ejecución.
         * 
         * @param motor
         */
        void finalizacion(Motor motor);
    }
    
    
}
