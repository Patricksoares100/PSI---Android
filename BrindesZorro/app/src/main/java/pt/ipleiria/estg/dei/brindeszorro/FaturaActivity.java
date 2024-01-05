package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class FaturaActivity extends AppCompatActivity {

    public static final String IDFATURAS = "IDFATURAS";
    Fatura fatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista_faturas);

        int idFatura = getIntent().getIntExtra(IDFATURAS, 0);
        fatura = SingletonGestorLoja.getInstance(getApplicationContext()).getFatura(idFatura);
    }
}