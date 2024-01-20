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
            Toast.makeText(this, R.string.preencha_todos_os_campos, Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.length() <= 4) {
            Toast.makeText(this, R.string.username_inv_lido, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isEmailValido(email)) {
            etEmailRegistar.setError(getString(R.string.etFormatoInvalido));
            Toast.makeText(this, R.string.email_em_formato_inv_lido, Toast.LENGTH_SHORT).show();
            return;
        }
        if (nome.length() <= 2) {
            Toast.makeText(this, R.string.nome_inv_lido, Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 8) {
            Toast.makeText(this, R.string.password_deve_conter_min_8_caracteres, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!nif.matches("[0-9]{9}")) {
            Toast.makeText(this, R.string.nif_dever_conter_9_digitos, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!telefone.matches("[0-9]{9}")) {
            Toast.makeText(this, R.string.telefone_dever_conter_9_digitos, Toast.LENGTH_SHORT).show();
            return;
        }

        if (morada.length() == 0) {
            Toast.makeText(this, R.string.morada_inv_lida, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!codPostal.matches("[0-9]{4}-[0-9]{3}")) {
            Toast.makeText(this,  R.string.formato_de_c_digo_postal_inv_lido, Toast.LENGTH_SHORT).show();
            return;
        }

        if (localidade.length() == 0) {
            Toast.makeText(this,  R.string.localidade_inv_lida, Toast.LENGTH_SHORT).show();
            return;
        }
        signup = new Signup(0,username,email,password,nome,
                morada,codPostal,localidade,Integer.parseInt(telefone),
                Integer.parseInt(nif));
        SingletonGestorLoja.getInstance(getApplicationContext()).signupAPI(signup, getApplicationContext());
//if registo com sucesso itent else continua na mesma activity
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
