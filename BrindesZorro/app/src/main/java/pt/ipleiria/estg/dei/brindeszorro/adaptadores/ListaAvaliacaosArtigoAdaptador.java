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
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;

public class ListaAvaliacaosArtigoAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Avaliacao> avaliacaos;

    // Alinea 4.4 - Ficha Books ListViews
    // O construtor deve receber como parâmetros um context e a lista de 'avaliacaos'
    public ListaAvaliacaosArtigoAdaptador(Context context, ArrayList<Avaliacao> avaliacaos) {
        this.context = context;
        this.avaliacaos = avaliacaos;
    }

    public ListaAvaliacaosArtigoAdaptador() {

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
            convertView = inflater.inflate(R.layout.item_lista_avaliacao_artigo, null);

        ListaAvaliacaosArtigoAdaptador.ViewHolderLista viewHolderLista = (ListaAvaliacaosArtigoAdaptador.ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ListaAvaliacaosArtigoAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(avaliacaos.get(position));

        //Avaliacao avaliacao = avaliacaos.get(position);
        //viewHolderLista.tvComentarioAvaliacao.setText(avaliacao.getComentario());
       // viewHolderLista.ratingClassificacaoAvaliacao.setRating(avaliacao.getClassificacao());

        return convertView;
    }

    // Alinea 4.5.1 - Ficha 5 Books ListViews
    // Para acesso aos componentes visuais
    private class ViewHolderLista {
        private TextView tvNomeArtigoAvaliacao, tvComentarioAvaliacao;
        RatingBar ratingClassificacaoAvaliacao;

        public ViewHolderLista(View view) {
           // tvNomeArtigoAvaliacao = view.findViewById(R.id.tvNomeUserAvaliacaoApresentado);
            tvComentarioAvaliacao = view.findViewById(R.id.tvComentarioUserAvaliacaoApresentado);
            ratingClassificacaoAvaliacao = view.findViewById(R.id.ratingBarClassificacaoUserAvaliacao);
        }

        public void update(Avaliacao avaliacao) {
           // tvNomeArtigoAvaliacao.setText(""+avaliacao.getPerfilId());//ja altero na API
            tvComentarioAvaliacao.setText(""+avaliacao.getComentario());
           // float
            ratingClassificacaoAvaliacao.setRating((float)avaliacao.getClassificacao());

        }
    }
}
