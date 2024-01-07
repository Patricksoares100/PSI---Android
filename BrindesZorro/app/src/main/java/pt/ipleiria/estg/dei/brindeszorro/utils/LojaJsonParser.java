package pt.ipleiria.estg.dei.brindeszorro.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Signup;
import pt.ipleiria.estg.dei.brindeszorro.modelo.User;

public class LojaJsonParser {
    //vai ser um JsonParser unico
    //criar region para facilitar

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static String parserJsonLogin(String response){
        String token = null;
        try{
            JSONObject login = new JSONObject(response);
            if (login.getBoolean("success"))
                token = login.getString("token");

        }catch (JSONException e){
            e.printStackTrace();
        }
        return token;
    }

    public static Signup parserJsonSignup(String response){

        Signup auxSignup = null;
        try{
            JSONObject  signup = new JSONObject(response);
            int id = signup.getInt("id");
            String username = signup.getString("username");
            String email = signup.getString("email");
            String password = signup.getString("password");
            String nome = signup.getString("nome");
            String morada = signup.getString("morada");
            String codigoPostal = signup.getString("codigoPostal");
            String localidade = signup.getString("localidade");
            int telefone = signup.getInt("telefone");
            int nif = signup.getInt("nif");

            auxSignup = new Signup(id, username,email,password,
                    nome,morada,codigoPostal,localidade,telefone,nif);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return auxSignup;
    }

    //region # MÉTODOS ARTIGOS JSON #
    public static ArrayList<Artigo> parserJsonArtigos(JSONArray response) {
        ArrayList<Artigo> artigos = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject artigo = (JSONObject) response.get(i);

                int id = artigo.getInt("id");
                String nome = artigo.getString("nome");
                String descricao = artigo.getString("descricao");
                String referencia = artigo.getString("referencia");
                double preco = artigo.getDouble("preco");
                int stock_atual = artigo.getInt("stock_atual");
                int iva = artigo.getInt("iva");
                String fornecedor = artigo.getString("fornecedor");
                String categoria = artigo.getString("categoria");
                int media_avaliacoes = artigo.getInt("media_avaliacoes");
                int num_avaliacoes = artigo.getInt("num_avaliacoes");
                String  imagem = artigo.getString("imagem");

                Artigo auxArtigo = new Artigo(id, nome, descricao, referencia, preco, stock_atual, iva, fornecedor, categoria, media_avaliacoes, num_avaliacoes,imagem);
                artigos.add(auxArtigo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return artigos;
    }

    // vai ler o json e criar um objeto Artigo
    public static Artigo parserJsonArtigo(String response) {
        Artigo auxArtigo = null;

        try {
            JSONObject artigo = new JSONObject(response);

            int id = artigo.getInt("id");
            String nome = artigo.getString("nome");
            String descricao = artigo.getString("descricao");
            String referencia = artigo.getString("referencia");
            double preco = artigo.getDouble("preco");
            int stock_atual = artigo.getInt("stock_atual");
            int iva = artigo.getInt("iva");
            String fornecedor = artigo.getString("fornecedor");
            String categoria = artigo.getString("categoria");
            //String perfil = artigo.getString("perfil");
            int media_avaliacoes = artigo.getInt("media_avaliacoes");
            int num_avaliacoes = artigo.getInt("num_avaliacoes");
            String imagem = artigo.getString("imagem");

            auxArtigo = new Artigo(id, nome, descricao, referencia, preco, stock_atual,iva,fornecedor,categoria,media_avaliacoes, num_avaliacoes,imagem);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxArtigo;
    }
    //endregion


    //region # MÉTODOS User JSON #

    public static ArrayList<User> parserJsonUsers(JSONArray response) {
        ArrayList<User> users = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject user = (JSONObject) response.get(i);

                int id = user.getInt("id");
                String nome = user.getString("nome");
                int telefone = user.getInt("telefone");
                int nif = user.getInt("nif");
                String morada = user.getString("morada");
                String codigo_postal = user.getString("codigo_postal");
                String localidade = user.getString("localidade");
                String token = user.getString("token");

                User auxUser = new User(id, nome, telefone, nif, morada, codigo_postal, localidade, token);
                users.add(auxUser);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }


    // vai ler o json e criar um objeto User
    public static User parserJsonUser(String response) {
        User auxUser = null;

        try {
            JSONObject user = new JSONObject(response);

            int id = user.getInt("id");
            String nome = user.getString("nome");
            int telefone = user.getInt("telefone");
            int nif = user.getInt("nif");
            String morada = user.getString("morada");
            String codigo_postal = user.getString("codigo_postal");
            String localidade = user.getString("localidade");
            String token = user.getString("token");

            auxUser = new User(id, nome, telefone, nif, morada, codigo_postal, localidade, token);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxUser;
    }
    //endregion
}
