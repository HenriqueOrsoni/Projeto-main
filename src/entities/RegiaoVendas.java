package entities;

import java.util.ArrayList;
import java.util.List;

public class RegiaoVendas {
    private String nome;
    private RepresentanteRegional representante;
    private List<ZonaVendas> zonas = new ArrayList<>();

    public RegiaoVendas(String nome, RepresentanteRegional representante) {
        this.nome = nome;
        this.representante = representante;
    }

    public String getNome() {
        return nome;
    }

    public RepresentanteRegional getRepresentante() {
        return representante;
    }

    public List<ZonaVendas> getZonas() {
        return zonas;
    }

    public void adicionarZona(ZonaVendas zona) {
        zonas.add(zona);
    }
}