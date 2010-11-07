package ar.com.comunidadesfera.batallaespacial;

/**
 * 
 *
 */
public enum Direccion {

	ORIGEN   ( 0, 0),
	NORTE    ( 0,-1),
	SUR      ( 0, 1),
	ESTE     ( 1, 0),
	OESTE    (-1, 0),
	NORESTE  ( 1,-1),
	NOROESTE (-1,-1),
	SUDESTE  ( 1, 1),
	SUDOESTE (-1, 1);
    
    
    final int coordenadaX;
    final int coordenadaY;
    
    private Direccion(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    
    public int getCoordenadaX() {
        return this.coordenadaX;
    }
    
    public int getCoordenadaY() {
        return this.coordenadaY;
    }
    
    public Direccion contraria() {
        
        Direccion contraria;
        
        switch (this) {
        
            case NORTE    : contraria = SUR;      break;
            case SUR      : contraria = NORTE;    break;
            case ESTE     : contraria = OESTE;    break;
            case OESTE    : contraria = ESTE;     break;
            case NORESTE  : contraria = SUDOESTE; break;
            case SUDOESTE : contraria = NORESTE;  break;
            case NOROESTE : contraria = SUDESTE;  break;
            case SUDESTE  : contraria = NOROESTE; break;
            default : contraria = ORIGEN;
        }
        
        return contraria;
    }
}