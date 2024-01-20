package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class AlterarPasswordActivity extends AppCompatActivity {
    Button buttonConfirmarPassword;
    private EditText etAtualPassword, etNovaPassword, etConfirmarNovaPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_password);

        etAtualPassword= findViewById(R.id.etAtualPassword);
        etNovaPassword = findViewById(R.id.etNovaPassword);
        etConfirmarNovaPassword = findViewById(R.id.etConfirmarNovaPassword);
        buttonConfirmarPassword = findViewById(R.id.buttonConfirmarAlterarPassword);
    }

    public void isPassNovaValida(View view) {
        String atualPass = etAtualPassword.getText().toString();
        String novaPassword = etNovaPassword.getText().toString();
        String confirmarPassword = etConfirmarNovaPassword.getText().toString();
        if(atualPass.isEmpty() || novaPassword.isEmpty() || confirmarPassword.isEmpty()){
            Toast.makeText(this, R.string.preencha_todos_os_campos, Toast.LENGTH_SHORT).show();
            return;
        }
        if(atualPass.length() <8 ){
            Toast.makeText(this, R.string.password_atual_deve_conter_no_minimo_8_caracteres, Toast.LENGTH_SHORT).show();
            return;
        }
       if (novaPassword.length() < 8 || confirmarPassword.length() < 8){
            Toast.makeText(this, R.string.nova_password_deve_conter_no_minimo_8_caracteres, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!novaPassword.equals(confirmarPassword)) {
            Toast.makeText(this, R.string.as_password_n_o_coincidem, Toast.LENGTH_SHORT).show();
            return;
        }

        if(atualPass.toString().matches(novaPassword.toString()) || atualPass.toString().matches(confirmarPassword.toString())){
           Toast.makeText(this, R.string.nova_password_deve_ser_diferente_da_atual, Toast.LENGTH_SHORT).show();
           return;
       }
        if (novaPassword.toString().matches(confirmarPassword.toString())){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
            SingletonGestorLoja.getInstance(getApplicationContext()).editPassAPI(getApplicationContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"),atualPass , novaPassword);
        }
    }
}