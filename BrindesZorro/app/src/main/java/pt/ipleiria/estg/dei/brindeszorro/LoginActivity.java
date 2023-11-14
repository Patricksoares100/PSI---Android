package pt.ipleiria.estg.dei.brindeszorro;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmailLogin);
        etPassword = findViewById(R.id.etPasswordLogin);


    }

    public void onClickLogin(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(!isEmailValido(email)){
            etEmail.setError(getString(R.string.etFormatoInvalido));
            return;
        }

        if(!isPasswordValida(password)){
            etPassword.setError(getString(R.string.etPasswordTextError));
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    public boolean isEmailValido(String email) {
        if (email == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
        Intent ForgetPassword = new Intent(this, RecuperarPassword.class);
        startActivity(ForgetPassword);
    }
}