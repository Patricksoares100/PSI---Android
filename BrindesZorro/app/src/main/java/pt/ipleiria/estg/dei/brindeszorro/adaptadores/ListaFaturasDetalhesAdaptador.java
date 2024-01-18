package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Empresa;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFaturasDetalhesAdaptador extends BaseAdapter {

    private Context context;
    private Empresa empresa;
    private LayoutInflater inflater;
    private ArrayList<Artigo> artigos;
    private ArrayList<Fatura> faturas;

    public ListaFaturasDetalhesAdaptador(Context context, ArrayList<Fatura> faturas) {
        this.context = context;
        this.faturas = faturas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return faturas.size();
    }

    @Override
    public Object getItem(int position) {
        return faturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return faturas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLista viewHolderLista;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_fatura_detalhes, parent, false);
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        } else {
            viewHolderLista = (ViewHolderLista) convertView.getTag();
        }

        viewHolderLista.update((Fatura) getItem(position));
        SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
        SingletonGestorLoja.getInstance(context).getEmpresaAPI(context);

        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvData, tvValor;

        public ViewHolderLista(View view) {
            tvData = view.findViewById(R.id.tvNomeEmpresa);
            tvValor = view.findViewById(R.id.tvValorFaturaDetalhes);
        }

        // Empresa empresa = SingletonGestorLoja.getInstance(context).getEmpresa();
        public void update(Fatura fatura) {
            tvData.setText("" + fatura.getData());
            tvValor.setText("" + fatura.getValorFatura());

        }
    }
}
