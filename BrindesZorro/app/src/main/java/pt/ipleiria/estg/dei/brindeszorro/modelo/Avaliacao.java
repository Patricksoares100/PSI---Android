package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Avaliacao {
    private int id;
    private String comentario;
    private int classificacao;
    private int artigoId;
    private int perfilId;

    public Avaliacao(int id, String comentario, int classificacao, int artigoId, int perfilId) {
        this.id = id;
        this.comentario = comentario;
        this.classificacao = classificacao;
        this.artigoId = artigoId;
        this.perfilId = perfilId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public int getArtigoId() {
        return artigoId;
    }

    public void setArtigoId(int artigoId) {
        this.artigoId = artigoId;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }
}
