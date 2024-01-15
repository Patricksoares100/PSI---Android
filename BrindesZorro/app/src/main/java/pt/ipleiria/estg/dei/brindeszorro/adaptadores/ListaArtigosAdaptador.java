package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaArtigosAdaptador extends BaseAdapter {

    private Context context; //é necessário para o adaptador
    private LayoutInflater inflater; //ter acesso ao layout específico para cada item
    private ArrayList<Artigo> artigos; // vai guardar a lista de artigos
    private Artigo artigo;
    private int quantidade = 1;

    public ListaArtigosAdaptador(Context context, ArrayList<Artigo> artigos) {
        // vai receber como parametros um context e a lista de artigos
        this.context = context;
        this.artigos = artigos;
    }

    @Override
    public int getCount() {
        return artigos.size();
    }

    @Override
    public Object getItem(int position) {
        return artigos.get(position);
    }

    @Override
    public long getItemId(int position) {return artigos.get(position).getId();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // o parâmetro View servirá para reutilizar a view para apresentar o
        //layout de cada item, em vez de estar sempre a criar uma nova
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_artigos, null);
        }
        ViewHolderLista viewHolderLista = (ListaArtigosAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolderLista ==null){
            viewHolderLista = new ListaArtigosAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        Button ButtonCarrinhoArtigo = convertView.findViewById(R.id.ButtonCarrinhoArtigo);
        ButtonCarrinhoArtigo.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
            SingletonGestorLoja.getInstance(context).adicionarCarrinhoAPI(artigos.get(position), context, sharedPreferences.getString(Public.TOKEN, "TOKEN"), quantidade);
            Toast.makeText(context, "teste Carrinho", Toast.LENGTH_SHORT).show();
        });


        Button ButtonFavoritoArtigo = convertView.findViewById(R.id.ButtonFavoritoArtigo);
        ButtonFavoritoArtigo.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
            SingletonGestorLoja.getInstance(context).adicionarFavoritoAPI(artigos.get(position), context, sharedPreferences.getString(Public.TOKEN, "TOKEN"));

            Toast.makeText(context, "teste Favoritos", Toast.LENGTH_SHORT).show();
        });

        viewHolderLista.update(artigos.get(position));
            //Perceber melhor -NAO ESQUECER
           /* Artigo artigo = artigos.get(position);
            viewHolderLista.tvNome.setText(artigo.getNome());
            viewHolderLista.tvPreco.setText(String.valueOf(artigo.getPreco()));
*/
        return convertView;

    }

    private class ViewHolderLista {
        private TextView tvId, tvNome, tvDescricao, tvPreco, tvAvaliacao, tvFornecedor, tvCategoria,tvStock_atual, tvIva_id;
        private ImageView imagem;

        public ViewHolderLista(View view) {
            tvNome = view.findViewById(R.id.tvNomeArtigo);
            tvPreco = view.findViewById(R.id.tvValorArtigo);
            imagem = view.findViewById(R.id.ivArtigo);
        }
        public void update(Artigo artigo) {

            tvNome.setText(artigo.getNome());
            tvPreco.setText(""+artigo.getPreco()+" €");
            Glide.with(context)
                    .load(artigo.getImagem())
                    .placeholder(R.drawable.ipleiria)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagem);
        }

    }

}
