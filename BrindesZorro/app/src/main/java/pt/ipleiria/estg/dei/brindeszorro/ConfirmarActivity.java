package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmarActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone ,editTextNif, editTextMorada,editTextTextPostalAddress,editTextLocalidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        editTextName = findViewById(R.id.editTextName);
        editTextNif = findViewById(R.id.editTextNif);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMorada = findViewById(R.id.editTextMorada);
        editTextLocalidade = findViewById(R.id.editTextLocalidade);
        editTextTextPostalAddress = findViewById(R.id.editTextTextPostalAddress);
    }

    public void onClickConfirmarPagamento(View view) {

        String nome = editTextName.getText().toString();
        String nif = editTextNif.getText().toString();
        String telefone = editTextPhone.getText().toString();
        String morada = editTextMorada.getText().toString();
        String localidade = editTextLocalidade.getText().toString();
        String codPostal = editTextTextPostalAddress.getText().toString();

        // validações e as verificações mais abixo
        if(nome.length() <= 4){
            Toast.makeText(this, "Username inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!nif.matches("[0-9]{9}")){
            Toast.makeText(this, "Nif deverá conter 9 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!telefone.matches("[0-9]{9}")){
            Toast.makeText(this, "Telemovel deverá conter 9 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        if(morada.length() == 0) {
            Toast.makeText(this, "Morada inválida", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!codPostal.matches("[0-9]{4}-[0-9]{3}")){
            Toast.makeText(this, "Formato de código postal inválido (____-___)", Toast.LENGTH_SHORT).show();
            return;
        }
        if(localidade.length() == 0) {
            Toast.makeText(this, "Localidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    public boolean isEmailValido(String email) {
        if (email == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
