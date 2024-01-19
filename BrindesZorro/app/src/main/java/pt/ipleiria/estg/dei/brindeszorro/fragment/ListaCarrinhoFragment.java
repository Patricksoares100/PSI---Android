package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.CarrinhosListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Carrinho;
import pt.ipleiria.estg.dei.brindeszorro.modelo.LinhaFatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaCarrinhoFragment extends Fragment implements CarrinhosListener {

    private ListView lvCarrinhos;
    private ArrayList<Carrinho> carrinhos;
    private Button buttonLimparCarrinho, buttonConcluirCompra;
    private TextView tvMostrarValorCarrinho;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_carrinho, container, false);
        setHasOptionsMenu(true);
        lvCarrinhos = view.findViewById(R.id.lvCarrinhoCompras);//vai add a LV os frags q queremos mostrar
        carrinhos = SingletonGestorLoja.getInstance(getContext()).getCarrinhosBD();

        //onRefreshListaCarrinhos(carrinhos);
        buttonLimparCarrinho = (Button) view.findViewById(R.id.buttonLimparCarrinho);
        buttonConcluirCompra = (Button) view.findViewById(R.id.buttonConcluirCompra);
        tvMostrarValorCarrinho =  view.findViewById(R.id.tvMostrarValorCarrinho);

        lvCarrinhos.setAdapter(new ListaCarrinhosAdaptador(getContext(), carrinhos));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getContext()).setCarrinhosListener(this);
        SingletonGestorLoja.getInstance(getContext()).getAllCarrinhosAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));
        atualizarTotalCarrinho(carrinhos);

        buttonConcluirCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onClickConcluirCompra(v);
            }
        });
        buttonLimparCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLimparCarrinho(v);
            }
        });
      /*  if(carrinhos.isEmpty()){
            buttonLimparCarrinho.setAlpha(0.5f);
            buttonConcluirCompra.setAlpha(0.5f);
        }*/

        return view;
    }

    public void onClickConcluirCompra(View view){
       /* buttonConcluirCompra.setAlpha(0.5f);
        buttonLimparCarrinho.setAlpha(0.5f);*/
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getContext()).comprarCarrinhoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));
        //SingletonGestorLoja.getInstance(getContext()).adicionarLinhaFaturaAPI(linhaFatura, getContext(), sharedPreferences.getString(Public.TOKEN, "TOKEN"));
        atualizarTotalCarrinho(carrinhos);
    }

    public void onClickLimparCarrinho(View view) {
       /* buttonLimparCarrinho.setAlpha(0.5f);
        buttonConcluirCompra.setAlpha(0.5f);*/
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getContext()).removerAllCarrinhoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));
        atualizarTotalCarrinho(carrinhos);
    }
    @Override
    public void onRefreshListaCarrinhos(ArrayList<Carrinho> carrinhos) {
        if(carrinhos != null){
            lvCarrinhos.setAdapter(new ListaCarrinhosAdaptador(getContext(), carrinhos));
            atualizarTotalCarrinho(carrinhos);

        }
    }

    public double atualizarTotalCarrinho(ArrayList<Carrinho> lvCarrinhos){
        double total = 0;
        for (Carrinho carrinho : lvCarrinhos){
            int quantidade = carrinho.getQuantidade();
            double valorArtigo = carrinho.getValorUnitario();

            total += quantidade * valorArtigo;
        }
        tvMostrarValorCarrinho.setText("" + total);
        return total;
    }
}
