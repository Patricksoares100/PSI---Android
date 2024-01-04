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

public class ListaHomeFragment extends Fragment {

    private ListView lvArtigos;
    private ArrayList<Artigo> artigos;
    public ListaHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getContext(), "entrou", Toast.LENGTH_SHORT).show();// mensagem de erro para ver se entra!!!!

        // inicia o layout com o activity_home
        View view = inflater.inflate(R.layout.fragment_lista_home, container, false);
        lvArtigos = view.findViewById(R.id.lvHomeArtigos);
        //vai buscar a lista de livros ao singleton
        artigos = SingletonGestorLoja.getInstance(getContext()).getArtigosBD();
        for (Artigo artigo : artigos) {
            Log.d("ArtigoDump", "ID: " + artigo.getId());
            Log.d("ArtigoDump", "Nome: " + artigo.getNome());
            Log.d("ArtigoDump", "Descrição: " + artigo.getDescricao());
            Log.d("ArtigoDump", "Referência: " + artigo.getReferencia());
            Log.d("ArtigoDump", "Preço: " + artigo.getPreco());
           /* Log.d("ArtigoDump", "Stock Atual: " + artigo.getStockAtual());
            Log.d("ArtigoDump", "IVA ID: " + artigo.getIvaId());
            Log.d("ArtigoDump", "Fornecedor ID: " + artigo.getFornecedorId());
            Log.d("ArtigoDump", "Categoria ID: " + artigo.getCategoriaId());
            Log.d("ArtigoDump", "Perfil ID: " + artigo.getPerfilId());
            Log.d("ArtigoDump", "-----------------------");*/
        }
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