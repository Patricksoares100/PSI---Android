package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class LinhaFatura {

    private int id, quantidade, idArtigo;
    private String nome, imagem;
    private double valor, valor_iva, preco;

    public LinhaFatura(int id, int quantidade,  double valor,  double valor_iva, String nome, double preco,int idArtigo, String imagem) {
        this.id = id;
        this.quantidade = quantidade;
        this.nome = nome;
        this.valor = valor;
        this.valor_iva = valor_iva;
        this.preco = preco;
        this.imagem = imagem;
        this.idArtigo = idArtigo;
    }

    public String getImagem() {
        return imagem;
    }

    public int getIdArtigo() {
        return idArtigo;
    }

    public void setIdArtigo(int idArtigo) {
        this.idArtigo = idArtigo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor_iva() {
        return valor_iva;
    }

    public void setValor_iva(double valor_iva) {
        this.valor_iva = valor_iva;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}