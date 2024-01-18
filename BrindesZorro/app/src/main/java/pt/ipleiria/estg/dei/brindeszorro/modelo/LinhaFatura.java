package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class LinhaFatura {

    private int id, quantidade, artigo_id, fatura_id;
    private double valor, valor_iva;

    public LinhaFatura(int id, int quantidade, int artigo_id, int fatura_id, double valor, double valor_iva) {
        this.id = id;
        this.quantidade = quantidade;
        this.artigo_id = artigo_id;
        this.fatura_id = fatura_id;
        this.valor = valor;
        this.valor_iva = valor_iva;
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

    public int getArtigo_id() {
        return artigo_id;
    }

    public void setArtigo_id(int artigo_id) {
        this.artigo_id = artigo_id;
    }

    public int getFatura_id() {
        return fatura_id;
    }

    public void setFatura_id(int fatura_id) {
        this.fatura_id = fatura_id;
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
}
