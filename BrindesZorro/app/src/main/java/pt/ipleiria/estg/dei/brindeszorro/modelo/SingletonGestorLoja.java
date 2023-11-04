package pt.ipleiria.estg.dei.brindeszorro.modelo;

import java.util.ArrayList;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;

    private static SingletonGestorLoja instance = null;

    public static synchronized SingletonGestorLoja getInstance(){
        if (instance == null)
            instance = new SingletonGestorLoja();
        return instance;

    }

    private SingletonGestorLoja() {
        gerarDadosFaturas();
    }

    private void gerarDadosFaturas(){
        faturas = new ArrayList<>();

        faturas.add(new Fatura(1,35,"paga"));
        faturas.add(new Fatura(2,40,"paga"));
        faturas.add(new Fatura(3,50,"paga"));
        faturas.add(new Fatura(4,60,"paga"));

    }

    public ArrayList<Fatura> getFaturas(){ return new ArrayList<>(faturas);}

}
