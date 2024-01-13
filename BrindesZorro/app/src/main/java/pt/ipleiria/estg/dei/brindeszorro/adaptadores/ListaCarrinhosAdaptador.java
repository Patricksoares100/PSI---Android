package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Carrinho;


public class ListaCarrinhosAdaptador extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Carrinho> carrinhos;

    public ListaCarrinhosAdaptador(Context context, ArrayList<Carrinho> carrinhos) {
        this.context = context;
        this.carrinhos = carrinhos;
    }
    @Override
    public int getCount() {
        return carrinhos.size();
    }

    @Override
    public Object getItem(int position) {
        return carrinhos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carrinhos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_carrinho, null);
        }
        ViewHolderLista viewHolderLista = (ListaCarrinhosAdaptador.ViewHolderLista) convertView.getTag();
       // ViewHolderLista viewHolderLista = (ListaCarrinhosAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolderLista ==null){
            viewHolderLista = new ListaCarrinhosAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(carrinhos.get(position));
        return convertView;
    }

    public class ViewHolderLista {
        private TextView tvNomeArtigo, tvValorUnitario, tvQuantidade, tvValorTotal;
        private ImageView ivCarrinho;

        public ViewHolderLista(View view){
            tvNomeArtigo = view.findViewById(R.id.idNomeArtigo);
            tvValorUnitario = view.findViewById(R.id.tvPrecoValorUnitCarrinho);
            tvQuantidade = view.findViewById(R.id.tvNumeroQtd);
            ivCarrinho = view.findViewById(R.id.imageViewCarrinho);
            tvValorTotal = view.findViewById(R.id.tvPrecoTotalArtigo);
        }

        public void update(Carrinho carrinho){
            tvNomeArtigo.setText(carrinho.getNome());
            tvValorUnitario.setText(""+carrinho.getValorUnitario() + " €");
            tvQuantidade.setText(""+ carrinho.getQuantidade());
            tvValorTotal.setText(""+ (carrinho.getValorUnitario() * carrinho.getQuantidade())+ " €");
            Glide.with(context)
                    .load(carrinho.getImagem())
                    .placeholder(R.drawable.ipleiria)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivCarrinho);
        }

    }
}
