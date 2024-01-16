package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;

public class CarrinhoActivity extends AppCompatActivity {
    private int quantidade = 1;
    private double totalQuantidade = 0;
    private Artigo artigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista_carrinho);

    }

    public void onClickConfirmarCompra(View view){
        Intent intent = new Intent(this, ConfirmarActivity.class);
        startActivity(intent);
        finish();
    }

}