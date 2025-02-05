package entities;

public class ZonaVendas {
    private String uf;
    private Vendedor vendedor;
    private RegiaoVendas regiao;

    public ZonaVendas(String uf, Vendedor vendedor, RegiaoVendas regiao) {
        this.uf = uf;
        this.vendedor = vendedor;
        this.regiao = regiao;
    }

    public String getUf() {
        return uf;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public RegiaoVendas getRegiao() {
        return regiao;
    }
}