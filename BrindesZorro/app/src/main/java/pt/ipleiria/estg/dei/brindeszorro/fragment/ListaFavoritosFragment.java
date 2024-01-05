package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaArtigosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFavoritosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Favorito;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class ListaFavoritosFragment extends Fragment {

    private ListView lvFavoritos;
    private ArrayList<Favorito> favoritos;

    public ListaFavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_favoritos, container, false);
        setHasOptionsMenu(true);
        lvFavoritos = view.findViewById(R.id.lvFavoritos); //vai adicionar à lvFavoritos os fragmentos que queremos mostrar
        favoritos = SingletonGestorLoja.getInstance(getContext()).getFavoritosBD();
        lvFavoritos.setAdapter(new ListaFavoritosAdaptador(getContext(), favoritos));

        lvFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), DetalhesArtigoActivity.class);
                intent.putExtra(DetalhesArtigoActivity.ID_ARTIGO,(int) position);
                startActivity(intent);

            }
        });
        return view;
    }


}