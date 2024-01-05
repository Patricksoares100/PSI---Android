package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Signup;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class RegistarClienteActivity extends AppCompatActivity {

    private EditText etUsername, etPasswordRegistar, etEmailRegistar, etNome ,etTelefoneRegistar,
            etNifRegistar, etMoradaRegistar, etCodigoPostalRegistar, etLocalidadeRegistar;
    Signup signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        etUsername = findViewById(R.id.etUsername);
        etPasswordRegistar = findViewById(R.id.etPasswordRegistar);
        etEmailRegistar = findViewById(R.id.etEmailRegistar);
        etNome = findViewById(R.id.etNome);
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
        String password = etPasswordRegistar.getText().toString();
        String email = etEmailRegistar.getText().toString();
        String nome = etNome.getText().toString();
        String nif = etNifRegistar.getText().toString();
        String telefone = etTelefoneRegistar.getText().toString();
        String morada = etMoradaRegistar.getText().toString();
        String localidade = etLocalidadeRegistar.getText().toString();
        String codPostal = etCodigoPostalRegistar.getText().toString();

        // validações e as verificações mais abixo
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() ||
                nome.isEmpty() || nif.isEmpty() || telefone.isEmpty() ||
                morada.isEmpty() || localidade.isEmpty() || codPostal.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.length() <= 4) {
            Toast.makeText(this, "Username inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isEmailValido(email)) {
            etEmailRegistar.setError(getString(R.string.etFormatoInvalido));
            Toast.makeText(this, "Email em formato inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nome.length() <= 2) {
            Toast.makeText(this, "Nome inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 8) {
            Toast.makeText(this, "Password deve conter min 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!nif.matches("[0-9]{9}")) {
            Toast.makeText(this, "Nif deverá conter 9 digitos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!telefone.matches("[0-9]{9}")) {
            Toast.makeText(this, "Telefone deverá conter 9 digitos", Toast.LENGTH_SHORT).show();
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
        signup = new Signup(0,username,email,password,nome,
                morada,codPostal,localidade,Integer.parseInt(telefone),
                Integer.parseInt(nif));
        SingletonGestorLoja.getInstance(getApplicationContext()).signupAPI(signup, getApplicationContext());

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
