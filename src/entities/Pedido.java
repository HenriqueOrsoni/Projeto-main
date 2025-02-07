package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private Vendedor vendedor;
    private List<Item> itens = new ArrayList<>();
    private LocalDate data;

    public Pedido(Cliente cliente, Vendedor vendedor, LocalDate data) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.data = data;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(Item::calcularValor).sum();
    }

    public double calcularComissaoVendedor() {
        return itens.stream()
            .mapToDouble(item -> item.calcularValor() * item.getProduto().getCategoria().getComissaoVendedor())
            .sum();
    }

    public double calcularComissaoRepresentante() {
        return itens.stream()
            .mapToDouble(item -> item.calcularValor() * item.getProduto().getCategoria().getComissaoRepresentante())
            .sum();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public List<Item> getItens() {
        return itens;
    }

    public LocalDate getData() {
        return data;
    }
}