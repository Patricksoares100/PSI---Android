package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Intent;
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
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;


public class ListaAvaliacaosFragment extends Fragment {

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
        avaliacaos = SingletonGestorLoja.getInstance(getContext()).getAvaliacaosBD();
        for (Avaliacao avaliacao : avaliacaos) {
            Log.d("ArtigoDump", "ID: " + avaliacao.getId());
            Log.d("ArtigoDump", "Comentario: " + avaliacao.getComentario());
        }

        // Alinea 5.1 Ficha 5 Books - Atribuir/definir o adaptador
        lvAvaliacaos.setAdapter(new ListaAvaliacaosAdaptador(getContext(), avaliacaos));

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
}