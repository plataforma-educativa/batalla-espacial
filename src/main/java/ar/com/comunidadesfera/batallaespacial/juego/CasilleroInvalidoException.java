package ar.com.comunidadesfera.batallaespacial.juego;

public class CasilleroInvalidoException extends Exception {

    private static final long serialVersionUID = 7799827762030140311L;

    private Tablero tablero;
    private int fila;
    private int columna;
    
    public CasilleroInvalidoException(Tablero tablero, int fila, int columna) {
        super();
        
        this.setTablero(tablero);
        this.setFila(fila);
        this.setColumna(columna);
    }

    public int getColumna() {
        return columna;
    }

    protected void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    protected void setFila(int fila) {
        this.fila = fila;
    }

    public Tablero getTablero() {
        return tablero;
    }

    protected void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }


}
