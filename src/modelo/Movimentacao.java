package modelo;

public abstract class Movimentacao {
    private double valor;
    private String  data;
    private String categoria;
    private String tipo;
    private String id;

    public Movimentacao(double valor, String data, String categoria, String tipo, String id) {
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.tipo = tipo;
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return "---------- Detalhes da Movimentação ----------\nValor: " + getValor() + "\nData: " + getData() + 
        "\nCategoria: " + getCategoria() + "\nTipo: " + getTipo() + "\nID: " + getId() ;
    }
    
}
