package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class DetalhesArtigoActivity extends AppCompatActivity {

    public static final String ID_ARTIGO = "IDARTIGO";
    Artigo artigo;
    private ImageView imgView;
    private TextView tvNome,tvValorUnitarioArtigo,tvDescricaoDetalhes, tvAvaliacaoArtigoDetalhes, tvValorTotalDetalhes;
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
        tvValorTotalDetalhes = findViewById(R.id.tvDescricaoDetalhes);
        imgView = findViewById(R.id.ivDetalhes);

        if (artigo != null) {
            carregarArtigo();
        }
    }

    private void carregarArtigo() {
        tvNome.setText(artigo.getNome());
        tvValorUnitarioArtigo.setText(String.valueOf(artigo.getPreco()));
        //falta avaçiação
        tvDescricaoDetalhes.setText(artigo.getDescricao());
        tvValorTotalDetalhes.setText(artigo.getDescricao());

    }
}