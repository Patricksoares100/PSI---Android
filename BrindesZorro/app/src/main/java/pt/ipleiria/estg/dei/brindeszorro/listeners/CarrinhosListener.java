package pt.ipleiria.estg.dei.brindeszorro.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Carrinho;

public interface CarrinhosListener {
    void onRefreshListaCarrinhos(ArrayList<Carrinho> carrinhos);
}
