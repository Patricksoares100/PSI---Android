package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AvaliacaoComentarioActivity extends AppCompatActivity {
private EditText etComentarioArtigoAvaliacaoComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_comentario);

        etComentarioArtigoAvaliacaoComentario = findViewById(R.id.etComentarioArtigoAvaliacaoComentario);
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
        if (ratingbar.getRating() == 0.0) {
            Toast.makeText(this, "Atribua uma pontuação antes de enviar", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}