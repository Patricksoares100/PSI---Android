package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFavoritosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FavoritosListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Favorito;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFavoritosFragment extends Fragment implements FavoritosListener {

    private ListView lvFavoritos;
    private ArrayList<Favorito> favoritos;
    private Button buttonLimparFavoritos, buttonAdicionarTodosCarrinho;

    public ListaFavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_favoritos, container, false);
        setHasOptionsMenu(true);
        lvFavoritos = view.findViewById(R.id.lvFavoritos); //vai adicionar à lvFavoritos os fragmentos que queremos mostrar
        favoritos = SingletonGestorLoja.getInstance(getContext()).getFavoritosBD();
        buttonLimparFavoritos = (Button) view.findViewById(R.id.buttonLimparFavoritos);
        buttonAdicionarTodosCarrinho = (Button) view.findViewById(R.id.buttonAdicionarTodosCarrinho);

        lvFavoritos.setAdapter(new ListaFavoritosAdaptador(getContext(), favoritos));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getContext()).setFavoritosListener(this);
        SingletonGestorLoja.getInstance(getContext()).getAllFavoritosAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );


        lvFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), DetalhesArtigoActivity.class);
                intent.putExtra(DetalhesArtigoActivity.ID_ARTIGO,(int) position);
                startActivity(intent);
            }
        });

        buttonAdicionarTodosCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPassarFavoritosCarrinho(v);
            }
        });
        buttonLimparFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLimparFavoritos(v);
            }
        });
        if(favoritos.isEmpty()){
            // buttonLimparCarrinho.setEnabled(false);
            buttonAdicionarTodosCarrinho.setAlpha(0.5f);
            // buttonConcluirCompra.setEnabled(false);
            buttonLimparFavoritos.setAlpha(0.5f);
        }
        return view;
    }

    @Override
    public void onRefreshListaFavoritos(ArrayList<Favorito> favoritos) {
        if(favoritos != null){
            lvFavoritos.setAdapter(new ListaFavoritosAdaptador(getContext(), favoritos));
        }
    }

    public void onClickLimparFavoritos(View view){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getContext()).removerAllFavoritosAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));
    }

    public void onClickPassarFavoritosCarrinho(View view){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getContext()).adicionarFavoritosCarrinhoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));
    }
}