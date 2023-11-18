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
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;


public class ListaArtigosFragment extends Fragment {

    private ListView lvArtigos;
    private ArrayList<Artigo> artigos;
    public ListaArtigosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home, container, false);

        lvArtigos = view.findViewById(R.id.lvHome);
        artigos = SingletonGestorLoja.getInstance().getArtigos();

        System.out.println("--->" + artigos);
        // DEFINIR ADAPTADOR
        lvArtigos.setAdapter(new ListaArtigosAdaptador(getContext(),artigos));
        return view;
    }
}