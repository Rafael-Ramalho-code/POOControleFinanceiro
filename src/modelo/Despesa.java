package modelo;

public class Despesa extends Movimentacao{
    private int meioPagamento;
    public Despesa(double valor, String categoria, int meioPagamento){
        super(valor, categoria, "despesa");
        this.meioPagamento = meioPagamento;
    }

    public int getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(int meioPagamento) {
        this.meioPagamento = meioPagamento;
    }
}
