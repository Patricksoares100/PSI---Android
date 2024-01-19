package pt.ipleiria.estg.dei.brindeszorro;

import static pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity.ID_ARTIGO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class AvaliacaoComentarioActivity extends AppCompatActivity {

    public static final String IDAVALIACAOS = "IDAVALIACAO";
    Avaliacao avaliacao;
    private TextView precoArtigo, descricaoArtigo, artigoNome,totalAvaliacaoTV;
    private Artigo artigo;
    private EditText etComentarioArtigoAvaliacaoComentario;
    private ImageView ivImagem;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_comentario);

        etComentarioArtigoAvaliacaoComentario = findViewById(R.id.etComentarioArtigoAvaliacaoComentario);
        precoArtigo = findViewById(R.id.tvPrecoArtigo);
        descricaoArtigo = findViewById(R.id.tvDesignacaoArtigo);
        artigoNome = findViewById(R.id.tvNomeArtigo);
        totalAvaliacaoTV = findViewById(R.id.tvMediaAva);
        ivImagem = findViewById(R.id.ivArtigoComentario);
        ratingBar = findViewById(R.id.ratingBarTotal);


        carregarDadosArtigo();

    }

    private void carregarDadosArtigo(){

        int idArtigo = getIntent().getIntExtra(ID_ARTIGO, 0);
        artigo = SingletonGestorLoja.getInstance(getApplicationContext()).getArtigo(idArtigo);

        precoArtigo.setText(String.format("%.2f €", artigo.getPreco()));
        descricaoArtigo.setText(artigo.getDescricao());
        artigoNome.setText(artigo.getNome());
        totalAvaliacaoTV.setText("" + artigo.getNum_avaliacoes() + " Avaliações");
        ratingBar.setRating(artigo.getMedia_avaliacoes());
        Glide.with(getApplicationContext())
                .load(artigo.getImagem())
                .placeholder(R.drawable.ipleiria)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImagem);


    }


    public void onClickEnviarComentario(View view) {

        String textoComentario = etComentarioArtigoAvaliacaoComentario.getText().toString();
        RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingBarArtigoAvaliacaoComentario);

        if (textoComentario.isEmpty()){
            Toast.makeText(this, "Deixe um comentário antes de enviar", Toast.LENGTH_SHORT).show();
            return;
        } else {

            // fui procurar no stack
            // https://stackoverflow.com/questions/38417596/how-to-check-if-a-rating-bar-is-has-been-rated-or-not-in-android
            // defini  android:rating="0.0" no xml para ele perceber qual o valor inicial e ver se continua 0.0
            if (ratingbar.getRating() == 0.0) {
                Toast.makeText(this, "Atribua uma pontuação antes de enviar", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }
}