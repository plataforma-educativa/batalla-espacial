package ar.com.comunidadesfera.persistencia.test;

public enum Datos {

    ENTIDAD_NULL(null),
    ENTIDAD_1(1L),
    ENTIDAD_2(2L),
    ENTIDAD_47253(47253L);
    
    public final Long id;

    private Datos(Long id) {
        this.id = id;
    }
}
