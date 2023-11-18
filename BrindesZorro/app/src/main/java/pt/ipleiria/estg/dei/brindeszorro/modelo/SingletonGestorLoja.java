package pt.ipleiria.estg.dei.brindeszorro.modelo;

import java.util.ArrayList;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;
    private ArrayList<Artigo> artigos;

    private static SingletonGestorLoja instance = null;

    public static synchronized SingletonGestorLoja getInstance(){
        if (instance == null)
            instance = new SingletonGestorLoja();
        return instance;

    }

    private SingletonGestorLoja() {
        gerarDadosFaturas();
        gerarDadosArtigos();
    }

    private void gerarDadosFaturas(){
        faturas = new ArrayList<>();

        faturas.add(new Fatura(1,35,"paga"));
        faturas.add(new Fatura(2,40,"paga"));
        faturas.add(new Fatura(3,50,"não paga"));
        faturas.add(new Fatura(4,60,"paga"));

    }

    private void gerarDadosArtigos(){
        artigos = new ArrayList<>();

        artigos.add(new Artigo(1, 10.5, "Artigo 1","Descrição do artigo 1", "#ART0124" ));
        artigos.add(new Artigo(2, 27, "Artigo 2","Detalhes do artigo 2", "#ART1569" ));
    }

    public ArrayList<Fatura> getFaturas(){ return new ArrayList<>(faturas);}
    public ArrayList<Artigo> getArtigos(){ return  new ArrayList<>(artigos);}

}
