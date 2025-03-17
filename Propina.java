package Punto2;

public enum Propina {
    DOS_PORCIENTO(2),
    TRES_PORCIENTO(3),
    CINCO_PORCIENTO(5);

    private final int valor;

    Propina(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor + "%";
    }
}