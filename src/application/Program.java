package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import entities.*;

public class Program {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Carregar dados
            List<RepresentanteRegional> representantes = Leitor.lerRepresentantes("arquivos/representantes.txt");
            List<RegiaoVendas> regioes = Leitor.lerRegioes("arquivos/regioes.txt", representantes);
            List<Vendedor> vendedores = Leitor.lerVendedores("arquivos/vendedores.txt");
            List<ZonaVendas> zonas = Leitor.lerZonas("arquivos/zonas.txt", regioes, vendedores);
            List<Cliente> clientes = Leitor.lerClientes("arquivos/clientes.txt", zonas);
            List<Categoria> categorias = Leitor.lerCategorias("arquivos/categorias.txt");
            List<Produto> produtos = Leitor.lerProdutos("arquivos/produtos.txt", categorias);

            // Solicitar cliente
            System.out.println("Escolha o Cliente:");
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                String statusZona = (cliente.getZona() == null) ? " (Zona não associada)" : "";
                System.out.println((i + 1) + ". " + cliente.getNome() + statusZona);
            }
            int clienteIndex = sc.nextInt() - 1;
            Cliente cliente = clientes.get(clienteIndex);

            // Validar se o cliente tem uma zona associada
            if (cliente.getZona() == null) {
                System.out.println("Erro: O cliente selecionado não está associado a uma zona de vendas. Escolha outro cliente.");
                return; // Encerra o programa
            }

            // Solicitar data do pedido
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = null;

            while (data == null) {
                System.out.println("Informe a data do pedido (DD/MM/AAAA):");
                String dataStr = sc.next();
                try {
                    data = LocalDate.parse(dataStr, formatoEntrada);
                    int ano = data.getYear();
                    if (ano < 2000 || ano > 2030) {
                        System.out.println("Erro: O ano deve estar entre 2000 e 2030.");
                        data = null; // Reinicia o loop
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Erro: Formato de data inválido. Use o formato DD/MM/AAAA.");
                }
            }

            // Criar pedido
            Pedido pedido = new Pedido(cliente, cliente.getZona().getVendedor(), data);

            // Adicionar itens
            System.out.print("Quantos produtos deseja adicionar? ");
            int qtdProdutos = sc.nextInt();
            for (int i = 0; i < qtdProdutos; i++) {
                System.out.println("Escolha o produto #" + (i + 1) + ":");
                for (int j = 0; j < produtos.size(); j++) {
                    Produto p = produtos.get(j);
                    System.out.println((j + 1) + ". " + p.getNome() + " - R$" + p.getPreco());
                }
                int produtoIndex = sc.nextInt() - 1;
                Produto produto = produtos.get(produtoIndex);
                System.out.print("Quantidade: ");
                int quantidade = sc.nextInt();
                sc.nextLine(); // Limpar buffer
                pedido.adicionarItem(new Item(produto, quantidade));
            }

            // Perguntar se deseja cancelar o pedido
            System.out.println("Deseja cancelar o pedido? (S/N)");
            String resposta = sc.next().trim().toUpperCase();

            if (resposta.equals("S")) {
                pedido.cancelar();
                System.out.println("Pedido cancelado com sucesso!");
            } else {
                System.out.println("Pedido confirmado.");
            }

            // Exibir resumo do pedido
            System.out.println("\nResumo do Pedido:");
            System.out.println("Cliente: " + pedido.getCliente().getNome() + " (CPF: " + pedido.getCliente().getCpf() + ")");
            System.out.println("Vendedor: " + pedido.getVendedor().getNome());
            System.out.println("Representante Regional: " + cliente.getZona().getRegiao().getRepresentante().getNome());
            System.out.println("Data: " + data.format(formatoSaida)); // Data no formato brasileiro
            System.out.println("Itens:");
            for (Item item : pedido.getItens()) {
                System.out.println(item.getQuantidade() + "x " + item.getProduto().getNome() + " - R$" + item.calcularValor());
            }
            System.out.println("Valor Total: R$" + pedido.calcularValorTotal());
            System.out.printf("Comissão do Vendedor: R$%.2f\n", pedido.calcularComissaoVendedor());
            System.out.printf("Comissão do Representante: R$%.2f\n", pedido.calcularComissaoRepresentante());

            if (pedido.isCancelado()) {
                System.out.println("Status: CANCELADO");
            } else {
                System.out.println("Status: CONFIRMADO");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}