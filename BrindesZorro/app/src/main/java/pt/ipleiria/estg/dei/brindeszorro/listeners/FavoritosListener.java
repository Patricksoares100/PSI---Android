package pt.ipleiria.estg.dei.brindeszorro.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Favorito;

public interface FavoritosListener {
    void onRefreshListaFavoritos(ArrayList<Favorito> favoritos);
}
