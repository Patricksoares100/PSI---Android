package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaArtigosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaAvaliacaosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFaturasDetalhesAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FaturasListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Empresa;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFaturaDetalhesFragment extends Fragment  {

    private ListView lvFaturasDetalhes;
    private Empresa empresa;
    private TextView tvMorada,tvEmail,tvTelefone,tvNomeEmpresa;
    private ArrayList<Artigo> artigos;
    private ArrayList<Fatura> faturas = new ArrayList<>();

    public ListaFaturaDetalhesFragment(){

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fatura_detalhes, container, false);
        setHasOptionsMenu(true);
        lvFaturasDetalhes = view.findViewById(R.id.lvArtigosFatura);
        artigos = SingletonGestorLoja.getInstance(getContext()).getArtigosBD();

        lvFaturasDetalhes.setAdapter(new ListaFaturasDetalhesAdaptador(getContext(), faturas));
        lvFaturasDetalhes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),artigos.get(position).getNome(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), DetalhesArtigoActivity.class);
                intent.putExtra(DetalhesArtigoActivity.ID_ARTIGO,(int) position);
                startActivity(intent);
            }
        });

        tvMorada = view.findViewById(R.id.tvMoradaEmpresa);
        tvEmail = view.findViewById(R.id.tvEmailEmpresa);
        tvTelefone = view.findViewById(R.id.tvTelefoneEmpresa);
        tvNomeEmpresa = view.findViewById(R.id.tvNomeEmpresa);

        SingletonGestorLoja.getInstance(getContext()).getEmpresaAPI(getContext());
        empresa = SingletonGestorLoja.getInstance(getContext()).getEmpresaBD();
        System.out.println("---> EMPRESAAAAAA" + empresa);

        //empresa = SingletonGestorLoja.getInstance(getContext()).getEmpresa(empresa.getId()); //se é fragmento fica getContext

        tvMorada.setText("" + empresa.getMorada());
        tvEmail.setText("" + empresa.getEmail());
        tvTelefone.setText("" + empresa.getTelefone());
        tvNomeEmpresa.setText("" + empresa.getNome());


        return view;
    }

}
