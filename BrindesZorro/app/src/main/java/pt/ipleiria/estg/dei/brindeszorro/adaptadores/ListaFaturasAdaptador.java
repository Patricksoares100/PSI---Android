package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.R;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.SingletonGestorLoja;
import pt.ipleiria.estg.dei.brindeszorro.utils.Public;

public class ListaFaturasAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Fatura> faturas;

    public ListaFaturasAdaptador(Context context, ArrayList<Fatura> faturas) {
        this.context = context;
        this.faturas = faturas;
    }

    public ListaFaturasAdaptador() {

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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // autorização a variacvel
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_faturas, null);
        }
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista ==null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

       viewHolderLista.update(faturas.get(position));

        Button buttonFaturaPagar = convertView.findViewById(R.id.ButtonFaturaPagar);
        buttonFaturaPagar.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Public.DADOS_USER, Context.MODE_PRIVATE);
            int idFatura = faturas.get(position).getId();
            System.out.println("--- o id da fatura é" + idFatura);
            SingletonGestorLoja.getInstance(context).pagarFatura(context, sharedPreferences.getString(Public.TOKEN, "TOKEN"), idFatura);
        });

        /*Fatura fatura = faturas.get(position);
        viewHolderLista.tvData.setText(String.format(fatura.getData()));
        viewHolderLista.tvValor.setText(String.format("%.2f", fatura.getValorFatura()));
        viewHolderLista.tvEstado.setText(String.valueOf(fatura.getEstado()));*/

        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvData, tvValor, tvEstado;
        private Button buttonFaturaPagar;

        public ViewHolderLista(View view) {
            tvData = view.findViewById(R.id.tvDataEmissaoFatura);
            tvValor = view.findViewById(R.id.tvValorFatura);
            tvEstado = view.findViewById(R.id.tvEstadoFatura);
            buttonFaturaPagar = view.findViewById(R.id.ButtonFaturaPagar);
        }

        public void update(Fatura fatura) {
            tvData.setText(""+fatura.getData());
            tvValor.setText(""+fatura.getValorFatura());
            tvEstado.setText(""+fatura.getEstado());
            if ("Paga".equals(fatura.getEstado())) {  // tentei com == mas dava erro
                buttonFaturaPagar.setVisibility(View.GONE);
            } else {
                buttonFaturaPagar.setVisibility(View.VISIBLE);
            }
        }

        }
    }
