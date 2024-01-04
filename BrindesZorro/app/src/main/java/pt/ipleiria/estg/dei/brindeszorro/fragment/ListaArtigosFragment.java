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
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity;
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

        // Não está a vir para aqui, possivelmente terá de se apagar
        // esta a ir para o listaHomeFragment



        Toast.makeText(getContext(), "entrou", Toast.LENGTH_SHORT).show();

        // inicia o layout com o activity_home
        View view = inflater.inflate(R.layout.fragment_lista_home, container, false);

        lvArtigos = view.findViewById(R.id.lvHomeArtigos);
        //vai buscar a lista de livros ao singleton
        artigos = SingletonGestorLoja.getInstance(getContext()).getArtigosBD();

        System.out.println("--->" + artigos);
        // DEFINIR ADAPTADOR

        lvArtigos.setAdapter(new ListaArtigosAdaptador(getContext(),artigos));

        //temos que utilizar um listener para saber qual o item que foi selecionado
        lvArtigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // inserir uma toast para ver o nome do artigo clicado
                Toast.makeText(getContext(),artigos.get(position).getNome(), Toast.LENGTH_SHORT).show();

                //agora vamos iniciar uma activity assim como passar o ID do artigo
                Intent intent = new Intent(getContext(), DetalhesArtigoActivity.class);
                intent.putExtra(DetalhesArtigoActivity.ID_ARTIGO,(int) position);
                startActivity(intent);
            }
        });

        return view;
    }
}