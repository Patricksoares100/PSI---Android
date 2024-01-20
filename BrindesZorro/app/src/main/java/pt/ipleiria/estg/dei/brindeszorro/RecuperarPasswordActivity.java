package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class RecuperarPasswordActivity extends AppCompatActivity {
    private EditText etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        etemail = findViewById(R.id.etEmailRecPassword);

    }


    public void recuperarPassword(View view){
        String email = etemail.getText().toString();

        if(email.isEmpty()){
            Toast.makeText(this, R.string.preencha_o_email, Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.length() <= 4) {
            Toast.makeText(this, R.string.email_invalido, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.contains("@")) {
            etemail.setError(getString(R.string.etFormatoInvalido));
            Toast.makeText(this, R.string.email_em_formato_inv_lido, Toast.LENGTH_SHORT).show();
            return;
        }

        SingletonGestorLoja.getInstance(getApplicationContext()).recuperarPasswordAPI(getApplicationContext(), email);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


}