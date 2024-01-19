package pt.ipleiria.estg.dei.brindeszorro;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import pt.ipleiria.estg.dei.brindeszorro.bdlocal.LojaBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Login;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPasswordLogin);

        if(isTokenValido()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        }

    public void onClickLogin(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(!isPasswordValida(password)){
            etPassword.setError(getString(R.string.etPasswordTextError));
            return;
        }
        login(username, password);
        }


    public void login(String username, String password){
        login = new Login(username,password);
        SingletonGestorLoja.getInstance(getApplicationContext()).loginAPI(login, getApplicationContext(), new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(MainActivity.USERNAME, username);
                startActivity(intent);
                finish();
            }
        });
    }
    public boolean isTokenValido(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        System.out.println(sharedPreferences.getString(Public.TOKEN,"TOKEN"));
        if(sharedPreferences.getString(Public.TOKEN,"TOKEN").matches("TOKEN")){
            System.out.println("--->token invalido, não faz login automatico");
            return false;
        }else{
            return true;
        }
    }


    public boolean isPasswordValida(String password){
        if (password == null)
            return false;
        return password.length()>4;
    }



    public void btn_register(View view) {
        Intent RegistarCliente = new Intent(this, RegistarClienteActivity.class);
        startActivity(RegistarCliente);
    }

    public void onClickPassword(View view) {
        Intent ForgetPassword = new Intent(this, RecuperarPasswordActivity.class);
        startActivity(ForgetPassword);
    }

    public void irServidor(View view) {
        Intent intent = new Intent(this, ServidorActivity.class);
        startActivity(intent);
    }
}