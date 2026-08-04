package modelo;

public class Despesa extends Movimentacao{
    private int meioPagamento;
    public Despesa(double valor, String data, String categoria, int meioPagamento) {
        super(valor, data, categoria, "despesa");
        this.meioPagamento = meioPagamento;
    }

    public int getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(int meioPagamento) {
        this.meioPagamento = meioPagamento;
    }
}
