package utilidades;

import excecoes.ValorInvalidoException;

public class Validador {

    // receita e despesa não podem ser zero nem negativas
    public static void validarValor(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("O valor deve ser maior que zero.");
        }
    }
}
