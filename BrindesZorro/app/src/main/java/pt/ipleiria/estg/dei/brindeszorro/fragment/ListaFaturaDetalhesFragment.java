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

import java.text.DecimalFormat;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.AvaliacaoComentarioActivity;
import pt.ipleiria.estg.dei.brindeszorro.DetalhesArtigoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaArtigosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaAvaliacaosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFaturasDetalhesAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.ArtigosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FaturasListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.LinhasFaturasListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Empresa;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.LinhaFatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFaturaDetalhesFragment extends Fragment implements LinhasFaturasListener {

    private ListView lvFaturasDetalhes;
    private Empresa empresa;
    private TextView tvMorada, tvEmail, tvTelefone, tvNomeEmpresa, tvData, tvIvaTotal, tvValorToTal;
    private ArrayList<LinhaFatura> linhaFaturas;

    public ListaFaturaDetalhesFragment(){

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fatura_detalhes, container, false);
        setHasOptionsMenu(true);
        // Recuperar o ID da fatura do Bundle
        Bundle bundle = getArguments();

        int idFatura = bundle.getInt("ID_FATURA", -1);


        lvFaturasDetalhes = view.findViewById(R.id.lvArtigosFatura);
        //empresa = SingletonGestorLoja.getInstance(getContext()).getEmpresaBD();
        linhaFaturas = SingletonGestorLoja.getInstance(getContext()).getLinhaFaturasBD();


        tvMorada = view.findViewById(R.id.tvMoradaEmpresa);
        tvEmail = view.findViewById(R.id.tvEmailEmpresa);
        tvTelefone = view.findViewById(R.id.tvTelefoneEmpresa);
        tvNomeEmpresa = view.findViewById(R.id.tvNomeEmpresaFatura);
        tvData = view.findViewById(R.id.tvDataFaturaValor);
        tvValorToTal = view.findViewById(R.id.tvValorTotalFaturaDetalhes);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getContext()).setLinhasFaturasListener(this);
        SingletonGestorLoja.getInstance(getContext()).getEmpresaAPI(getContext());
        SingletonGestorLoja.getInstance(getContext()).getAllLinhasFaturasAPI(getContext(),idFatura);
       // SingletonGestorLoja.getInstance(getContext()).getFaturasAPI(getContext(), sharedPreferences.getString(Public.TOKEN,"TOKEN"));

       // linhaFaturas = SingletonGestorLoja.getInstance(getContext()).getLinhaFaturasBD();

        /*lvFaturasDetalhes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),linhaFaturas.get(position).getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), AvaliacaoComentarioActivity.class);
                intent.putExtra(AvaliacaoComentarioActivity.IDAVALIACAOS,(int) position);
                startActivity(intent);
            }

        });*/

        empresa = SingletonGestorLoja.getInstance(getContext()).getEmpresaBD();
        linhaFaturas = SingletonGestorLoja.getInstance(getContext()).getLinhaFaturasBD();


        //empresa = SingletonGestorLoja.getInstance(getContext()).getEmpresa(empresa.getId()); //se é fragmento fica getContext

        tvMorada.setText("" + empresa.getMorada());
        tvEmail.setText("" + empresa.getEmail());
        tvTelefone.setText("" + empresa.getTelefone());
        tvNomeEmpresa.setText("" + empresa.getNome());
        tvData.setText("" + bundle.getString("DATA"));
        //tvIvaTotal.setText("" + fatura.get);
        tvValorToTal.setText(String.format("%.2f",bundle.getDouble("VALOR_TOTAL"))+" €");


        return view;
    }

    @Override
    public void onRefreshListaLinhasFaturas(ArrayList<LinhaFatura> linhaFaturas) {
        if(linhaFaturas != null){
            lvFaturasDetalhes.setAdapter(new ListaFaturasDetalhesAdaptador(getContext(), linhaFaturas));

        }
    }
}
