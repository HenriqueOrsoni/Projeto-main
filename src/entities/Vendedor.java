package entities;

public class Vendedor extends Pessoa {
    private ZonaVendas zona;

    public Vendedor(String nome, int id, ZonaVendas zona) {
        super(nome, id);
        this.zona = zona;
    }

    public ZonaVendas getZona() {
        return zona;
    }

    public void setZona(ZonaVendas zona) {
        this.zona = zona;
    }

    @Override
    public String getTipo() {
        return "Vendedor";
    }
}