package pt.ipleiria.estg.dei.brindeszorro.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

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
}
