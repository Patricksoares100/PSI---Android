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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

import pt.ipleiria.estg.dei.brindeszorro.bdlocal.LojaBDHelper;
import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaAvaliacaosFragment;
import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaFaturasFragment;
import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaFavoritosFragment;
import pt.ipleiria.estg.dei.brindeszorro.fragment.ListaHomeFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String email;
    private EditText etPassword, etEmail;

    private FragmentManager fragmentManager;
    private Fragment fragment;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    public static final int ADD = 100, EDIT = 200, DELETE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar); // Configura a barra de ferramentas (toolbar)
        setSupportActionBar(toolbar); // Define a barra de ferramentas como a barra de apoio da atividade
        drawer = findViewById(R.id.drawerLayout); // Obtém a gaveta (drawer)
        navigationView = findViewById(R.id.navView); // Obtém a vista de navegação (navigation view)

        navigationView.setNavigationItemSelectedListener(this); // Define a atividade atual como o ouvinte de eventos de navegação
        // Configura o ActionBarDrawerToggle para abrir e fechar a gaveta
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.ndOpen, R.string.ndClose);
        toggle.syncState(); // Sincroniza o estado do ActionBarDrawerToggle
        drawer.addDrawerListener(toggle); // Adiciona o ActionBarDrawerToggle à gaveta
        drawer.closeDrawer(GravityCompat.START); // Adiciona esta linha para fechar a gaveta no início

        fragmentManager = getSupportFragmentManager();

        fragment = new ListaHomeFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();

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

        if (item.getItemId() == R.id.navHome){
            fragment = new ListaHomeFragment();
            setTitle(item.getTitle());

        } else if (item.getItemId() == R.id.navCarrinho) {
            Intent artigosLoja = new Intent(this, CarrinhoActivity.class);
            startActivity(artigosLoja);

        } else if (item.getItemId() == R.id.navFavoritos) {
            fragment = new ListaFavoritosFragment();
            setTitle(item.getTitle());

        } else if (item.getItemId() == R.id.navFaturas) {;
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
            // Alinea 6 Ficha 5 Books - Iniciar o fragment quando se clica nas Avaliacoes da Main
            fragment = new ListaAvaliacaosFragment();
            setTitle(item.getTitle());
            /*Intent intent = new Intent(this, AvaliacaoComentarioActivity.class);
            startActivity(intent);*/
        }

        if (fragment != null) fragmentManager.beginTransaction().replace(R.id.contentFragment,fragment).commit();
        drawer.closeDrawer(GravityCompat.START); // Fecha a gaveta após a seleção do item

        return true; // Retorna true para indicar que o evento de seleção foi tratado com sucesso
    }

    public void logOut(View view){

        // Remover user na bd
        LojaBDHelper lojaBDHelper = new LojaBDHelper(getApplicationContext());
        lojaBDHelper.removerUserBD(1);

        // Redirecionar para activity de login
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        onDestroy();
        finish();
    }

}