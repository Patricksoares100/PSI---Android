package pt.ipleiria.estg.dei.brindeszorro;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

    }

    public void onClickLogin(View view) {
        String email = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if(!isUserNamelValido(email)){
            etUserName.setError(getString(R.string.etUserNameInvalido));
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



    public boolean isUserNamelValido(String email) {
        if (email == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValida(String password){
        if (password == null)
            return false;
        return password.length()>4;
    }
}