package pt.ipleiria.estg.dei.brindeszorro;

import static pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity.ID_ARTIGO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import pt.ipleiria.estg.dei.brindeszorro.modelo.User;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class AvaliacaoComentarioActivity extends AppCompatActivity {

    public static final String IDAVALIACAOS = "IDAVALIACAO";
    private TextView precoArtigo, descricaoArtigo, artigoNome,totalAvaliacaoTV;
    private Artigo artigo;
    private EditText etComentarioArtigoAvaliacaoComentario, editTextTextMultiLineComentario;
    private ImageView ivImagem;
    private RatingBar ratingBar, ratingBarArtigoAvaliacaoComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_comentario);

        etComentarioArtigoAvaliacaoComentario = findViewById(R.id.editTextTextMultiLineComentario);
        precoArtigo = findViewById(R.id.tvPrecoArtigo);
        descricaoArtigo = findViewById(R.id.tvDesignacaoArtigo);
        artigoNome = findViewById(R.id.tvNomeArtigo);
        totalAvaliacaoTV = findViewById(R.id.tvMediaAva);
        ivImagem = findViewById(R.id.ivArtigoComentario);
        ratingBar = findViewById(R.id.ratingBarTotal);
        editTextTextMultiLineComentario = findViewById(R.id.editTextTextMultiLineComentario);
        ratingBarArtigoAvaliacaoComentario = findViewById(R.id.ratingBarArtigoAvaliacaoComentario);


        carregarDadosArtigo();

    }

    private void carregarDadosArtigo(){

        int idAvaliacao = getIntent().getIntExtra("IDAVALIACAO", 0);
        Avaliacao avaliacao = SingletonGestorLoja.getInstance(getApplicationContext()).getAvaliacao(idAvaliacao);
        artigo = SingletonGestorLoja.getInstance(getApplicationContext()).getArtigo(avaliacao.getArtigoId());
        //Avaliacao avaliacao = SingletonGestorLoja.getInstance(getApplicationContext()).getAvaliacao();

        precoArtigo.setText(String.format("%.2f €", artigo.getPreco()));
        descricaoArtigo.setText(artigo.getDescricao());
        artigoNome.setText(artigo.getNome());
        totalAvaliacaoTV.setText("" + artigo.getNum_avaliacoes() + " Avaliações");
        ratingBar.setRating(artigo.getMedia_avaliacoes());
        editTextTextMultiLineComentario.setText(avaliacao.getComentario());
        ratingBarArtigoAvaliacaoComentario.setRating(avaliacao.getClassificacao());
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
        }

        // fui procurar no stack
        // https://stackoverflow.com/questions/38417596/how-to-check-if-a-rating-bar-is-has-been-rated-or-not-in-android
        // defini  android:rating="0.0" no xml para ele perceber qual o valor inicial e ver se continua 0.0
        //alterei para 5.0 no xml para assim estar logo a 5 e caso o user queira ele baixa
        if (ratingbar.getRating() == 0.0) {
            Toast.makeText(this, "Atribua uma pontuação antes de enviar", Toast.LENGTH_SHORT).show();
            return;
        }
        if(textoComentario.length() >= 4 ){

            User user = SingletonGestorLoja.getInstance(getApplicationContext()).getUserBD();

            Avaliacao avaliacao = new Avaliacao(0,textoComentario,(int)ratingbar.getRating(), artigo.getId(),user.getId());
            SingletonGestorLoja.getInstance(getApplicationContext()).adicionarAvaliacaoAPI(avaliacao,getApplicationContext());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }


    }
}