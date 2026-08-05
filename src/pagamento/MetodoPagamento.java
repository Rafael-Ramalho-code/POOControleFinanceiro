package pagamento;
import modelo.Conta;

public interface MetodoPagamento {
    public void processarPagamento(double valor, Conta conta);
}
