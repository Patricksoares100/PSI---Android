package pt.ipleiria.estg.dei.brindeszorro.modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.bdlocal.LojaBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.utils.LojaJsonParser;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;
    private ArrayList<Artigo> artigos;
    private ArrayList<Avaliacao> avaliacaos;
    private ArrayList<Favorito> favoritos;
    private static SingletonGestorLoja instance = null;
    private LojaBDHelper lojaBDHelper = null;   // Alinea 2.2 Ficha 8 Books - criar um atributo (nome)BD do tipo (nomeModelo)BDHelper;

    private static RequestQueue volleyQueue = null;
  private static final String mUrlAPISignup = "http://10.0.0.2/PlataformaSI/ProjetoPSI/PSI_Web/web/backend/web/api/users/registo";
   // private static final String mUrlAPISignup = "localhost/PlataformaSI/ProjetoPSI/PSI_Web/web/backend/web/api/users/registo";


    public static synchronized SingletonGestorLoja getInstance(Context context){
        //corrigir todas as chamadas ao método getInstance() para que passe a receber o contexto
        //adequado - ficha 08 -2.1 - adicionado getContext() dentro do getInstance
        if (instance == null) {
            instance = new SingletonGestorLoja(context);
            volleyQueue = Volley.newRequestQueue(context);//API
        }
        return instance;
    }

    // Alinea 2.1 Ficha 8 Books - alterar o construtor e receber um parâmetro do tipo Context
    // Necessário para instanciar a classe da base de dados
    private SingletonGestorLoja(Context context) {
        lojaBDHelper = new LojaBDHelper(context);
    }


    public ArrayList<Fatura> getFaturasBD(){
        faturas = lojaBDHelper.getAllFaturasBD();
        return new ArrayList<>(faturas);}

    public ArrayList<Artigo> getArtigosBD(){
        artigos = lojaBDHelper.getAllArtigosBD();
        return  new ArrayList<>(artigos);
    }

    public ArrayList<Avaliacao> getAvaliacaosBD(){
        avaliacaos = lojaBDHelper.getAllAvaliacaosBD();
        return  new ArrayList<>(avaliacaos);
    }

    public ArrayList<Favorito> getFavoritosBD() {
        favoritos = lojaBDHelper.getAllFavoritosBD();
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

    public Fatura getFatura(int idFatura){

        for (Fatura f : faturas) {
            if(f.getId() == idFatura)
                return f;
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
        lojaBDHelper.removerAllAvaliacaosBD();
        for (Avaliacao a : avaliacaos){
            adicionarAvaliacaoBD(a);
        }
    }

    public void adicionarAvaliacaoBD(Avaliacao a) {
        lojaBDHelper.adicionarAvaliacaoBD(a);
    }

    public void editarAvaliacaoBD(Avaliacao a) {
        Avaliacao auxAvaliacao = getAvaliacao(a.getId());

        if (auxAvaliacao != null) {
            if (lojaBDHelper.editarAvaliacaoBD(a)) {
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
            if (lojaBDHelper.removerAvaliacaoBD(idAvaliacao)) {
                avaliacaos.remove(auxAvaliacao);
            }
        }
    }

    // endregion

    //region # METODOS SIGNUP API #
    public void signupAPI(final Signup signup, final Context context){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  R.string.sem_liga_a_internet, Toast.LENGTH_SHORT).show();

        }else{
            StringRequest request = new StringRequest(Request.Method.POST, mUrlAPISignup, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //add com sucesso?
                    System.out.println("----> ACERTOU SIGNUP");

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO Signup" + error.getMessage());
                }
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", signup.getUsername());
                    params.put("email", signup.getEmail());
                    params.put("password", signup.getPassword());
                    params.put("nome", signup.getNome());
                    params.put("morada", signup.getMorada());
                    params.put("codigo_postal", signup.getCodigoPostal());
                    params.put("localidade", signup.getLocalidade());
                    params.put("telefone", ""+signup.getTelefone());
                    params.put("nif", ""+signup.getNif());
                    return params;

                }
            };
            volleyQueue.add(request);
        }
    }
    //endregion

    // region # METODOS FATURAS BD #

    public void adicionarFaturasBD(ArrayList<Fatura> faturas){
        lojaBDHelper.removerAllFaturasBD();
        for (Fatura f : faturas){
            adicionarFaturaBD(f);
        }
    }

    public void adicionarFaturaBD(Fatura f) {
        lojaBDHelper.adicionarFaturaBD(f);
    }

    public void editarFaturaBD(Fatura f) {
        Fatura auxFatura = getFatura(f.getId());

        if (auxFatura != null) {
            if (lojaBDHelper.editarFaturaBD(f)) {
                auxFatura.setData(f.getData());
                auxFatura.setValorFatura(f.getValorFatura());
                auxFatura.setEstado(f.getEstado());
                auxFatura.setPerfil_id(f.getPerfil_id());
            }
        }
    }

    public void removerFaturaBD(int idFatura) {
        Fatura auxFatura = getFatura(idFatura);
        if (auxFatura != null) {
            if (lojaBDHelper.removerFaturaBD(idFatura)) {
                faturas.remove(auxFatura);
            }
        }
    }

    // endregion

    // region # METODOS Favoritos BD #

    public void adicionarFavoritosBD(ArrayList<Favorito> favoritos){
        lojaBDHelper.removerAllFavoritos();
        for (Favorito f : favoritos){
            adicionarFavoritoBD(f);
        }
    }
    public void adicionarFavoritoBD(Favorito f) {
        lojaBDHelper.adicionarFavoritoBD(f);
    }

    public void removerFavoritoBD(int idFavorito) {
        Favorito auxFavorito = getFavoritos(idFavorito);
        if (auxFavorito != null) {
            if (lojaBDHelper.removerFaturaBD(idFavorito)) {
                faturas.remove(auxFavorito);
            }
        }
    }
    // endregion
}
