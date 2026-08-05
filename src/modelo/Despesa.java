package modelo;

class Despesa extends Movimentacao{
    private int meioPagamento;
    public Despesa(double valor, String data, String categoria, String tipo, String id, int meioPagamento){
        super(valor, data, categoria, tipo, id);
        this.meioPagamento = meioPagamento;
    }

    public int getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(int meioPagamento) {
        this.meioPagamento = meioPagamento;
    }
}
