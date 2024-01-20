package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaAvaliacaosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaAvaliacaosArtigoAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaosListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Favorito;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class DetalhesArtigoActivity extends AppCompatActivity implements AvaliacaosListener {

    public static final String ID_ARTIGO = "IDARTIGO";
    private Artigo artigo;
    private ImageView imgDetalhes;
    private TextView tvNome,tvValorUnitarioArtigo,tvDescricaoDetalhes, tvAvaliacaoArtigoDetalhes, tvValorTotalDetalhes, tvQtdClassificacoes;
    private int quantidade = 1;
    private double totalQuantidade = 0;
    RatingBar ratingBar;
    private ListView lvAvaliacaoDetalheArtigo;

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
        imgDetalhes = findViewById(R.id.ivDetalhes);
        ratingBar = findViewById(R.id.ratingBar);
        tvQtdClassificacoes = findViewById(R.id.tvQtdClassificacoes);
        lvAvaliacaoDetalheArtigo = findViewById(R.id.lvAvaliacaoDetalheArtigo);

        if (artigo != null) {
            carregarArtigo();
        }
        String quantidade = ((TextView) findViewById(R.id.tvQuantidadeArtigoDetalhes)).getText().toString();

       // Toast.makeText(this, "quantidade" + quantidade, Toast.LENGTH_SHORT).show();

    }

    private void carregarArtigo() {
        tvNome.setText(artigo.getNome());
        tvValorUnitarioArtigo.setText(String.format("Preço  %.2f €", artigo.getPreco()));
        //falta avaçiação
        tvDescricaoDetalhes.setText(artigo.getDescricao());
        quantidadeAtualizada();

        String urlImagem = artigo.getImagem();
        Glide.with(this).load(urlImagem).into(imgDetalhes);

        ratingBar.setRating(artigo.getMedia_avaliacoes());
        tvQtdClassificacoes.setText(""+artigo.getNum_avaliacoes()+" Avaliações");

        SingletonGestorLoja.getInstance(getApplicationContext()).setAvaliacaosListener(this);
        SingletonGestorLoja.getInstance(getApplicationContext()).getAllAvaliacoesAPI(getApplicationContext());

    }

    public void diminuirQuantidade(View view) {
        if (!(quantidade > 1 && quantidade <= artigo.getStock_atual())) {
            Toast.makeText(this, R.string.a_quantidade_n_o_pode_ser_inferior_a_1, Toast.LENGTH_LONG).show();;
        } else {
            quantidade--;
            quantidadeAtualizada();
        }
    }

    public void aumentarQuantidade(View view) {
        if (!(quantidade >= 1 && quantidade < artigo.getStock_atual())) {
            Toast.makeText(this, R.string.quantidade_m_xima_de_stock_selecionada, Toast.LENGTH_SHORT).show();
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

    public void adicionarCarrinho(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getApplicationContext()).adicionarCarrinhoAPI(artigo, getApplicationContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"), quantidade );

    }

    public void adicionarFavoritos(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getApplicationContext()).adicionarFavoritoAPI(artigo, getApplicationContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );
        //Intent intent = new Intent(this, AvaliacaosActivity.class);
        //startActivity(intent);


    }

    @Override
    public void onRefreshListaAvaliacaos(ArrayList<Avaliacao> avaliacaos) {
        if(avaliacaos != null){
            ArrayList auxAv = new ArrayList<>();
            for(Avaliacao a : avaliacaos) {
                if(a.getArtigoId() == artigo.getId()){
                    auxAv.add(a);
                }
            }
            lvAvaliacaoDetalheArtigo.setAdapter(new ListaAvaliacaosArtigoAdaptador(getApplicationContext(),auxAv));
        }
    }
}