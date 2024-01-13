package pt.ipleiria.estg.dei.brindeszorro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.CarrinhoActivity;
import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaCarrinhosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.adaptadores.ListaFavoritosAdaptador;
import pt.ipleiria.estg.dei.brindeszorro.listeners.CarrinhosListener;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Carrinho;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaCarrinhoFragment extends Fragment implements CarrinhosListener {

    private ListView lvCarrinhos;
    private ArrayList<Carrinho> carrinhos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_carrinho, container, false);
        setHasOptionsMenu(true);
        lvCarrinhos = view.findViewById(R.id.lvCarrinhoCompras);//vai add a LV os frags q queremos mostrar
        carrinhos = SingletonGestorLoja.getInstance(getContext()).getCarrinhosBD();

        lvCarrinhos.setAdapter(new ListaCarrinhosAdaptador(getContext(), carrinhos));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);//alem disso fazer o implements la em cima
        SingletonGestorLoja.getInstance(getContext()).setCarrinhosListener(this);
        SingletonGestorLoja.getInstance(getContext()).getAllCarrinhosAPI(getContext(),sharedPreferences.getString(Public.TOKEN,"TOKEN") );


        return view;
    }

    @Override
    public void onRefreshListaCarrinhos(ArrayList<Carrinho> carrinhos) {
        if(carrinhos != null){
            lvCarrinhos.setAdapter(new ListaCarrinhosAdaptador(getContext(), carrinhos));
        }
    }
}
