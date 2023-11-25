package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistarClienteActivity extends AppCompatActivity {

    private EditText etNomeRegistar, etPasswordRegistar, etEmailRegistar, etTelefoneRegistar,
            etNifRegistar, etMoradaRegistar, etCodigoPostalRegistar, etLocalidadeRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        etNomeRegistar = findViewById(R.id.etNomeRegistar);
        etPasswordRegistar = findViewById(R.id.etPasswordRegistar);
        etEmailRegistar = findViewById(R.id.etEmailRegistar);
        etTelefoneRegistar = findViewById(R.id.etTelefoneRegistar);
        etNifRegistar = findViewById(R.id.etNifRegistar);
        etMoradaRegistar = findViewById(R.id.etMoradaRegistar);
        etCodigoPostalRegistar = findViewById(R.id.etCodigoPostalRegistar);
        etLocalidadeRegistar = findViewById(R.id.etLocalidadeRegistar);
    }


    public void onClickRegistarCliente(View view) {
        // as validaçoes estao feitas mas o botão nao está a funcionar...
        //ja fiz um novo e nem a toast lê
        Toast.makeText(this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "cliquei", Toast.LENGTH_SHORT).show();
        String nome = etNomeRegistar.getText().toString();
        String email = etEmailRegistar.getText().toString();
        String nif = etNifRegistar.getText().toString();
        String telefone = etTelefoneRegistar.getText().toString();
        String morada = etMoradaRegistar.getText().toString();
        String localidade = etLocalidadeRegistar.getText().toString();
        String codPostal = etCodigoPostalRegistar.getText().toString();

        // validações e as verificações mais abixo
        if (nome.length() <= 4) {
            Toast.makeText(this, "Username inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isEmailValido(email)) {
            etEmailRegistar.setError(getString(R.string.etFormatoInvalido));
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
