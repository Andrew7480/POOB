package domain;

public enum PokemonType {
    ACERO(),
    AGUA(),
    BICHO(),
    DRAGON(),
    ELECTRICO(),
    FANTASMA(),
    FUEGO(),
    HADA(),
    HIELO(),
    LUCHA(),
    NORMAL(),
    PLANTA(),
    PSIQUICO(),
    ROCA(),
    SINIESTRO(),
    TIERRA(),
    VOLADOR(),
    VENENO();

    public int getIndex() {
        return this.ordinal();
    }

}
