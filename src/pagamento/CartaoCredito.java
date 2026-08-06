package pagamento;

import modelo.Conta;

public class CartaoCredito implements MetodoPagamento{
    /*private String diaVencimeto;

    public CartaoCredito(String diaVencimento){
        this.diaVencimeto = diaVencimento;
    }
    */

    @Override
    public void processarPagamento(double valor, Conta conta) {
       double limite = conta.getLimiteCartao();
       double fatura = conta.getFaturaCartao();

       if(limite - fatura >= valor && valor <= limite){
            fatura += valor;
        }else{
            //SaldoInsuficienteException
            System.out.println("Não é possível fazer a transação no momento");
        }
        
    }

    
}
