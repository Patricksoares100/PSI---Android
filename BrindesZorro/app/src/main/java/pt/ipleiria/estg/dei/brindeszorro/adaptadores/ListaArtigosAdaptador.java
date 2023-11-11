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
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;

public class ListaArtigosAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Artigo> artigos;

    public ListaArtigosAdaptador(Context context, ArrayList<Artigo> artigos) {
        this.context = context;
        this.artigos = artigos;
    }

    public ListaArtigosAdaptador() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_artigos, null);
        }
        ViewHolderLista viewHolderLista = (ListaArtigosAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolderLista ==null){
            viewHolderLista = new ListaArtigosAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(artigos.get(position));
        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvId, tvPreco, tvNome, tvDescricao, tvReferencia;

        // ELABORAR BEM ESTE METODO CONSOANTE O LAYOUT DO ARTIGO QUE FOR PARA USAR!!!!!
        // ESTA COMENTADO POIS NEM TODOS OS ID's DE ATRIBUTOS ESTÃO BEM DEFINIDOS NO LAYOUT
        public ViewHolderLista(View view) {
            //tvId = view.findViewById(R.id.tvIdArtigo);
            //tvPreco = view.findViewById(R.id.tvPrecoArtigo);
            //tvNome = view.findViewById(R.id.tvNomeArtigo);
            //tvDescricao = view.findViewById(R.id.tvDescricao);
            //tvReferencia = view.findViewById(R.id.tvReferenciaArtigo);
        }

        public void update(Artigo artigo) {
            tvId.setText(""+artigo.getId());
            tvPreco.setText(""+artigo.getPreco());
            tvNome.setText(""+artigo.getNome());
            tvDescricao.setText(""+artigo.getNome());
            tvReferencia.setText(""+artigo.getReferencia());
        }
    }

}
