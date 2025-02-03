package entities;

public class Vendedor extends Pessoa {
    private double taxaComissao;

    public Vendedor(String nome, int id, double taxaComissao) {
        super(nome, id);
        this.taxaComissao = taxaComissao;
    }

    public double calcularComissao(double valorVendas) {
        return valorVendas * taxaComissao;
    }

    @Override
    public String getTipo() {
        return "Vendedor";
    }
}