package pagamento;

import excecoes.SaldoInsuficienteException;
//import excecoes.SaldoInsuficienteException;
import modelo.Conta;

public class Pix implements MetodoPagamento{

    @Override
    public void processarPagamento(double valor, Conta conta) throws SaldoInsuficienteException{
        double saldo = conta.getSaldo();
        if(saldo >= valor){
            saldo -= valor;
            conta.setSaldo(saldo);
        }else{
            throw new SaldoInsuficienteException("Saldo insuficiente");    
        }
        
    }

    @Override
    public String toString(){
        return "Pix";
    }
    
}
