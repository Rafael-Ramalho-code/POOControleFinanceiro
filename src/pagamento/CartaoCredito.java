package pagamento;

import excecoes.SaldoInsuficienteException;
import modelo.Conta;

public class CartaoCredito implements MetodoPagamento{
    /*private String diaVencimeto;

    public CartaoCredito(String diaVencimento){
        this.diaVencimeto = diaVencimento;
    }
    */

    @Override
    public void processarPagamento(double valor, Conta conta) throws SaldoInsuficienteException{
       double limite = conta.getLimiteCartao();
       double fatura = conta.getFaturaCartao();

       if(limite >= valor + fatura){
            fatura += valor;
            conta.setFaturaCartao(fatura);
        }else{
            throw new SaldoInsuficienteException("Valor excede o limite restante");
        }
        
    }

    @Override
    public String toString(){
        return "Cartão de crédito";
    }

    
}
