package pt.ipleiria.estg.dei.brindeszorro.listeners;

import java.lang.reflect.Array;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;

public interface ArtigosListener {
    void onRefreshListaArtigos(ArrayList<Artigo> artigos);
}
