package pt.ipleiria.estg.dei.brindeszorro.modelo;

import pt.ipleiria.estg.dei.brindeszorro.utils.MqttManager;

public class Artigo {
    private int id, stock_atual,iva,media_avaliacoes,num_avaliacoes ;
    private String nome, descricao, referencia, imagem, fornecedor, categoria;
    private double preco;


    public Artigo(int id, String nome, String descricao, String referencia, double preco, int stock_atual, int iva, String fornecedor, String categoria, int media_avaliacoes, int num_avaliacoes, String imagem) {
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
        this.media_avaliacoes = media_avaliacoes;
        this.num_avaliacoes = num_avaliacoes;
    }

    public int getMedia_avaliacoes() {
        return media_avaliacoes;
    }

    public void setMedia_avaliacoes(int media_avaliacoes) {
        this.media_avaliacoes = media_avaliacoes;
    }

    public int getNum_avaliacoes() {
        return num_avaliacoes;
    }

    public void setNum_avaliacoes(int num_avaliacoes) {
        this.num_avaliacoes = num_avaliacoes;
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
    //MqttManager mqttManager = new MqttManager("tcp://seu-servidor-mosquitto:1883", "seu-client-id");
    //mqttManager.subscribe("seu-topico");
    //mqttManager.publish("seu-topico", "Mensagem a ser publicada");

}