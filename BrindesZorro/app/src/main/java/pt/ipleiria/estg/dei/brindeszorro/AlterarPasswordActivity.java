package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.modelo.User;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class AlterarPasswordActivity extends AppCompatActivity {
    private User user;
    private EditText etAtualPassword, etNovaPassword, etConfirmarNovaPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_password);

        etAtualPassword= findViewById(R.id.etAtualPassword);
        etNovaPassword = findViewById(R.id.etNovaPassword);
        etConfirmarNovaPassword = findViewById(R.id.etConfirmarNovaPassword);



    }

    public void isPassNovaValida(View view) {
        String atualPass = etAtualPassword.getText().toString();
        String novaPassword = etNovaPassword.getText().toString();
        String confirmarPassword = etConfirmarNovaPassword.getText().toString();
       if (novaPassword.length() < 8 || confirmarPassword.length() < 8){
            Toast.makeText(this, "Nova password deve conter no minimo 8 catacteres", Toast.LENGTH_SHORT).show();
            return;
        }
       if(atualPass == novaPassword || atualPass == confirmarPassword){
           Toast.makeText(this, "Nova password deve ser diferente da atual", Toast.LENGTH_SHORT).show();
           return;
       }
        if (novaPassword == confirmarPassword){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
            SingletonGestorLoja.getInstance(getApplicationContext()).editPassAPI(getApplicationContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"),atualPass , novaPassword);
        }
    }
}