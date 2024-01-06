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

                int idArtigo = artigo.getInt("id");
                String nome = artigo.getString("nome");
                String descricao = artigo.getString("descricao");
                String referencia = artigo.getString("referencia");
                double preco = artigo.getDouble("preco");
                int stock_atual = artigo.getInt("stock_atual");
                int iva_id = artigo.getInt("iva_id");
                int fornecedor_id = artigo.getInt("fornecedor_id");
                int categoria_id = artigo.getInt("categoria_id");
                int perfil_id = artigo.getInt("perfil_id");
                String  imagem = artigo.getString("imagens");

                Artigo auxArtigo = new Artigo(idArtigo, nome, descricao, referencia, preco, stock_atual, iva_id, fornecedor_id, categoria_id, perfil_id,imagem);
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

            int idArtigo = artigo.getInt("id");
            String nome = artigo.getString("nome");
            String descricao = artigo.getString("descricao");
            String referencia = artigo.getString("referencia");
            double preco = artigo.getDouble("preco");
            int stock_atual = artigo.getInt("stock_atual");
            int iva_id = artigo.getInt("iva_id");
            int fornecedor_id = artigo.getInt("fornecedor_id");
            int categoria_id = artigo.getInt("categoria_id");
            int perfil_id = artigo.getInt("perfil_id");
            String imagem = artigo.getString("imagens");

            auxArtigo = new Artigo(idArtigo, nome, descricao, referencia, preco, stock_atual,iva_id,fornecedor_id,categoria_id,perfil_id,imagem);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxArtigo;
    }
    //endregion
}
