package entities;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private Vendedor vendedor;
    private List<Item> itens;
    private DataPedido data; 

    public Pedido(Cliente cliente, Vendedor vendedor, DataPedido data) { 
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.data = data;
        this.itens = new ArrayList<>(); 
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(Item::calcularValor).sum();
    }

    public double calcularComissao() {
        return vendedor.calcularComissao(calcularValorTotal());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public DataPedido getData() { 
        return data;
    }

    public List<Item> getItens() {
        return itens;
    }
}
