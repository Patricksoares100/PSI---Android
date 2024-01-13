package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Favorito {
    private int id;
    private int artigoId, perfilId;
    double valorArtigo;
   private String nomeArtigo , imagem;

    public Favorito(int id, int artigoId, int perfilId, double valorArtigo, String nomeArtigo,  String imagem) {
        this.id = id;
        this.artigoId = artigoId;
        this.perfilId = perfilId;
        this.valorArtigo = valorArtigo;
        this.nomeArtigo = nomeArtigo;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public double getValorArtigo() {
        return valorArtigo;
    }

    public void setValorArtigo(double valorArtigo) {
        this.valorArtigo = valorArtigo;
    }

    public String getNomeArtigo() {
        return nomeArtigo;
    }

    public void setNomeArtigo(String nomeArtigo) {
        this.nomeArtigo = nomeArtigo;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getArtigoId() {return artigoId;}

    public void setArtigoId(int artigoId) {this.artigoId = artigoId;}

    public int getPerfilId() {return perfilId;}

    public void setPerfilId(int perfilId) {this.perfilId = perfilId;}
}
