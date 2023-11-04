package pt.ipleiria.estg.dei.brindeszorro;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String email;
    private EditText etPassword, etEmail;

    private FragmentManager fragmentManager;

    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar); // Configura a barra de ferramentas (toolbar)
        setSupportActionBar(toolbar); // Define a barra de ferramentas como a barra de apoio da atividade
        drawer = findViewById(R.id.drawerLayout); // Obtém a gaveta (drawer)
        navigationView = findViewById(R.id.navView); // Obtém a vista de navegação (navigation view)


        // Configura o ActionBarDrawerToggle para abrir e fechar a gaveta
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.ndOpen, R.string.ndClose);
        toggle.syncState(); // Sincroniza o estado do ActionBarDrawerToggle
        drawer.addDrawerListener(toggle); // Adiciona o ActionBarDrawerToggle à gaveta

        navigationView.setNavigationItemSelectedListener(this); // Define a atividade atual como o ouvinte de eventos de navegação
        fragmentManager = getSupportFragmentManager();

        carregarFragmentoInicial();


    }

    private boolean carregarFragmentoInicial(){

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.getItem(0);
        item.setChecked(true);
        return onNavigationItemSelected(item);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Este método é chamado quando um item de menu da vista de navegação é selecionado
        Fragment fragment = null;
        if (item.getItemId()==R.id.navHome){
            System.out.println("-->Nav Estatico"); // Imprime no console quando "Nav Estatico" é selecionado
            setTitle(item.getTitle());
        } else if (item.getItemId() == R.id.navCarrinho) {
            System.out.println("-->Nav Dinamico"); // Imprime no console quando "Nav Dinamico" é selecionado
            //fragment = new DinamicoFragment();
            setTitle(item.getTitle());
        }

        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.contentFragment,
                    fragment).commit();
        drawer.closeDrawer(GravityCompat.START); // Fecha a gaveta após a seleção do item

        return true; // Retorna true para indicar que o evento de seleção foi tratado com sucesso
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}