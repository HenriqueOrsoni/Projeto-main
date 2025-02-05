package entities;

public class RepresentanteRegional extends Pessoa {
    private RegiaoVendas regiao;

    public RepresentanteRegional(String nome, int id, RegiaoVendas regiao) {
        super(nome, id);
        this.regiao = regiao;
    }

    public RegiaoVendas getRegiao() {
        return regiao;
    }

    public void setRegiao(RegiaoVendas regiao) {
        this.regiao = regiao;
    }

    @Override
    public String getTipo() {
        return "RepresentanteRegional";
    }
}