package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class AvaliacaoComentarioActivity extends AppCompatActivity {

    public static final String IDAVALIACAOS = "IDAVALIACAO";
    Avaliacao avaliacao;
    private EditText etComentarioArtigoAvaliacaoComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_comentario);

        int idAvaliacao = getIntent().getIntExtra(IDAVALIACAOS, 0);
        avaliacao = SingletonGestorLoja.getInstance(getApplicationContext()).getAvaliacao(idAvaliacao);

        etComentarioArtigoAvaliacaoComentario = findViewById(R.id.etComentarioArtigoAvaliacaoComentario);
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