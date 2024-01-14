package pt.ipleiria.estg.dei.brindeszorro.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;

public interface FaturasListener {
    void onRefreshListaFaturas(ArrayList<Fatura> faturas);
}
