package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Artigo {
    private int id, stock_atual, iva_id, fornecedor_id, categoria_id, perfil_id;
    private String nome,descricao, referencia;
    private double preco;


    public Artigo(int id, String nome,String descricao, String referencia,double preco, int stock_atual, int fornecedor_id, int categoria_id, int perfil_id){

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.referencia = referencia;
        this.preco = preco;
        this.stock_atual = stock_atual;
        this.iva_id = iva_id;
        this.fornecedor_id = fornecedor_id;
        this.categoria_id = categoria_id;
        this.perfil_id = perfil_id;


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
