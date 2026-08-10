package pagamento;
import excecoes.SaldoInsuficienteException;
import modelo.Conta;

public interface MetodoPagamento {
    public void processarPagamento(double valor, Conta conta) throws SaldoInsuficienteException;
}
