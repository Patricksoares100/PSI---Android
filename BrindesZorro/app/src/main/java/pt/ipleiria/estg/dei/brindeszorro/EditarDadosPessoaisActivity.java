package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.bdlocal.LojaBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaHomeFragment;
import pt.ipleiria.estg.dei.brindeszorro.listeners.UsersListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.modelo.User;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class EditarDadosPessoaisActivity extends AppCompatActivity implements UsersListener {

    private User user;
    public static final String TOKEN = "TOKEN";
    private EditText etNomeEditarDadosPessoais, etTelefoneEditarDadosPessoais,
            etNifEditarDadosPessoais,etMoradaEditarDadosPessoais,
            etCodigoPostalEditarDadosPessoais,etLocalidadeEditarDadosPessoais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_pessoais);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getApplicationContext()).setUsersListener(this);
        SingletonGestorLoja.getInstance(getApplicationContext()).getUserDataAPI(getApplicationContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );

        etNomeEditarDadosPessoais = findViewById(R.id.etNomeEditarDadosPessoais);
        etTelefoneEditarDadosPessoais = findViewById(R.id.etTelefoneEditarDadosPessoais);
        etNifEditarDadosPessoais = findViewById(R.id.etNifEditarDadosPessoais);
        etMoradaEditarDadosPessoais = findViewById(R.id.etMoradaEditarDadosPessoais);
        etCodigoPostalEditarDadosPessoais = findViewById(R.id.etCodigoPostalEditarDadosPessoais);
        etLocalidadeEditarDadosPessoais = findViewById(R.id.etLocalidadeEditarDadosPessoais);

        carregarDadosPessoais();

    }

    private void carregarDadosPessoais(){

        user = SingletonGestorLoja.getInstance(getApplicationContext()).getUserBD();

        etNomeEditarDadosPessoais.setText(user.getNome());
        etTelefoneEditarDadosPessoais.setText("" +user.getTelefone());
        etNifEditarDadosPessoais.setText(""+user.getNif());
        etMoradaEditarDadosPessoais.setText(user.getMorada());
        etCodigoPostalEditarDadosPessoais.setText(user.getCodigo_postal());
        etLocalidadeEditarDadosPessoais.setText(user.getLocalidade());

    }

    public void Cancelar(View view) {
        Intent NavHome = new Intent(this, MainActivity.class);
        startActivity(NavHome);
    }

    public void onClickConfirmarEditarDadosPessoais(View view) {

        user = SingletonGestorLoja.getInstance(getApplicationContext()).getUserBD();
        System.out.println("---> sout do user" + user);

        String nome = etNomeEditarDadosPessoais.getText().toString();
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

        System.out.println( "---> antes" + user.getNome());
        user.setNome(nome);
        user.setNif(Integer.parseInt(nif));
        user.setTelefone(Integer.parseInt(telefone));
        user.setMorada(morada);
        user.setLocalidade(localidade);
        user.setCodigo_postal(codPostal);
        System.out.println( "---> depois" + user.getNome());


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        System.out.println("---> sout do user" + user);
        SingletonGestorLoja.getInstance(getApplicationContext()).setUsersListener(this);
        SingletonGestorLoja.getInstance(getApplicationContext()).editUserAPI(user,getApplicationContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void alterarPassword (View view){
        Intent intent = new Intent(this, AlterarPasswordActivity.class);
        startActivity(intent);
    }


    @Override
    public void onRefreshListaUsers(ArrayList<User> users) {
        if (users != null){
            Toast.makeText(this, "Faça login ", Toast.LENGTH_SHORT).show();
        }
    }
}