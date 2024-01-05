package pt.ipleiria.estg.dei.brindeszorro.modelo;

public class Signup {

    private int id, telefone, nif;
    private String username, email, password, nome, morada, codigoPostal, localidade;


    public Signup(int id, String username, String email, String password, String nome, String morada, String codigoPostal, String localidade, int telefone, int nif) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.morada = morada;
        this.codigoPostal = codigoPostal;
        this.localidade = localidade;
        this.telefone = telefone;
        this.nif = nif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getId() {
        return id;
    }

    public int getTelefone() {
        return telefone;
    }

    public int getNif() {
        return nif;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getLocalidade() {
        return localidade;
    }
}
