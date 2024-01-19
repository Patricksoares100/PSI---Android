package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Favorito;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFavoritosAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Favorito> favoritos;
    private Artigo artigo;
    private int quantidade = 1;

    public ListaFavoritosAdaptador(Context context, ArrayList<Favorito> favoritos) {
        this.context = context;
        this.favoritos = favoritos;
    }
    @Override
    public int getCount() {return favoritos.size();}
    @Override
    public Object getItem(int position) {return favoritos.get(position);}
    @Override
    public long getItemId(int position) {return favoritos.get(position).getId();}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_favoritos, null);
        }
        ViewHolderLista viewHolderLista = (ListaFavoritosAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolderLista ==null){
            viewHolderLista = new ListaFavoritosAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        Button buttonAdicionarCarrinhoFavoritos = convertView.findViewById(R.id.buttonAdicionarCarrinhoFavoritos);
        buttonAdicionarCarrinhoFavoritos.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
            SingletonGestorLoja.getInstance(context).adicionarFavoritoCarrinhoAPI(favoritos.get(position), context, sharedPreferences.getString(Public.TOKEN, "TOKEN"));
        });

        Button buttonRemoverFavorito = convertView.findViewById(R.id.buttonRemoverFavorito);
        buttonRemoverFavorito.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
            SingletonGestorLoja.getInstance(context).removerFavoritoAPI(favoritos.get(position), context, sharedPreferences.getString(Public.TOKEN, "TOKEN"));
        });

        viewHolderLista.update(favoritos.get(position));
        return convertView;
    }

    public class ViewHolderLista {
        private TextView tvNomeArtigo, tvPreco;
        private ImageView ivCaracol;
        public ViewHolderLista(View view) {
            tvNomeArtigo = view.findViewById(R.id.tvTituloNomeArtigo);
            tvPreco = view.findViewById(R.id.tvValorTotal);
            ivCaracol = view.findViewById(R.id.imageView3);
        }

        public void update(Favorito favorito){

            tvNomeArtigo.setText(favorito.getNomeArtigo());
            tvPreco.setText(String.format("%.2f",favorito.getValorArtigo())+" €");

            Glide.with(context)
                    .load(favorito.getImagem())
                    .placeholder(R.drawable.ipleiria)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivCaracol);
        }
    }
}
