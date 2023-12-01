package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaHomeFragment;

public class EditarDadosPessoaisActivity extends AppCompatActivity {

    private EditText etNomeEditarDadosPessoais, etTelefoneEditarDadosPessoais,
            etEmailEditarDadosPessoais,etNifEditarDadosPessoais,etMoradaEditarDadosPessoais,
            etCodigoPostalEditarDadosPessoais,etLocalidadeEditarDadosPessoais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_pessoais);

        etNomeEditarDadosPessoais = findViewById(R.id.etNomeEditarDadosPessoais);
        etEmailEditarDadosPessoais = findViewById(R.id.etEmailEditarDadosPessoais);
        etTelefoneEditarDadosPessoais = findViewById(R.id.etTelefoneEditarDadosPessoais);
        etNifEditarDadosPessoais = findViewById(R.id.etNifEditarDadosPessoais);
        etMoradaEditarDadosPessoais = findViewById(R.id.etMoradaEditarDadosPessoais);
        etCodigoPostalEditarDadosPessoais = findViewById(R.id.etCodigoPostalEditarDadosPessoais);
        etLocalidadeEditarDadosPessoais = findViewById(R.id.etLocalidadeEditarDadosPessoais);
    }



    public void Canceler(View view) {
        Intent NavHome = new Intent(this, MainActivity.class);
        startActivity(NavHome);
    }

    public void onClickConfirmarEditarDadosPessoais(View view) {

        String nome = etNomeEditarDadosPessoais.getText().toString();
        String email = etEmailEditarDadosPessoais.getText().toString();
        String nif = etNifEditarDadosPessoais.getText().toString();
        String telefone = etTelefoneEditarDadosPessoais.getText().toString();
        String morada = etMoradaEditarDadosPessoais.getText().toString();
        String localidade = etLocalidadeEditarDadosPessoais.getText().toString();
        String codPostal = etCodigoPostalEditarDadosPessoais.getText().toString();

       // validações e as verificações mais abixo
        if(nome.length() <= 4){
            Toast.makeText(this, "Username inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isEmailValido(email)){
            etEmailEditarDadosPessoais.setError(getString(R.string.etFormatoInvalido));
            return;
        }
        //vi esta validação neste site
        https://copyprogramming.com/howto/pattern-validation-for-mobile-number-in-android
        // o 0 -9 indica que aceita valores no intervalo de 0 a 9 nos {9} numeros seguintes
        if(!nif.matches("[0-9]{9}")){
            Toast.makeText(this, "Nif deverá conter 9 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!telefone.matches("[0-9]{9}")){
            Toast.makeText(this, "Telemovel deverá conter 9 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if(morada.length() == 0) {
            Toast.makeText(this, "Morada inválida", Toast.LENGTH_SHORT).show();
            return;
        }


        // aqui a mesma questão separado por um -
        if(!codPostal.matches("[0-9]{4}-[0-9]{3}")){
            Toast.makeText(this, "Formato de código postal inválido (____-___)", Toast.LENGTH_SHORT).show();
            return;
        }

        if(localidade.length() == 0) {
            Toast.makeText(this, "Localidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(this, ListaHomeFragment.class);
        // deve ser para colocar aqui a função de dar um save
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