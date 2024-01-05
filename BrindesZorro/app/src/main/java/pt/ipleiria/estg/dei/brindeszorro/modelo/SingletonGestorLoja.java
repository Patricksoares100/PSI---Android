package pt.ipleiria.estg.dei.brindeszorro.modelo;

import android.content.Context;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.bdlocal.ArtigoBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.bdlocal.AvaliacaoBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.bdlocal.FavoritoBDHelper;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;
    private ArrayList<Artigo> artigos;
    private ArrayList<Avaliacao> avaliacaos;
    private ArrayList<Favorito> favoritos;
    private static SingletonGestorLoja instance = null;
    private ArtigoBDHelper artigoBDHelper = null;   // Alinea 2.2 Ficha 8 Books - criar um atributo (nome)BD do tipo (nomeModelo)BDHelper;
    private AvaliacaoBDHelper avaliacaoBDHelper = null;
    private FavoritoBDHelper favoritoBDHelper = null;


    public static synchronized SingletonGestorLoja getInstance(Context context) {
        //corrigir todas as chamadas ao método getInstance() para que passe a receber o contexto
        //adequado - ficha 08 -2.1 - adicionado getContext() dentro do getInstance
        if (instance == null)
            instance = new SingletonGestorLoja(context);
        return instance;
    }

    // Alinea 2.1 Ficha 8 Books - alterar o construtor e receber um parâmetro do tipo Context
    // Necessário para instanciar a classe da base de dados
    private SingletonGestorLoja(Context context) {
        artigoBDHelper = new ArtigoBDHelper(context);
        avaliacaoBDHelper = new AvaliacaoBDHelper(context);
        favoritoBDHelper = new FavoritoBDHelper(context);

        //gerarDadosFaturas();
        //gerarDadosArtigos();
    }

    /*private void gerarDadosFaturas(){
        faturas = new ArrayList<>();

        faturas.add(new Fatura(1,35,"paga"));
        faturas.add(new Fatura(2,40,"paga"));
        faturas.add(new Fatura(3,50,"não paga"));
        faturas.add(new Fatura(4,60,"paga"));

    }

    private void gerarDadosArtigos(){
        artigos = new ArrayList<>();

        artigos.add(new Artigo(1, 10.5, "Artigo 1","Descrição do artigo 1", "#ART0124" ));
        artigos.add(new Artigo(2, 27, "Artigo 2","Detalhes do artigo 2", "#ART1569" ));
    }*/

    public ArrayList<Fatura> getFaturas() {
        return new ArrayList<>(faturas);
    }

    public ArrayList<Artigo> getArtigosBD() {
        artigos = artigoBDHelper.getAllArtigosBD();
        return new ArrayList<>(artigos);
    }

    public ArrayList<Avaliacao> getAvaliacaosBD() {
        avaliacaos = avaliacaoBDHelper.getAllAvaliacaosBD();
        return new ArrayList<>(avaliacaos);
    }

    public ArrayList<Favorito> getFavoritosBD() {
        favoritos = favoritoBDHelper.getAllFavoritosBD();
        return new ArrayList<>(favoritos);
    }

    public Artigo getArtigo(int id) {  //recebe id por parametro
        for (Artigo art : artigos) {   //vai percorrer o array artigo
            if (art.getId() == id) {   //se algum dos artigos for igual ao id do parametro recebido em cima
                return art;     //vai devolver um objeto artigo
            }
        }
        return null;       //caso contrario devolve nulo
    }

    // Alinea 7.2.1 Ficha 5 Books - Para aceder de forma correta a avaliacao selecionada, implementamos o método getAvaliacao(int idAvaliacao)
    public Avaliacao getAvaliacao(int idAvaliacao) {

        for (Avaliacao a : avaliacaos) {
            if (a.getId() == idAvaliacao)
                return a;
        }
        return null;
    }

    public Favorito getFavoritos(int idFavoritos) {
        for (Favorito fav : favoritos) {
            if (fav.getId() == idFavoritos)
                return fav;
        }
        return null;
    }
    // region # METODOS AVALIACAOS BD #
    // Alinea 2.4 Ficha 8 Books - métodos adicionar, remover, editar e get

    public void adicionarAvaliacaosBD(ArrayList<Avaliacao> avaliacaos){
        avaliacaoBDHelper.removerAllAvaliacaosBD();
        for (Avaliacao a : avaliacaos){
            adicionarAvaliacaoBD(a);
        }
    }

    public void adicionarAvaliacaoBD(Avaliacao a) {
        avaliacaoBDHelper.adicionarAvaliacaoBD(a);
    }

    public void editarAvaliacaoBD(Avaliacao a) {
        Avaliacao auxAvaliacao = getAvaliacao(a.getId());

        if (auxAvaliacao != null) {
            if (avaliacaoBDHelper.editarAvaliacaoBD(a)) {
                auxAvaliacao.setComentario(a.getComentario());
                auxAvaliacao.setClassificacao(a.getClassificacao());
                auxAvaliacao.setArtigoId(a.getArtigoId());
                auxAvaliacao.setPerfilId(a.getPerfilId());
            }
        }
    }

    public void removerAvaliacaoBD(int idAvaliacao) {
        Avaliacao auxAvaliacao = getAvaliacao(idAvaliacao);
        if (auxAvaliacao != null) {
            if (avaliacaoBDHelper.removerAvaliacaoBD(idAvaliacao)) {
                avaliacaos.remove(auxAvaliacao);
            }
        }
    }

    // endregion
}
