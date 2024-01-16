package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.AvaliacaoComentarioActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaAvaliacaosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaoListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.AvaliacaosListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;


public class ListaAvaliacaosFragment extends Fragment implements AvaliacaosListener {

    private ListView lvAvaliacaos;
    private ArrayList<Avaliacao> avaliacaos;
    public ListaAvaliacaosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_avaliacaos, container, false);

        // VAI FAZER COM QUE O MENU APAREÇA
        setHasOptionsMenu(true);


        lvAvaliacaos = view.findViewById(R.id.lvAvaliacaoLista);
        // getAvaliacaosBD() função que está no SingletonGestorLoja
        avaliacaos = SingletonGestorLoja.getInstance(getContext()).getAllAvaliacaosUserBD();
        System.out.println("---> antes da api "+ avaliacaos);
        // Alinea 5.1 Ficha 5 Books - Atribuir/definir o adaptador
        lvAvaliacaos.setAdapter(new ListaAvaliacaosAdaptador(getContext(), avaliacaos));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        //SingletonGestorLoja.getInstance(getContext()).setAvaliacaosListener(AvaliacaosListener);
        SingletonGestorLoja.getInstance(getContext()).setAvaliacaosListener(this);
        SingletonGestorLoja.getInstance(getContext()).getAvaliacaoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );
        System.out.println("---> depois  da api ");

        // Alinea 5.2 Ficha 5 Books - utilizar um listener para saber qual o item que foi selecionado
        lvAvaliacaos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), livros.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
                // Alinea 5.2 Ficha 5 Books - Inicia a atividade com a informação da fatura após clicar
                Intent intent = new Intent(getContext(), AvaliacaoComentarioActivity.class);
                intent.putExtra(AvaliacaoComentarioActivity.IDAVALIACAOS, (int) id);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onRefreshListaAvaliacaos(ArrayList<Avaliacao> avaliacaos) {
        if (avaliacaos != null) {
            lvAvaliacaos.setAdapter(new ListaAvaliacaosAdaptador(getContext(), avaliacaos));
        }
    }
}