package modelo;

import java.time.LocalDate;

public abstract class Movimentacao {
    private double valor;
    private final LocalDate data = LocalDate.now();
    private String categoria;
    private String tipo;

    public Movimentacao(double valor, String categoria, String tipo) {
        this.valor = valor;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.strip();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return "---------- Detalhes da Movimentação ----------\nValor: " + getValor() + "\nData: " + getData() + 
        "\nCategoria: " + getCategoria() + "\nTipo: " + getTipo();
    }
    
}
