package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Favorito {
    private int id;
    private int artigoId;
    private int perfilId;

    public Favorito(int id, int artigoId, int perfilId) {
        this.id = id;
        this.artigoId = artigoId;
        this.perfilId = perfilId;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getArtigoId() {return artigoId;}

    public void setArtigoId(int artigoId) {this.artigoId = artigoId;}

    public int getPerfilId() {return perfilId;}

    public void setPerfilId(int perfilId) {this.perfilId = perfilId;}
}
