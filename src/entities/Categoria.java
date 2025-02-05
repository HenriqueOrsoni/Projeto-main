package entities;

public class Categoria {
    private String nome;
    private double comissaoVendedor;
    private double comissaoRepresentante;

    public Categoria(String nome, double comissaoVendedor, double comissaoRepresentante) {
        this.nome = nome;
        this.comissaoVendedor = comissaoVendedor;
        this.comissaoRepresentante = comissaoRepresentante;
    }

    public String getNome() {
        return nome;
    }

    public double getComissaoVendedor() {
        return comissaoVendedor;
    }

    public double getComissaoRepresentante() {
        return comissaoRepresentante;
    }
}