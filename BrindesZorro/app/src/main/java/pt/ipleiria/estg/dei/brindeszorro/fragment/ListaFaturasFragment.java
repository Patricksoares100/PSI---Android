package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.AvaliacaoComentarioActivity;
import pt.ipleiria.estg.dei.brindeszorro.FaturaActivity;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFaturasAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FaturasListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;


public class ListaFaturasFragment extends Fragment implements FaturasListener {

    private ListView lvFaturas;
    private ArrayList<Fatura> faturas;

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private SearchView searchView;

    public ListaFaturasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_faturas, container, false);
        setHasOptionsMenu(true); // vai chamar o menu
        lvFaturas = view.findViewById(R.id.lvFaturaFragment); // vai adicionar à lvHome da activity home os fragmentos que queremos mandar
        faturas = SingletonGestorLoja.getInstance(getContext()).getFaturasBD();

        lvFaturas.setAdapter(new ListaFaturasAdaptador(getContext(), faturas));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getContext()).setFaturasListener(this);
        SingletonGestorLoja.getInstance(getContext()).getFaturasAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );
        SingletonGestorLoja.getInstance(getContext()).getEmpresaAPI(getContext());
        lvFaturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), livros.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
                // Alinea 5.2 Ficha 5 Books - Inicia a atividade com a informação da fatura após clicar
                /*fragmentManager = getActivity().getSupportFragmentManager();
                fragment = new ListaFaturaDetalhesFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();*/

                // tentei me guiar pelo books no menu do main activity mas continuava a dar erro. Apenas consegui dessa forma
                Fatura faturaSelecionada = faturas.get(position);



                Bundle bundle = new Bundle();
                bundle.putInt("ID_FATURA", faturaSelecionada.getId());
                bundle.putDouble("VALOR_TOTAL", faturaSelecionada.getValorFatura());
                bundle.putString("DATA", faturaSelecionada.getData());
                ListaFaturaDetalhesFragment detalhesFragment = new ListaFaturaDetalhesFragment();
                detalhesFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, detalhesFragment).addToBackStack(null).commit(); // Isto permite ao utilizador voltar ao fragmento anterior
            }
        });
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
            for (Fatura f : SingletonGestorLoja.getInstance(getContext()).getFaturasBD())
                if (f.getEstado().toString().toLowerCase().contains(newText.toLowerCase())) {
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

    @Override
    public void onRefreshListaFaturas(ArrayList<Fatura> faturas) {
        if(faturas != null){
            lvFaturas.setAdapter(new ListaFaturasAdaptador(getContext(), faturas));
        }
    }
}