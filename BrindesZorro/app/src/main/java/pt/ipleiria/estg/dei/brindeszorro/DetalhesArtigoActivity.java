package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class DetalhesArtigoActivity extends AppCompatActivity {

    public static final String ID_ARTIGO = "IDARTIGO";
    Artigo artigo;
    private ImageView imgView;
    private TextView tvNome,tvValorUnitarioArtigo,tvDescricaoDetalhes, tvAvaliacaoArtigoDetalhes, tvValorTotalDetalhes;
    private int quantidade = 1;
    private double totalQuantidade = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_artigo);

        int idArtigo = getIntent().getIntExtra(ID_ARTIGO, 0); // se nao passar nada, começa em 0
        artigo = SingletonGestorLoja.getInstance(getApplicationContext()).getArtigo(idArtigo);

        tvNome = findViewById(R.id.tvNomeArtigoDetalhes);
        tvValorUnitarioArtigo = findViewById(R.id.tvValorUnitarioArtigo);
        tvAvaliacaoArtigoDetalhes = findViewById(R.id.tvDescricaoDetalhes);
        tvDescricaoDetalhes = findViewById(R.id.tvDescricaoDetalhes);
        tvValorTotalDetalhes = findViewById(R.id.tvValorTotalDetalhes);
        imgView = findViewById(R.id.ivDetalhes);

        if (artigo != null) {
            carregarArtigo();
        }
        String quantidade = ((TextView) findViewById(R.id.tvQuantidadeArtigoDetalhes)).getText().toString();

        Toast.makeText(this, "quantidade" + quantidade, Toast.LENGTH_SHORT).show();
    }

    private void carregarArtigo() {
        tvNome.setText(artigo.getNome());
        tvValorUnitarioArtigo.setText(String.format("Preço  %.2f €", artigo.getPreco()));
        //falta avaçiação
        tvDescricaoDetalhes.setText(artigo.getDescricao());
        quantidadeAtualizada();
    }

    public void diminuirQuantidade(View view) {
        if (!(quantidade > 1 && quantidade <= artigo.getStock_atual())) {
            Toast.makeText(this, "A quantidade não pode ser inferior a 1.", Toast.LENGTH_LONG).show();;
        } else {
            quantidade--;
            quantidadeAtualizada();
        }
    }

    public void aumentarQuantidade(View view) {
        if (!(quantidade >= 1 && quantidade < artigo.getStock_atual())) {
            Toast.makeText(this, "Quantidade máxima de stock selecionada.", Toast.LENGTH_SHORT).show();
        } else {
            quantidade++;
            quantidadeAtualizada();
        }
    }

    public void quantidadeAtualizada() {
        TextView tvQuantidadeArtigoDetalhes = findViewById(R.id.tvQuantidadeArtigoDetalhes);
        tvQuantidadeArtigoDetalhes.setText(String.valueOf(quantidade));

        totalQuantidade = quantidade * artigo.getPreco();

        TextView tvValorTotalDetalhes = findViewById(R.id.tvValorTotalDetalhes);
        tvValorTotalDetalhes.setText(String.format("Valor Total   %.2f €", totalQuantidade));
    }
}