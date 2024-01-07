package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaArtigosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigosListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class ListaHomeFragment extends Fragment implements ArtigosListener {

    private ListView lvArtigos;
    private ImageView imgView;
    private ArrayList<Artigo> artigos;
    private SearchView searchView;
    public ListaHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getContext(), "entrou", Toast.LENGTH_SHORT).show();// mensagem de erro para ver se entra!!!!

        // inicia o layout com o activity_home
        View view = inflater.inflate(R.layout.fragment_lista_home, container, false);
        setHasOptionsMenu(true);
        lvArtigos = view.findViewById(R.id.lvHomeArtigos);
        //vai buscar a lista de livros ao singleton
        artigos = SingletonGestorLoja.getInstance(getContext()).getArtigosBD();

        // DEFINIR ADAPTADOR
        lvArtigos.setAdapter(new ListaArtigosAdaptador(getContext(),artigos));

        //temos que utilizar um listener para saber qual o item que foi selecionado
        lvArtigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // inserir uma toast para ver o nome do artigo clicado
                Toast.makeText(getContext(),artigos.get(position).getNome(), Toast.LENGTH_SHORT).show();

                //agora vamos iniciar uma activity assim como passar o ID do artigo
                Intent intent = new Intent(getContext(), DetalhesArtigoActivity.class);
                intent.putExtra(DetalhesArtigoActivity.ID_ARTIGO,(int) id);
                startActivity(intent);
            }
        });

        SingletonGestorLoja.getInstance(getContext()).setArtigosListener(this);
        SingletonGestorLoja.getInstance(getContext()).getAllArtigosAPI(getContext());
        SingletonGestorLoja.getInstance(getContext()).getAllUserAPI(getContext());

       return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa,menu);
        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        searchView = (SearchView) itemPesquisa.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Artigo> artigoArrayList = new ArrayList<>();
                for (Artigo art: SingletonGestorLoja.getInstance(getContext()).getArtigosBD())
                    if (art.getNome().toLowerCase().contains(newText.toLowerCase())) {
                    //vai comparar a nova letra com as que existem no array. Se conter vai adicionar ao array
                    //para comparar com a proxima letra
                    artigoArrayList.add(art);
                }
                lvArtigos.setAdapter(new ListaArtigosAdaptador(getContext(),artigoArrayList));
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onRefreshListaArtigos(ArrayList<Artigo> artigos) {
        if(artigos != null){
            lvArtigos.setAdapter(new ListaArtigosAdaptador(getContext(), artigos));
        }
    }
}