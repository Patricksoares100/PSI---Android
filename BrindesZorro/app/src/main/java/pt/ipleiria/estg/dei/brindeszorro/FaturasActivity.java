package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;


public class FaturasActivity extends AppCompatActivity {


    private TextView tvTextDataFatura,tvTextValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faturas);

    }
}