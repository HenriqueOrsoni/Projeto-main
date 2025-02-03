package entities;

public class Cliente extends Pessoa {
    private String cpf;

    public Cliente(String nome, int id, String cpf) {
        super(nome, id);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
