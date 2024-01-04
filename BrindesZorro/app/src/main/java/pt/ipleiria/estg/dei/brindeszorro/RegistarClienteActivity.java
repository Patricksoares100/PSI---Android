package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistarClienteActivity extends AppCompatActivity {

    private EditText etUsername, etPasswordRegistar, etEmailRegistar, etTelefoneRegistar,
            etNifRegistar, etMoradaRegistar, etCodigoPostalRegistar, etLocalidadeRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        etUsername = findViewById(R.id.etUsername);
        etPasswordRegistar = findViewById(R.id.etPasswordRegistar);
        etEmailRegistar = findViewById(R.id.etEmailRegistar);
        etTelefoneRegistar = findViewById(R.id.etTelefoneRegistar);
        etNifRegistar = findViewById(R.id.etNifRegistar);
        etMoradaRegistar = findViewById(R.id.etMoradaRegistar);
        etCodigoPostalRegistar = findViewById(R.id.etCodigoPostalRegistar);
        etLocalidadeRegistar = findViewById(R.id.etLocalidadeRegistar);
    }


    public void onClickRegistarCliente(View view) {
        // esta a funcionar, tens é que preencher os campos
        //senao retorna o erro
        //no fim agora tem q se fazer pedido de POST na API
        //em vez de fazer para o user, deviamos fazer para o frontend/signup e
        //reaproveitar os metodos
        //Toast.makeText(this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "cliquei", Toast.LENGTH_SHORT).show();
        String username = etUsername.getText().toString();
        String email = etEmailRegistar.getText().toString();
        String nif = etNifRegistar.getText().toString();
        String telefone = etTelefoneRegistar.getText().toString();
        String morada = etMoradaRegistar.getText().toString();
        String localidade = etLocalidadeRegistar.getText().toString();
        String codPostal = etCodigoPostalRegistar.getText().toString();

        // validações e as verificações mais abixo
        if (username.length() <= 4) {
            Toast.makeText(this, "Username inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isEmailValido(email)) {
            etEmailRegistar.setError(getString(R.string.etFormatoInvalido));
            Toast.makeText(this, "Email em formato inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!nif.matches("[0-9]{9}")) {
            Toast.makeText(this, "Nif deverá conter 9 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!telefone.matches("[0-9]{9}")) {
            Toast.makeText(this, "Telemovel deverá conter 9 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (morada.length() == 0) {
            Toast.makeText(this, "Morada inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!codPostal.matches("[0-9]{4}-[0-9]{3}")) {
            Toast.makeText(this, "Formato de código postal inválido (____-___)", Toast.LENGTH_SHORT).show();
            return;
        }

        if (localidade.length() == 0) {
            Toast.makeText(this, "Localidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Registo Efetuado com Sucesso!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //função de validação de login usada na aula
    public boolean isEmailValido(String email) {
        if (email == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
