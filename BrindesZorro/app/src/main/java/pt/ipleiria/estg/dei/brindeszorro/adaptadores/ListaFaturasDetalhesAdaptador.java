package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
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
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Empresa;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.LinhaFatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFaturasDetalhesAdaptador extends BaseAdapter {

    private Context context;
    private Empresa empresa;
    private LayoutInflater inflater;
    private ArrayList<Artigo> artigos;
    private ArrayList<Fatura> faturas;
    private ArrayList<LinhaFatura> linhaFaturas;

    public ListaFaturasDetalhesAdaptador(Context context, ArrayList<LinhaFatura> linhaFaturas) {
        this.context = context;
        this.linhaFaturas = linhaFaturas;
        //this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return linhaFaturas.size();
    }

    @Override
    public Object getItem(int position) {
        return linhaFaturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return linhaFaturas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_fatura_detalhes, parent, false);
        }

        ViewHolderLista viewHolderLista = (ListaFaturasDetalhesAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null){
            viewHolderLista = new ListaFaturasDetalhesAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(linhaFaturas.get(position));

        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvArtigo, tvPreco, tvQuantidade, tvIvaTotal, tvValorTotal;
        ImageView ivArtigo;

        public ViewHolderLista(View view) {
            tvArtigo = view.findViewById(R.id.tvNomeArtigo);
            tvPreco = view.findViewById(R.id.tvValorArtigoDetalhesFatura);
            tvQuantidade = view.findViewById(R.id.tvQuantidadeDetalhesFatura);
            tvIvaTotal = view.findViewById(R.id.tvValorIvaTotalDetalhesFatura);
            tvValorTotal = view.findViewById(R.id.tvValorTotalDetalhesFatura);
            ivArtigo = view.findViewById(R.id.ivArtigoDetalhesFatura);
        }

        public void update(LinhaFatura linhaFatura) {


            tvArtigo.setText("" + linhaFatura.getNome());
            tvPreco.setText(String.format("%.2f",linhaFatura.getPreco())+ " €");
            tvQuantidade.setText("" + linhaFatura.getQuantidade());
            tvIvaTotal.setText(String.format("%.2f",linhaFatura.getValor_iva())+ " €");
            tvValorTotal.setText(String.format("%.2f",linhaFatura.getValor())+ " €");
            Glide.with(context)
                    .load(linhaFatura.getImagem())
                    .placeholder(R.drawable.ipleiria)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivArtigo);

        }
    }
}
