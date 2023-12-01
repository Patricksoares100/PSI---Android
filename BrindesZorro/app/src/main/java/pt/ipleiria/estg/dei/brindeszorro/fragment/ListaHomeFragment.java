package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import pt.ipleiria.estg.dei.brindeszorro.R;

public class ListaHomeFragment extends Fragment {

    private ListView lvHomeArtigos;
    public ListaHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lista_home, container, false);

        lvHomeArtigos = view.findViewById(R.id.lvHomeArtigos);
        //favoritos = SingletonGestorLoja.getInstance().getFavoritos();

        // DEFINIR ADAPTADOR
        //lvFavoritos.setAdapter(new ListaArtigosAdaptador(getContext(),favoritos));
        return view;
    }
}