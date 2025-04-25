package domain;

public enum Ocupation {
    ALTA(30),
    MEDIA(20),
    BAJA(10);

    private final int valor;

    Ocupation(int newValor) {
        valor = newValor;
    }

    public int getValor() {
        return valor;
    }
}
