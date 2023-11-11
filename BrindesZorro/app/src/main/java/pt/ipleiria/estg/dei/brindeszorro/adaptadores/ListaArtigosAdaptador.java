package pt.ipleiria.estg.dei.brindeszorro.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
