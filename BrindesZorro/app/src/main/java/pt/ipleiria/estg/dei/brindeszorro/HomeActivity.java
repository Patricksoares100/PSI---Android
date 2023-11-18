package pt.ipleiria.estg.dei.brindeszorro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolBar); // Configura a barra de ferramentas (toolbar)
        setSupportActionBar(toolbar); // Define a barra de ferramentas como a barra de apoio da atividade
        drawer = findViewById(R.id.drawerLayout); // Obtém a gaveta (drawer)
        navigationView = findViewById(R.id.navView); // Obtém a vista de navegação (navigation view)

    }
}