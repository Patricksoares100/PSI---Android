package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.Response;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.CarrinhoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFavoritosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.CarrinhosListener;
import pt.ipleiria.estg.dei.brindeszorro.listeners.FaturasListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Carrinho;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaCarrinhoFragment extends Fragment implements CarrinhosListener {

    private ListView lvCarrinhos;
    private ArrayList<Carrinho> carrinhos;
    private Button buttonLimparCarrinho, buttonConcluirCompra;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_carrinho, container, false);
        setHasOptionsMenu(true);
        lvCarrinhos = view.findViewById(R.id.lvCarrinhoCompras);//vai add a LV os frags q queremos mostrar
        carrinhos = SingletonGestorLoja.getInstance(getContext()).getCarrinhosBD();
        buttonLimparCarrinho = (Button) view.findViewById(R.id.buttonLimparCarrinho);
        buttonConcluirCompra = (Button) view.findViewById(R.id.buttonConcluirCompra);

        lvCarrinhos.setAdapter(new ListaCarrinhosAdaptador(getContext(), carrinhos));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getContext()).setCarrinhosListener(this);
        SingletonGestorLoja.getInstance(getContext()).getAllCarrinhosAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );

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
        if(carrinhos.isEmpty()){
           // buttonLimparCarrinho.setEnabled(false);
            buttonLimparCarrinho.setAlpha(0.5f);
           // buttonConcluirCompra.setEnabled(false);
            buttonConcluirCompra.setAlpha(0.5f);
        }/*else {
            buttonLimparCarrinho.setVisibility(View.VISIBLE);
            buttonConcluirCompra.setVisibility(View.VISIBLE);
        }*/

        return view;
    }

    public void onClickConcluirCompra(View view){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getContext()).comprarCarrinhoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));
        /*SingletonGestorLoja.getInstance(getContext()).comprarCarrinhoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"), new Response.Listener(){
            @Override
            public void onResponse(Object response) {
                SingletonGestorLoja.getInstance(getContext()).setCarrinhosListener();
                SingletonGestorLoja.getInstance(getContext()).getAllCarrinhosAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );
                //onRefreshListaCarrinhos(carrinhos);
                System.out.println("---> carrinho vazio" + carrinhos);
            }
        });*/

    }

    public void onClickLimparCarrinho(View view) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(getContext()).removerAllCarrinhoAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN"));

    }
    @Override
    public void onRefreshListaCarrinhos(ArrayList<Carrinho> carrinhos) {
        if(carrinhos != null){
            lvCarrinhos.setAdapter(new ListaCarrinhosAdaptador(getContext(), carrinhos));
        }
    }
}
