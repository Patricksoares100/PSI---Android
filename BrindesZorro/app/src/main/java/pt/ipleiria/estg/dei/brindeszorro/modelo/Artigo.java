package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Artigo {
    private int id, stock_atual,iva;
    private String nome, descricao, referencia, imagem, fornecedor, categoria;
    private double preco;


    public Artigo(int id, String nome, String descricao, String referencia, double preco, int stock_atual, int iva, String fornecedor, String categoria, String imagem) {
        this.id = id;
        this.stock_atual = stock_atual;
        this.nome = nome;
        this.descricao = descricao;
        this.referencia = referencia;
        this.imagem = imagem;
        this.iva = iva;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        //this.perfil = perfil;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock_atual() {
        return stock_atual;
    }

    public void setStock_atual(int stock_atual) {
        this.stock_atual = stock_atual;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}