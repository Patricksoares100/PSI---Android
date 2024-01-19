package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;

public class ListaAvaliacaosAdaptador extends BaseAdapter {

    // Alinea 4.3 - Ficha Books ListViews
    // Esta classe usa estes três atributos
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Avaliacao> avaliacaos;

    // Alinea 4.4 - Ficha Books ListViews
    // O construtor deve receber como parâmetros um context e a lista de 'avaliacaos'
    public ListaAvaliacaosAdaptador(Context context, ArrayList<Avaliacao> avaliacaos) {
        this.context = context;
        this.avaliacaos = avaliacaos;
    }

    public ListaAvaliacaosAdaptador() {

    }

    @Override
    public int getCount() {
        return avaliacaos.size();
    }

    @Override
    public Object getItem(int position) {
        return avaliacaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return avaliacaos.get(position).getId();
    }

    // Alinea 4.5.2 - Ficha 5 Books ListViews
    // O método getView, o parâmetro View servirá para reutilizar a view para apresentar o
    // layout de cada item, em vez de estar sempre a criar uma nova
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_lista_avaliacaos, null);

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(avaliacaos.get(position));

        Avaliacao avaliacao = avaliacaos.get(position);
        viewHolderLista.tvComentarioAvaliacao.setText(avaliacao.getComentario());
        //viewHolderLista.tvClassificacaoAvaliacao.setText(String.valueOf(avaliacao.getClassificacao()));

        return convertView;
    }

    // Alinea 4.5.1 - Ficha 5 Books ListViews
    // Para acesso aos componentes visuais
    private class ViewHolderLista {
        private TextView tvNomeArtigoAvaliacao, tvComentarioAvaliacao, tvClassificacaoAvaliacao;
        RatingBar ratingClassificacaoAvaliacao;
        public ViewHolderLista(View view) {
            tvNomeArtigoAvaliacao = view.findViewById(R.id.tvNomeArtigoAvaliacaoApresentado);
            tvComentarioAvaliacao = view.findViewById(R.id.tvComentarioAvaliacaoApresentado);
            //tvClassificacaoAvaliacao = view.findViewById(R.id.tvClassificacaoAvaliacao);
            ratingClassificacaoAvaliacao = view.findViewById(R.id.ratingBarClassificacaoAvaliacao);
        }

        public void update(Avaliacao avaliacao) {
            Artigo artigo = SingletonGestorLoja.getInstance(context).getArtigo(avaliacao.getArtigoId());

            tvNomeArtigoAvaliacao.setText(""+ artigo.getNome());
            tvComentarioAvaliacao.setText(""+avaliacao.getComentario());
            ratingClassificacaoAvaliacao.setRating((float)avaliacao.getClassificacao());

        }
    }
}
