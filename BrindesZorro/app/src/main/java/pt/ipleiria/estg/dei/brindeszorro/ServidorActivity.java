package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ServidorActivity extends AppCompatActivity {
    EditText etIpServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor);
        etIpServidor = findViewById(R.id.etIpServidor);

    }

    public void onClickServidor(View view){
        ipServer();
    }
    public void ipServer(){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String novoIP = etIpServidor.getText().toString().trim();
        Public.SERVER = novoIP;// concatenar aqui 'http://' + novoIP + ':' + porto + 'api'
        editor.putString(Public.SERVER_KEY, novoIP);
        editor.apply();
        //buscar o valor das sharedpreferences
        //entre aspas vem o valor padrao caso nao tenha nada
        String server = sharedPreferences.getString(Public.SERVER_KEY, "http://172.22.21.219:8080/api/");
        System.out.println("--->1 " + server);
    }
}