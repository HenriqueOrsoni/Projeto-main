package entities;

import java.io.*;
import java.util.*;

public class Leitor {
    public static List<Vendedor> lerVendedores(String caminho) throws IOException {
        List<Vendedor> vendedores = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            vendedores.add(new Vendedor(partes[0], Integer.parseInt(partes[1]), Double.parseDouble(partes[2])));
        }
        br.close();
        return vendedores;
    }

    public static List<Cliente> lerClientes(String caminho) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            clientes.add(new Cliente(partes[0], Integer.parseInt(partes[1]), partes[2]));
        }
        br.close();
        return clientes;
    }

    public static List<Produto> lerProdutos(String caminho) throws IOException {
        List<Produto> produtos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",");
            produtos.add(new Produto(partes[0], Double.parseDouble(partes[1]), new Categoria(partes[2])));
        }
        br.close();
        return produtos;
    }
}