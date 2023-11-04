package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;

public class ListaFaturasAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Fatura> faturas;

    public ListaFaturasAdaptador(Context context, ArrayList<Fatura> faturas){
        this.context = context;
        this.faturas = faturas;
    }

    public ListaFaturasAdaptador(){

    }

    @Override
    public int getCount() {return faturas.size();}

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
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel
        if (convertView ==null){
            convertView = inflater.inflate(R.layout.activity_faturas, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista ==null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(faturas.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private TextView tvId,tvValor,tvEstado;


        public ViewHolderLista(View view){
            tvId = view.findViewById(R.id.tvIdFatura);
            tvValor = view.findViewById(R.id.tvValorFatura);
            tvEstado = view.findViewById(R.id.tvEstadoFatura);
        }

        public void update(Fatura fatura){
            tvId.setText(fatura.getId());
            tvValor.setText(fatura.getValorFatura());
            tvEstado.setText(fatura.getEstado());
        }
    }
}
