package modelo;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private double saldo;
    private double limiteCartao;
    private double faturaCartao;
    private List <Movimentacao> movimentacoes;
    private List <Categoria> categorias;

    public Conta(double limiteCartao) {
        this.saldo = 0.0;
        this.limiteCartao = limiteCartao;
        this.faturaCartao = 0.0;
        this.movimentacoes = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }

    public double getFaturaCartao() {
        return faturaCartao;
    }

    public void setFaturaCartao(double faturaCartao) {
        this.faturaCartao = faturaCartao;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }


    public void adicionarMovimentacao(Movimentacao m){
        movimentacoes.add(m);
        AdicionarCategoria(m.getCategoria());
    }

    public void AdicionarCategoria(String n){
        Categoria c = new Categoria();
        n = c.getNome();
        categorias.add(c);
    }
}
