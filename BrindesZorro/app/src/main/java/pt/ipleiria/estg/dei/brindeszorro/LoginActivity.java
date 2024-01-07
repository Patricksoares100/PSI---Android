package pt.ipleiria.estg.dei.brindeszorro;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Login;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPasswordLogin);


    }

    public void onClickLogin(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        login = new Login(username,password);
        

        if(!isPasswordValida(password)){
            etPassword.setError(getString(R.string.etPasswordTextError));
            return;
        }
        if(!isLoginValido(username, password)){
            Toast.makeText(this, R.string.username_e_ou_password_incorreto, Toast.LENGTH_SHORT).show();
        }else{

            SingletonGestorLoja.getInstance(getApplicationContext()).loginAPI(login, getApplicationContext());

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        //SingletonGestorLoja.getInstance(getApplicationContext()).signupAPI(signup, getApplicationContext());

       /* */
    }
    public boolean isLoginValido(String username, String password){
        return true;
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
}