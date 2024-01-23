package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class ProvaOraIPL extends AppCompatActivity {

    private ArrayList<Artigo> artigos;
    private TextView tvOral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova_ora_ipl);
        tvOral = findViewById(R.id.tvOral);
    }


    public void obterOral(View view) {
        SingletonGestorLoja.getInstance(getApplicationContext()).getAllArtigosOralAPI(getApplicationContext());
        artigos = SingletonGestorLoja.getInstance(getApplicationContext()).getArtigosBD();
        int produtos = artigos.size();
        double valorMaisBaixo = SingletonGestorLoja.getInstance(getApplicationContext()).artigoMaisBaixo(artigos);
        double valBaixo = SingletonGestorLoja.getInstance(getApplicationContext()).getArtigoMaisBaixoOralAPI(getApplicationContext());

        tvOral.setText("Existem " + produtos + " produtos, o mais barato custa "+valorMaisBaixo+ "€");


    }
}