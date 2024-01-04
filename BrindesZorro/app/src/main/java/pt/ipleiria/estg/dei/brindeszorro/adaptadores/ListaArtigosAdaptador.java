package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;

public class ListaArtigosAdaptador extends BaseAdapter {

    private Context context; //é necessário para o adaptador
    private LayoutInflater inflater; //ter acesso ao layout específico para cada item
    private ArrayList<Artigo> artigos; // vai guardar a lista de artigos

    public ListaArtigosAdaptador(Context context, ArrayList<Artigo> artigos) {
        // vai receber como parametros um context e a lista de artigos
        this.context = context;
        this.artigos = artigos;
    }

    public ListaArtigosAdaptador() {

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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
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

        //viewHolderLista.update(artigos.get(position));
        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvId, tvPrecoTotal, tvPrecoUnit, tvNome, tvDescricao, tvAvaliacao;

        public ViewHolderLista(View view) {
            tvId = view.findViewById(R.id.tvNomeArtigoDetalhes);
            tvPrecoTotal = view.findViewById(R.id.tvValorTotalDetalhes);
            tvPrecoUnit = view.findViewById(R.id.tvValorUnitarioArtigo);
            tvNome = view.findViewById(R.id.tvNomeArtigoDetalhes);
            tvDescricao = view.findViewById(R.id.tvDescricaoDetalhes);
            tvAvaliacao = view.findViewById(R.id.tvAvaliacaoArtigoDetalhes);
        }

        /*
        public void update(Artigo artigo) {
            tvId.setText(""+artigo.getId());
            tvPreco.setText(""+artigo.getPreco());
            tvNome.setText(""+artigo.getNome());
            tvDescricao.setText(""+artigo.getNome());
            tvReferencia.setText(""+artigo.getReferencia());
        }
         */
    }

}
