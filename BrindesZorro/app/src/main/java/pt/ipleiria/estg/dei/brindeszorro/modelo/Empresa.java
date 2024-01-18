package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Empresa {

    private int id, telefone;
    private String nome, email, morada;

    public Empresa(int id, int telefone, String nome, String email, String morada) {
        this.id = id;
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
        this.morada = morada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}