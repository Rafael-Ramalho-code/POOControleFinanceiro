package modelo;

public abstract class Movimentacao {
    private int valor;
    private int data;
    private String categoria;

    public Movimentacao(int valor, int data, String categoria) {
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }
}
