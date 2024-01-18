package pt.ipleiria.estg.dei.brindeszorro.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.LinhaFatura;

public interface LinhasFaturasListener {
    void onRefreshListaLinhasFaturas(ArrayList<LinhaFatura> linhaFaturas);
}
