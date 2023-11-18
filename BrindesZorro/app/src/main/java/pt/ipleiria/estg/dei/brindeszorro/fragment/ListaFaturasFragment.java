package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFaturasAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;


public class ListaFaturasFragment extends Fragment {

    private ListView lvFaturas;
    private ArrayList<Fatura> faturas;
    private SearchView searchView;

    public ListaFaturasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        setHasOptionsMenu(true); // vai chamar o menu
        lvFaturas = view.findViewById(R.id.lvMain); // vai adicionar à lvHome da activity home os fragmentos que queremos mandar
        faturas = SingletonGestorLoja.getInstance().getFaturas();
        lvFaturas.setAdapter(new ListaFaturasAdaptador(getContext(),faturas));
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
            ArrayList<Fatura> listaTempFaturas = new ArrayList<>(); // vamos criar uma array list temporaria
            for (Fatura f : SingletonGestorLoja.getInstance().getFaturas())
                if (f.getEstado().toLowerCase().contains(newText.toLowerCase())) {
                    //vai comparar a nova letra com as que existem no array. Se conter vai adicionar ao array
                    //para comparar com a proxima letra
                    listaTempFaturas.add(f);
                }

            lvFaturas.setAdapter(new ListaFaturasAdaptador(getContext(), listaTempFaturas));
            return true;
        }
    });
    super.onCreateOptionsMenu(menu, inflater);
}

}