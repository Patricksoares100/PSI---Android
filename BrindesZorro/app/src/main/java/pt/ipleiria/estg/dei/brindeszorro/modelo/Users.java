package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Users {
    private int id,telefone,nif;
    private String nome, morada, codigo_postal, localidade, token;

    public Users(int id, String nome, int telefone, int nif, String morada, String codigo_postal, String localidade, String token) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.nif = nif;
        this.morada = morada;
        this.codigo_postal = codigo_postal;
        this.localidade = localidade;
        this.token = token;
    }


    public int getId() {return id;}

    public void setId(int id) {this.id = id; }

    public int getTelefone() {return telefone;}

    public void setTelefone(int telefone) {this.telefone = telefone;}

    public int getNif() {return nif;}

    public void setNif(int nif) {this.nif = nif;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getMorada() {return morada;}

    public void setMorada(String morada) {this.morada = morada;}

    public String getCodigo_postal() {return codigo_postal;}

    public void setCodigo_postal(String codigo_postal) {this.codigo_postal = codigo_postal;}

    public String getLocalidade() {return localidade;}

    public void setLocalidade(String localidade) {this.localidade = localidade; }


    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}
}
