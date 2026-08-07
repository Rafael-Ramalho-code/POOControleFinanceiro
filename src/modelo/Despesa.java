package modelo;

import pagamento.MetodoPagamento;

public class Despesa extends Movimentacao{
    private MetodoPagamento meioPagamento;
    public Despesa(double valor, String categoria, MetodoPagamento meioPagamento){
        super(valor, categoria, "despesa");
        this.meioPagamento = meioPagamento;
    }
    public MetodoPagamento getMeioPagamento() {
        return meioPagamento;
    }
    @Override
    public String toString(){
        String detalhes;
        detalhes = super.toString();
        return detalhes + "\nMétodo de pagamento: " + getMeioPagamento();
    }
}
