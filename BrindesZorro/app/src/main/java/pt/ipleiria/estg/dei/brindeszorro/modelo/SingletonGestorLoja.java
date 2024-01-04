package pt.ipleiria.estg.dei.brindeszorro.modelo;

import android.content.Context;

import java.util.ArrayList;

public class SingletonGestorLoja {

    private ArrayList<Fatura> faturas;
    private ArrayList<Artigo> artigos;
    private static SingletonGestorLoja instance = null;
    private ArtigoBDHelper artigoBDHelper = null;

    public static synchronized SingletonGestorLoja getInstance(Context context){
        //corrigir todas as chamadas ao método getInstance() para que passe a receber o contexto
        //adequado - ficha 08 -2.1 - adicionado getContext() dentro do getInstance
        if (instance == null)
            instance = new SingletonGestorLoja(context);
        return instance;
    }

    private SingletonGestorLoja(Context context) {
        artigos = new ArrayList<>();
        artigoBDHelper = new ArtigoBDHelper(context);

        //gerarDadosFaturas();
        //gerarDadosArtigos();
    }

    /*private void gerarDadosFaturas(){
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
    }*/

    public ArrayList<Fatura> getFaturas(){ return new ArrayList<>(faturas);}
    public ArrayList<Artigo> getArtigosBD(){
        artigos = artigoBDHelper.getAllArtigosBD();
        return  new ArrayList<>(artigos);
    }

}
