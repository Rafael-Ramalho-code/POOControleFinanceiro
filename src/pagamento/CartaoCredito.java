package pagamento;

import modelo.Conta;

public class CartaoCredito implements MetodoPagamento{
    private String diaVencimeto;

    public CartaoCredito(String diaVencimento){
        this.diaVencimeto = diaVencimento;
    }

    @Override
    public void processarPagamento(double valor, Conta conta) {
       double limite = conta.getLimiteCartao();
       double fatura = conta.getFaturaCartao();

       if(limite - fatura >= valor){
            fatura += valor;
        }else{
            //SaldoInsuficienteException
        }
        
    }

    
}
