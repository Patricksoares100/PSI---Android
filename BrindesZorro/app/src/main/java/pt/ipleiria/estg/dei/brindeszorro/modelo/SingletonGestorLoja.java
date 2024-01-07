package pt.ipleiria.estg.dei.brindeszorro.modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.brindeszorro.EditarDadosPessoaisActivity;
import pt.ipleiria.estg.dei.brindeszorro.MainActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.bdlocal.LojaBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigoListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaoListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.UserListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.UsersListener;
import pt.ipleiria.estg.dei.brindeszorro.utils.LojaJsonParser;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;
    private ArrayList<Artigo> artigos;
    private ArrayList<Avaliacao> avaliacaos;
    private ArrayList<Favorito> favoritos;
    private ArrayList<User> users;
    //private User user;
    private static SingletonGestorLoja instance = null;
    private LojaBDHelper lojaBDHelper = null;   // Alinea 2.2 Ficha 8 Books - criar um atributo (nome)BD do tipo (nomeModelo)BDHelper;
    private static RequestQueue volleyQueue = null;
    private ArtigosListener artigosListener;
    private ArtigoListener artigoListener;
    private AvaliacaoListener avaliacaoListener;
    private AvaliacaosListener avaliacaosListener;
    private UserListener userListener;
    private UsersListener usersListener;


  private static final String mUrlAPI = "http://172.22.21.219:8080/api/";//depois concatenas com o resto - como levou o:8080 retirou-se o .../PSI_Web/backend/web


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


    // region # SET LISTENERS  #

    public void setArtigoListener(ArtigoListener artigoListener){
        this.artigoListener = artigoListener;
    }

    public void setArtigosListener(ArtigosListener artigosListener1){
        this.artigosListener = artigosListener;
    }

    public void setUserListener(UserListener userListener){
        this.userListener = userListener;
    }

    public void setUsersListener(UsersListener usersListener){
        this.usersListener = usersListener;
    }


    //endregion

    // region # SET ARRAYLISTS  #
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

    public ArrayList<User> getUsersBD(){
        users = lojaBDHelper.getAllUsersBD();
        return new ArrayList<>(users);
    }
    //endregion

    // region # GETs #
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

    public User getUser(int idUser ) {

        for (User user : users) {
            if (user.getId() == idUser)
                return user;
        }
        return null;
    }

    //endregion

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

    // region # METODOS Favoritos BD #

    public void adicionarFavoritosBD(ArrayList<Favorito> favoritos){
        lojaBDHelper.removerAllFavoritosBD();
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

    //region # METODOS USER BD #
    public void adicionarUserBD(User u){
        lojaBDHelper.adicionarUserBD(u);
    }

    public void adicionarUsersBD(ArrayList<User> users){
        lojaBDHelper.removerAllArtigosBD();
        for (User u : users){
            adicionarUserBD(u);
        }
    }

    public void editUserBD(User user){
        User auxUser = getUser(user.getId());

        if (auxUser != null){
            if (lojaBDHelper.editarUserBD(user)) {
                auxUser.setNome(user.getNome());
                auxUser.setTelefone(user.getTelefone());
                auxUser.setNif(user.getNif());
                auxUser.setMorada(user.getMorada());
                auxUser.setCodigo_postal(user.getCodigo_postal());
                auxUser.setLocalidade(user.getLocalidade());
                auxUser.setToken(user.getToken());
            }
        }
    }

    public void removerUserBD(int idUser) {
        User auxUser = getUser(idUser);
        if (auxUser != null) {
            if (lojaBDHelper.removerUserBD(idUser)) {
                users.remove(auxUser);
            }
        }
    }
    //endregion

    //region # ARTIGOS BD #
    public void adicionarArtigosBD(ArrayList<Artigo> artigos){
        lojaBDHelper.removerAllArtigosBD();
        for (Artigo a : artigos){
            adicionarArtigoBD(a);
        }
    }

    public void adicionarArtigoBD(Artigo a) {
        lojaBDHelper.adicionarArtigoBD(a);
    }
    //endregion

    //region # METODOS SIGNUP API #
    public void signupAPI(final Signup signup, final Context context){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  R.string.sem_liga_a_internet, Toast.LENGTH_SHORT).show();

        }else{
            StringRequest request = new StringRequest(Request.Method.POST, mUrlAPI +"users/registo", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //add com sucesso?
                    if(response.contains("sucesso")){
                        System.out.println("----> SUCESSO Signup "+ response);
                        Toast.makeText(context, R.string.registo_com_sucesso, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO Signup" + error.getMessage() + error);
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

    // region # AVALIACAO API #

    public void adicionarAvaliacaoAPI(final Avaliacao avaliacao, final Context context) {
        if (!LojaJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();
            //aqui temos de ir buscar na base de dados local se nao tiver net

        } else {
            StringRequest req = new StringRequest(Request.Method.POST, mUrlAPI, new Response.Listener<String>() { //requisição por http, com a nssa configuração de link acima
                @Override
                public void onResponse(String response) {
                    adicionarArtigoBD(LojaJsonParser.parserJsonArtigo(response));
                    if (avaliacaoListener != null) {
                        avaliacaoListener.onRefreshAvaliacao(MainActivity.ADD);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("---> ERRO adicionarAvaliacaoAPI: " + error.getMessage());
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    // possivelmente será puxado aqui o token
                    params.put("comentario", avaliacao.getComentario());
                    params.put("classificacao", "" + avaliacao.getClassificacao());
                    params.put("artigoId", "" + avaliacao.getArtigoId());

                    return params;
                }
            };
            volleyQueue.add(req);
        }

    }

    // endregion

    //region # METODOS ARTIGOS API #
    public void getAllArtigosAPI(final Context context) {
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();

            if (artigosListener != null){
                artigosListener.onRefreshListaArtigos(lojaBDHelper.getAllArtigosBD());
            }
        } else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "artigos", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("----> response ARTIGOS API" + response);
                   // response.re .getString('imagem').replace("\\/", "/");
                    artigos = LojaJsonParser.parserJsonArtigos(response);
                    adicionarArtigosBD(artigos);

                    if(artigosListener != null){
                        artigosListener.onRefreshListaArtigos(artigos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> response ERRO ARTIGOS API" + error);
                }
            });

            volleyQueue.add(req);
        }
    }
    //endregion

    //region # METODOS USERS API #

    public void editUserAPI(final User user, Context context, final String token) {
        if (!LojaJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();
        } else {
            StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPI + "users/" + user.getId(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    editUserBD(user);
                    if (userListener != null) {
                        userListener.onRefreshUser(MainActivity.EDIT);
                    }
                }
            },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error){
                    System.out.println("----> response ERRO USERS API" + error);
                }
                }){
                    protected Map<String, String> getParams () {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("token", user.getToken());
                    params.put("nome", user.getNome());
                    params.put("telefone", "" + user.getTelefone());
                    params.put("nif", "" + user.getNif());
                    params.put("morada", "" + user.getMorada());
                    params.put("codigo_postas", "" + user.getCodigo_postal());
                    params.put("localidade", "" + user.getLocalidade());
                    return params;
                }
                };
            volleyQueue.add(req);
            }
        }

    public void removerUserAPI (final User user, final Context context) {
        if (!LojaJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();
        } else {
            StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPI + "users/" + user.getId(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    removerUserBD(user.getId());
                    if (userListener != null){
                        userListener.onRefreshUser(MainActivity.DELETE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("---> ERRO removerUserAPI: " + error.getMessage());
                }
            });
            volleyQueue.add(req);
        }
    }

    public void getAllUserAPI(final Context context){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();

            if (usersListener != null){
                usersListener.onRefreshListaUsers(lojaBDHelper.getAllUsersBD());
            }
        } else{

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "users/login", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    users = LojaJsonParser.parserJsonUsers(response);
                    System.out.println("---> Sucesso - Ver users: " + response);
                    adicionarUsersBD(users);

                    if(usersListener != null){
                        usersListener.onRefreshListaUsers(users);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("----> Erro USER API "+ error.getMessage());
                }
            });

            volleyQueue.add(req);
        }
    }

    //endregion

    //region # METODO LOGIN API #
    public void loginAPI(final Signup signup, final Context context){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  R.string.sem_liga_a_internet, Toast.LENGTH_SHORT).show();

        }else{
            StringRequest request = new StringRequest(Request.Method.POST, mUrlAPI +"users/registo", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //add com sucesso?
                    if(response.contains("sucesso")){
                        System.out.println("----> SUCESSO Signup "+ response);
                        Toast.makeText(context, R.string.registo_com_sucesso, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO Signup" + error.getMessage() + error);
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
}
