package application;

import java.util.List;
import java.util.Scanner;

import entities.Cliente;
import entities.DataPedido;
import entities.Item;
import entities.Leitor;
import entities.Pedido;
import entities.Produto;
import entities.Vendedor;

public class Program {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Carregar dados com os caminhos fornecidos
            List<Vendedor> vendedores = Leitor.lerVendedores("arquivos/vendedores.txt");
            List<Cliente> clientes = Leitor.lerClientes("arquivos/clientes.txt");
            List<Produto> produtos = Leitor.lerProdutos("arquivos/produtos.txt");

            // Solicitar dados do vendedor
            System.out.println("Escolha o Vendedor:");
            for (int i = 0; i < vendedores.size(); i++) {
                System.out.println((i + 1) + ". " + vendedores.get(i).getNome());
            }
            int vendedorIndex = sc.nextInt() - 1;
            Vendedor vendedor = vendedores.get(vendedorIndex);

            // Solicitar dados do cliente
            System.out.println("Escolha o Cliente:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + ". " + clientes.get(i).getNome());
            }
            int clienteIndex = sc.nextInt() - 1;
            Cliente cliente = clientes.get(clienteIndex);

            // Solicitar a data do pedido separadamente
            System.out.println("Informe a data do pedido:");
            
            System.out.print("Dia: ");
            int dia = sc.nextInt();
            
            System.out.print("Mês: ");
            int mes = sc.nextInt();
            
            System.out.print("Ano: ");
            int ano = sc.nextInt();

            // Criar o objeto DataPedido com as informações fornecidas
            DataPedido data = new DataPedido(dia, mes, ano);

            // Criar o pedido
            Pedido pedido = new Pedido(cliente, vendedor, data);

            // Solicitar a quantidade de produtos a serem adicionados ao pedido
            System.out.print("Quantos produtos deseja adicionar ao pedido? ");
            int quantidadeProdutos = sc.nextInt();
            sc.nextLine(); // Limpa buffer

            // Exibir os produtos disponíveis e pedir quantidades
            for (int i = 0; i < quantidadeProdutos; i++) {
                System.out.println("Escolha o produto #" + (i + 1) + " (Digite o número):");
                for (int j = 0; j < produtos.size(); j++) {
                    System.out.println((j + 1) + ". " + produtos.get(j).getNome() + " - R$" + produtos.get(j).getPreco());
                }
                int produtoIndex = sc.nextInt() - 1;
                sc.nextLine(); // Limpa buffer
                Produto produtoEscolhido = produtos.get(produtoIndex);

                System.out.print("Digite a quantidade de " + produtoEscolhido.getNome() + ": ");
                int quantidade = sc.nextInt();
                sc.nextLine(); // Limpa buffer

                Item item = new Item(produtoEscolhido, quantidade);
                pedido.adicionarItem(item);
            }

            // Exibir resumo do pedido
            System.out.println("\nResumo do Pedido:");
            System.out.println("Cliente: " + pedido.getCliente().getNome() + " (CPF: " + pedido.getCliente().getCpf() + ")");
            System.out.println("Vendedor: " + pedido.getVendedor().getNome());
            System.out.println("Data: " + pedido.getData());
            System.out.println("Itens:");

            for (Item item : pedido.getItens()) {
                System.out.println(item.getQuantidade() + "x " + item.getProduto().getNome() + " - R$" + item.calcularValor());
            }

            System.out.println("Valor Total: R$" + pedido.calcularValorTotal());
            System.out.printf("Comissão do(a) Vendedor(a): R$%.2f\n", pedido.calcularComissao());

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execução do programa. Verifique os dados inseridos e tente novamente.");
        }
    }   
}