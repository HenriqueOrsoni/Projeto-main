package entities;

public class Cliente extends Pessoa {
    private String cpf;
    private String uf;
    private ZonaVendas zona;

    public Cliente(String nome, int id, String cpf, String uf) {
        super(nome, id);
        this.cpf = cpf;
        this.uf = uf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getUf() {
        return uf;
    }

    public ZonaVendas getZona() {
        return zona;
    }

    public void setZona(ZonaVendas zona) {
        this.zona = zona;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}