package entities;

import java.io.*;
import java.util.*;

public class Leitor {

    // Método para ler representantes regionais
    public static List<RepresentanteRegional> lerRepresentantes(String caminho) throws IOException {
        List<RepresentanteRegional> representantes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            representantes.add(new RepresentanteRegional(
                partes[0].trim(),
                Integer.parseInt(partes[1].trim()),
                null
            ));
        }
        br.close();
        return representantes;
    }

    // Método para ler regiões
    public static List<RegiaoVendas> lerRegioes(String caminho, List<RepresentanteRegional> representantes) throws IOException {
        List<RegiaoVendas> regioes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            int idRepresentante = Integer.parseInt(partes[1].trim());

            RepresentanteRegional rep = representantes.stream()
                .filter(r -> r.getId() == idRepresentante)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Representante não encontrado ID: " + idRepresentante));

            regioes.add(new RegiaoVendas(partes[0].trim(), rep));
        }
        br.close();
        return regioes;
    }

    // Método para ler vendedores
    public static List<Vendedor> lerVendedores(String caminho) throws IOException {
        List<Vendedor> vendedores = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            vendedores.add(new Vendedor(
                partes[0].trim(),
                Integer.parseInt(partes[1].trim()),
                null
            ));
        }
        br.close();
        return vendedores;
    }

    // Método para ler zonas (simplificado)
    public static List<ZonaVendas> lerZonas(String caminho, List<RegiaoVendas> regioes, List<Vendedor> vendedores) throws IOException {
        List<ZonaVendas> zonas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            String uf = partes[0].trim().toUpperCase();
            String nomeRegiao = partes[1].trim();
            int idVendedor = Integer.parseInt(partes[2].trim());

            RegiaoVendas regiao = regioes.stream()
                .filter(r -> r.getNome().equalsIgnoreCase(nomeRegiao))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Região não encontrada: " + nomeRegiao));

            Vendedor vendedor = vendedores.stream()
                .filter(v -> v.getId() == idVendedor)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado: " + idVendedor));

            zonas.add(new ZonaVendas(uf, vendedor, regiao));
        }
        br.close();
        return zonas;
    }

    // Método para ler clientes
    public static List<Cliente> lerClientes(String caminho, List<ZonaVendas> zonas) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            Cliente cliente = new Cliente(
                partes[0].trim(),
                Integer.parseInt(partes[1].trim()),
                partes[2].trim(),
                partes[3].trim().toUpperCase()
            );

            String ufCliente = cliente.getUf();
            zonas.stream()
                .filter(z -> z.getUf().equalsIgnoreCase(ufCliente))
                .findFirst()
                .ifPresentOrElse(
                    cliente::setZona,
                    () -> System.out.println("Aviso: Cliente " + cliente.getNome() + " (UF " + ufCliente + ") sem zona associada")
                );

            clientes.add(cliente);
        }
        br.close();
        return clientes;
    }

    // Método para ler categorias
    public static List<Categoria> lerCategorias(String caminho) throws IOException {
        List<Categoria> categorias = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            categorias.add(new Categoria(
                partes[0].trim(),
                Double.parseDouble(partes[1].trim()),
                Double.parseDouble(partes[2].trim())
            ));
        }
        br.close();
        return categorias;
    }

    // Método para ler produtos
    public static List<Produto> lerProdutos(String caminho, List<Categoria> categorias) throws IOException {
        List<Produto> produtos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            String nomeCategoria = partes[2].trim();

            Categoria categoria = categorias.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nomeCategoria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + nomeCategoria));

            produtos.add(new Produto(
                partes[0].trim(),
                Double.parseDouble(partes[1].trim()),
                categoria
            ));
        }
        br.close();
        return produtos;
    }
}