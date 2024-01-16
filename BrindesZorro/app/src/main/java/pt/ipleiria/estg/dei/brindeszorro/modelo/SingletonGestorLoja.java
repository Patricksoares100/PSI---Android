package pt.ipleiria.estg.dei.brindeszorro.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.brindeszorro.EditarDadosPessoaisActivity;
import pt.ipleiria.estg.dei.brindeszorro.FavoritosActivity;
import pt.ipleiria.estg.dei.brindeszorro.MainActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.bdlocal.LojaBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigoListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaoListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.CarrinhosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FaturasListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FavoritosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.UserListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.UsersListener;
import pt.ipleiria.estg.dei.brindeszorro.utils.LojaJsonParser;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;

    private ArrayList<Artigo> artigos;
    private ArrayList<Avaliacao> avaliacaos;
    private ArrayList<Avaliacao> avaliacaoUser;
    private ArrayList<Favorito> favoritos;
    private ArrayList<Carrinho> carrinhos;
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
    private FavoritosListener favoritosListener;
    private CarrinhosListener carrinhosListener;
    private FaturasListener faturasListener;




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
        avaliacaos = new ArrayList<>();
        users = new ArrayList<>();
        favoritos = new ArrayList<>();
        carrinhos = new ArrayList<>();
        avaliacaoUser = new ArrayList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        String mUrlAPI = sharedPreferences.getString(Public.SERVER_KEY, "http://172.22.21.219:8080/api/");
        Public.SERVER = mUrlAPI;

    }


    // region # SET LISTENERS  #
    public void  setAvaliacaosListener(AvaliacaosListener avaliacaosListener){
            this.avaliacaosListener = avaliacaosListener;
    }
    public void setArtigoListener(ArtigoListener artigoListener){
        this.artigoListener = artigoListener;
    }

    public void setArtigosListener(ArtigosListener artigosListener){
        this.artigosListener = artigosListener;
    }
    public void setFavoritosListener(FavoritosListener favoritosListener){
        this.favoritosListener = favoritosListener;
    }
    public void setCarrinhosListener(CarrinhosListener carrinhosListener){
        this.carrinhosListener = carrinhosListener;
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
    public ArrayList<Carrinho> getCarrinhosBD() {
        carrinhos = lojaBDHelper.getAllCarrinhosBD();
        return new ArrayList<>(carrinhos);
    }

    public ArrayList<Avaliacao> getAllAvaliacaosUserBD(){
        return lojaBDHelper.getAllAvaliacaosUserBD(getUserBD().getId());

    }

    public ArrayList<User> getUsersBD(){
        users = lojaBDHelper.getAllUsersBD();
        return new ArrayList<>(users);
    }

    public User getUserBD(){
        User user = lojaBDHelper.getUserBD();
        return user;
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

    public Favorito getFavorito(int id) {
        for (Favorito fav : favoritos) {
            if (fav.getId() == id)
                return fav;
        }
        return null;
    }
    public Carrinho getCarrinho(int id) {
        for (Carrinho car : carrinhos) {
            if (car.getId() == id)
                return car;
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

    //region # METODO AVALIACAO API #
    public void getAllAvaliacoesAPI(final Context context) {
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();
            if (avaliacaosListener != null){
                avaliacaosListener.onRefreshListaAvaliacaos(lojaBDHelper.getAllAvaliacaosBD());
            }
        } else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, Public.SERVER + "avaliacaos", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("----> response AVALIACAOS API" + response);
                    // response.re .getString('imagem').replace("\\/", "/");
                    avaliacaos = LojaJsonParser.parserJsonAvaliacaos(response);
                    adicionarAvaliacaosBD(avaliacaos);

                    if(avaliacaosListener != null){
                        avaliacaosListener.onRefreshListaAvaliacaos(avaliacaos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> response ERRO AVALIACAO API" + error);
                }
            });

            volleyQueue.add(req);
        }
    }


    public void getAvaliacaoAPI(final Context context, String token) {
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();
            if (avaliacaosListener != null){
                avaliacaosListener.onRefreshListaAvaliacaos(lojaBDHelper.getAllAvaliacaosBD());
            }
        } else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, Public.SERVER + "avaliacao/byuser?token=" + token.toString(), null,new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("----> response AVALIACAOS API" + response);
                    // response.re .getString('imagem').replace("\\/", "/");
                    avaliacaos = LojaJsonParser.parserJsonAvaliacaos(response);
                    adicionarAvaliacaosBD(avaliacaos);

                    if(avaliacaosListener != null){
                        avaliacaosListener.onRefreshListaAvaliacaos(avaliacaos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> response ERRO AVALIACAO API" + error);

                }
            });

            volleyQueue.add(req);
        }
    }
    //endregion

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
        Favorito auxFavorito = getFavorito(idFavorito);
        if (auxFavorito != null) {
            if (lojaBDHelper.removerFavoritoBD(auxFavorito.getId())) {
            }
        }
    }
    // endregion

    // region # METODOS CARRINHO BD #
    public void adicionarCarrinhosBD(ArrayList<Carrinho> carrinhos){
        lojaBDHelper.removerAllCarrinhosBD();
        for (Carrinho c : carrinhos){
            adicionarCarrinhoBD(c);
        }
    }
    public void adicionarCarrinhoBD(Carrinho c) {
        lojaBDHelper.adicionarCarrinhoBD(c);
    }
    public void removerCarrinhoBD(int idCarrinho) {
        Carrinho auxCarrinho = getCarrinho(idCarrinho);
        if (auxCarrinho != null) {
            if (lojaBDHelper.removerCarrinhoBD(auxCarrinho.getId())) {
            }
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
    public void editarCarrinhoBD(Carrinho c){
        //como tamos em memoria ele altera diretamente n precisa de dar save
        Carrinho auxCarrinho = getCarrinho(c.getId());
        if(auxCarrinho != null){
            lojaBDHelper.editarCarrinhoBD(c);
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
        lojaBDHelper.removerAllUsersBD();
        lojaBDHelper.adicionarUserBD(u);
    }

    public void adicionarUsersBD(ArrayList<User> users){
        lojaBDHelper.removerAllUsersBD();
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
            StringRequest request = new StringRequest(Request.Method.POST, Public.SERVER +"users/registo", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //add com sucesso?
                    if(response.contains("sucesso")){
                        System.out.println("----> SUCESSO Signup "+ response);
                       // Toast.makeText(context, R.string.registo_com_sucesso, Toast.LENGTH_SHORT).show();
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
            StringRequest req = new StringRequest(Request.Method.POST, Public.SERVER, new Response.Listener<String>() { //requisição por http, com a nssa configuração de link acima
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
            System.out.println("--->"+Public.SERVER);
            System.out.println("--->"+Public.SERVER.toString());
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, Public.SERVER + "artigos", null, new Response.Listener<JSONArray>() {
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
            StringRequest req = new StringRequest(Request.Method.PUT, Public.SERVER + "users/editar?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("----> SUCESSO Login " + response);
                    SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    adicionarUserBD(LojaJsonParser.parserJsonUser(response));
                    try {
                        System.out.println("---> teste try");
                        JSONObject jsonObject = new JSONObject(response);
                        editor.putString(Public.TOKEN, jsonObject.getString("token"));
                        editor.apply();
                        //listener.onResponse(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        //listener.onResponse(null);
                    }
                 /*   editUserBD(user);
                    if (userListener != null) {
                        userListener.onRefreshUser(MainActivity.EDIT);
                    }*/
                }
            },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error){
                    System.out.println("----> response ERRO USERS API" + error);
                }
                }){
                    protected Map<String, String> getParams () {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nome", user.getNome());
                    params.put("telefone", "" + user.getTelefone());
                    params.put("nif", "" + user.getNif());
                    params.put("morada", user.getMorada());
                    params.put("codigo_postal", user.getCodigo_postal());
                    params.put("localidade", user.getLocalidade());
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
            StringRequest req = new StringRequest(Request.Method.DELETE, Public.SERVER + "users/" + user.getId(), new Response.Listener<String>() {
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

    public void getUserDataAPI(final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();

            if (usersListener != null){
                usersListener.onRefreshListaUsers(lojaBDHelper.getAllUsersBD());
            }
        } else{
            StringRequest req = new StringRequest(Request.Method.GET, Public.SERVER + "users/data?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("---> Sucesso - Ver user: " + response);
                    adicionarUserBD(LojaJsonParser.parserJsonUser(response));

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("----> Erro USER DATA API "+ error.getMessage());
                }
            });

            volleyQueue.add(req);
        }
    }

    public void editPassAPI(Context context, final String token, String passAtual, String novaPass) {
        if (!LojaJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, R.string.sem_liga_a_internet, Toast.LENGTH_LONG).show();
        } else {
            StringRequest req = new StringRequest(Request.Method.PUT, Public.SERVER + "users/atualizarpassword?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("----> resposta Password API" + response);
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse (VolleyError error){
                    System.out.println("----> response ERRO password API" + error);
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Password Invalida", Toast.LENGTH_SHORT).show();
                    }
                }
            }){
                protected Map<String, String> getParams () {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("atualPassword", passAtual.toString());
                    params.put("novaPassword", novaPass.toString());
                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    //endregion

    //region # METODO LOGIN API #
    public void loginAPI(final Login login, final Context context, Response.Listener listener){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  R.string.sem_liga_a_internet, Toast.LENGTH_SHORT).show();

        }else{
            StringRequest request = new StringRequest(Request.Method.POST, Public.SERVER +"users/login", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //add com sucesso?
                    System.out.println("----> SUCESSO Login " + response);
                    SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    adicionarUserBD(LojaJsonParser.parserJsonUser(response));
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            editor.putString(Public.TOKEN, jsonObject.getString("token"));
                            editor.apply();
                            listener.onResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onResponse(null);
                        }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO Login" + error.getMessage() + error);
                    SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Login Invalido", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                    editor.putString(Public.TOKEN, "TOKEN");
                    editor.apply();
                }
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", login.getUsername());
                    params.put("password", login.getPassword());

                    return params;

                }
            };
            volleyQueue.add(request);
        }
    }

    //endregion

    // region #METODO FAVORITO API #

    public  void getAllFavoritosAPI(final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();
            if(favoritosListener != null){
                favoritosListener.onRefreshListaFavoritos(lojaBDHelper.getAllFavoritosBD());
            }
            //LISTENERS vamos buscar os favoritos a bd local
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, Public.SERVER + "favoritos/byuser?token=" + token.toString(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    //fazer sub aqui e
                    System.out.println("---> response Favoritos API:"+ response);
                    favoritos = LojaJsonParser.parserJsonFavoritos(response);
                    adicionarFavoritosBD(favoritos);
                    if(favoritosListener != null){
                        favoritosListener.onRefreshListaFavoritos(favoritos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--->Erro favorito" + error.getMessage());
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }

    public void adicionarFavoritoAPI(final Artigo artigo, final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();
        }else {
            StringRequest req = new StringRequest(Request.Method.POST,Public.SERVER + "favoritos/adicionar?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //fazer sub  aqui
                            System.out.println("--->Add favorito c/ sucesso"+response.toString());
                            Toast.makeText(context, "Artigo adicionado aos favoritos!", Toast.LENGTH_SHORT).show();
                            adicionarFavoritoBD(LojaJsonParser.parserJsonFavorito(response));//recebe em jason para a dicionar a BD tem que converter atraves do parser

                     //listener add com  sucesso? falta codigo
                    if(favoritosListener != null){
                        favoritosListener.onRefreshListaFavoritos(new ArrayList<>());
                    }
                }
            },new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    System.out.println("----> ERRO adicionar favorito api" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Artigo já adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String,String>();
                    params.put("artigo_id", ""+artigo.getId());// tem q ser uma variavel n pode ser hardcoded

                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    public void removerAllFavoritosAPI( final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();

        }else {
            StringRequest req = new StringRequest(Request.Method.DELETE, Public.SERVER + "favoritos/limparfavoritos?token=" + token.toString() ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            lojaBDHelper.removerAllFavoritosBD();
                            System.out.println("--->Favoritos limpo com sucesso"+ response);
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            //remove todos os items do carrinho
                            //listener por toast com livro removido com sucesso e atualizar a vista
                            if(favoritosListener != null){
                                favoritosListener.onRefreshListaFavoritos(new ArrayList<>());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO remover todos os items dos favoritos" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Não há itens nos favoritos para serem removidos!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            volleyQueue.add(req);
        }

    }

    public void adicionarFavoritosCarrinhoAPI( Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();
        }else {
            StringRequest req = new StringRequest(Request.Method.GET,Public.SERVER + "favoritos/passarfavoritoscarrinho?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
            /*JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPI + "favoritos/passarfavoritoscarrinho?token=" + token.toString(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {*/
                    //fazer sub  aqui
                    System.out.println("--->Add favorito ao carrinho c/ sucesso"+response.toString());
                    Toast.makeText(context, "Artigos adicionados ao carrinho!", Toast.LENGTH_SHORT).show();
                    //adicionarCarrinhosBD(LojaJsonParser.parserJsonCarrinhos(response));//recebe em jason para a dicionar a BD tem que converter atraves do parser
                    if(favoritosListener != null){
                        favoritosListener.onRefreshListaFavoritos(new ArrayList<>());
                    }
                }
            },new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    System.out.println("----> ERRO adicionar favoritos ao carrinho api" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Insira um artigo nos favoritos antes de adicionar ao carrinho", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            volleyQueue.add(req);
        }
    }

    //endregion

    // region # METODO CARRINHO API #
    public void adicionarCarrinhoAPI(final Artigo artigo, final Context context, String token, Integer quantidade){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();
        }else {
            StringRequest req = new StringRequest(Request.Method.POST,Public.SERVER + "carrinho/adicionar?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //fazer sub  aqui
                        System.out.println("--->Add carrinho c/ sucesso"+response.toString());
                        Toast.makeText(context, "Artigo adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
                    if(carrinhosListener != null){
                        carrinhosListener.onRefreshListaCarrinhos(new ArrayList<>());
                    }
                }
            },new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    System.out.println("----> ERRO adicionar carrinho api" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Artigo somado ao carrinho", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String,String>();
                    params.put("artigo_id", ""+artigo.getId());// tem q ser uma variavel n pode ser hardcoded
                    params.put("quantidade", quantidade.toString());
                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }

    public  void getAllCarrinhosAPI(final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();
            if(carrinhosListener != null){
                carrinhosListener.onRefreshListaCarrinhos(lojaBDHelper.getAllCarrinhosBD());
            }
            //LISTENERS vamos buscar os favoritos a bd local
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, Public.SERVER + "carrinhos/byuser?token=" + token.toString(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    //fazer sub aqui e
                    System.out.println("---> response Carrinho API:"+ response);
                    carrinhos = LojaJsonParser.parserJsonCarrinhos(response);
                    adicionarCarrinhosBD(carrinhos);
                    if(carrinhosListener != null){
                        carrinhosListener.onRefreshListaCarrinhos(carrinhos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--->Erro carrinho" + error.getMessage());
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }
    public void removerAllCarrinhoAPI( final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();

        }else {
            StringRequest req = new StringRequest(Request.Method.DELETE, Public.SERVER + "carrinhos/limparcarrinho?token=" + token.toString() ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            lojaBDHelper.removerAllCarrinhosBD();
                            System.out.println("--->Carrinho limpo com sucesso"+ response);
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            //remove todos os items do carrinho
                            //listener por toast com livro removido com sucesso e atualizar a vista
                            if(carrinhosListener != null){
                                carrinhosListener.onRefreshListaCarrinhos(new ArrayList<>());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO remover todos os items do carrinho" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Não há itens no carrinho para serem removidos!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            volleyQueue.add(req);
        }

    }
    public void removerLinhaCarrinhoAPI(Carrinho carrinho, final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();

        }else {
            StringRequest req = new StringRequest(Request.Method.DELETE, Public.SERVER + "carrinhos/limparlinhacarrinho?token=" + token.toString() +"&id="+ carrinho.getId(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            lojaBDHelper.removerCarrinhoBD(carrinho.getId());
                            System.out.println("--->Artigo removido com sucesso"+ response);
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            //remove todos os items do carrinho
                            //listener por toast com livro removido com sucesso e atualizar a vista
                            if(carrinhosListener != null){
                                carrinhosListener.onRefreshListaCarrinhos(new ArrayList<>());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO remover artigo do carrinho" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            volleyQueue.add(req);
        }

    }
    public void comprarCarrinhoAPI( final Context context, String token){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();

        }else {

            StringRequest req = new StringRequest(Request.Method.GET, Public.SERVER + "faturas/comprarcarrinho?token=" + token.toString() ,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("--->Carrinho comprado com sucesso"+ response);
                            Toast.makeText(context, "Carrinho comprado com sucesso", Toast.LENGTH_SHORT).show();
                            adicionarFaturaBD(LojaJsonParser.parserJsonFatura(response));
                            if(carrinhosListener != null){
                                carrinhosListener.onRefreshListaCarrinhos(new ArrayList<>());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("----> ERRO remover todos os items do carrinho" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Não há itens no carrinho para efetuar compra!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            volleyQueue.add(req);
        }

    }


    public void aumentarDiminuirQuantidadeCarrinhoAPI(final Carrinho carrinho, final Context context, String token, String sinal){
        if(!LojaJsonParser.isConnectionInternet(context)){
            Toast.makeText(context,  context.getString(R.string.sem_liga_a_internet), Toast.LENGTH_SHORT).show();
        }else {
            System.out.println("---> carrinhoazzzz " + carrinho.getId());
            StringRequest req = new StringRequest(Request.Method.PUT, Public.SERVER + "carrinho/atualizar?token=" + token.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //fazer sub  aqui
                    System.out.println("--->Quantidade artigo carrinho atualizada c/ sucesso!"+response.toString());
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    if(carrinhosListener != null){
                        carrinhosListener.onRefreshListaCarrinhos(carrinhos);
                    }
                }
            },new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    System.out.println("----> ERRO atualizar quantidade artigo carrinho api" + error.getMessage());
                    if(error.networkResponse.statusCode == 401){
                        Toast.makeText(context, "Nao foi possivel adicionar quantidade ao carrinho!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Pedido não pode ser processado", Toast.LENGTH_SHORT).show();
                    }
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String,String>();
                    params.put("id", "" + carrinho.getId());// tem q ser uma variavel n pode ser hardcoded
                    params.put("sinal", sinal.toString());
                    return params;
                }
            };
            volleyQueue.add(req);
        }
    }


    //endregion
}
