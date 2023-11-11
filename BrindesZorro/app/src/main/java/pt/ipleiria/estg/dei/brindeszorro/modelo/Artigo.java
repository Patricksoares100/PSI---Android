package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Artigo {
    private int id;
    private double preco;
    private String nome;
    private String descricao;
    private String referencia;
    public Artigo(int id, double preco, String nome, String descricao, String referencia){

        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
        this.referencia = referencia;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
