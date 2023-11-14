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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaFaturasFragment;


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

        System.out.println("--> Olá");

        // Configura o ActionBarDrawerToggle para abrir e fechar a gaveta
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.ndOpen, R.string.ndClose);
        toggle.syncState(); // Sincroniza o estado do ActionBarDrawerToggle
        drawer.addDrawerListener(toggle); // Adiciona o ActionBarDrawerToggle à gaveta

        navigationView.setNavigationItemSelectedListener(this); // Define a atividade atual como o ouvinte de eventos de navegação
        fragmentManager = getSupportFragmentManager();

        // falta aqui carregar o cabeçalho da gaveta (nome e email do user)
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
            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
        } else if (item.getItemId() == R.id.navCarrinho) {
            Intent artigosLoja = new Intent(this, CarrinhoActivity.class);
            startActivity(artigosLoja);
        } else if (item.getItemId() == R.id.navFavoritos) {
            setTitle(item.getTitle());
        } else if (item.getItemId() == R.id.navFaturas) {
            setTitle(item.getTitle());
            fragment = new ListaFaturasFragment();
            setTitle(item.getTitle());
        } else if (item.getItemId() == R.id.navServidor) {
            setTitle(item.getTitle());
            Intent editarServidor = new Intent(this, ServidorActivity.class);
            startActivity(editarServidor);


        } else if (item.getItemId() == R.id.navPessoal) {
            setTitle(item.getTitle());
            Intent editarDadosPessoais = new Intent(this, EditarDadosPessoaisActivity.class);
            startActivity(editarDadosPessoais);

        } else if (item.getItemId() == R.id.navAvaliacoes) {
            setTitle(item.getTitle());
            Intent editarDadosPessoais = new Intent(this, FavoritosActivity.class);
            startActivity(editarDadosPessoais);
        }
        if (fragment != null) fragmentManager.beginTransaction().replace(R.id.contentFragment,fragment).commit();
        drawer.closeDrawer(GravityCompat.START); // Fecha a gaveta após a seleção do item

        return true; // Retorna true para indicar que o evento de seleção foi tratado com sucesso
    }

}