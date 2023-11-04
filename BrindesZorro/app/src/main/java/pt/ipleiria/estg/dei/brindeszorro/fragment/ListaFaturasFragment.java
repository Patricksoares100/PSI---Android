package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFaturasAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;


public class ListaFaturasFragment extends Fragment {

    private ListView lvFaturas;
    private ArrayList<Fatura> faturas;
    public ListaFaturasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_faturas, container, false);

        lvFaturas = view.findViewById(R.id.lvFaturas);
        faturas = SingletonGestorLoja.getInstance().getFaturas();
        lvFaturas.setAdapter(new ListaFaturasAdaptador(getContext(), faturas));
        return view;
    }
}