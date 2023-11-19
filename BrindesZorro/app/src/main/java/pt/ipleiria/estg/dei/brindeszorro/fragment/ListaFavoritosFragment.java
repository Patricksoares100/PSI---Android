package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaArtigosAdaptador;
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

        lvFavoritos = view.findViewById(R.id.lvFavoritos);
        //favoritos = SingletonGestorLoja.getInstance().getFavoritos();

        // DEFINIR ADAPTADOR
        //lvFavoritos.setAdapter(new ListaArtigosAdaptador(getContext(),favoritos));
         return view;
    }
}