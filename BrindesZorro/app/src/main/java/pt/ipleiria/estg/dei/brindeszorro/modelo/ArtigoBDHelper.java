package pt.ipleiria.estg.dei.brindeszorro.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ArtigoBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbArtigos";
    private static final String TABLE_NAME = "artigos";
    private static final int DB_VERSION = 1;
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String DESCRICAO = "descricao";
    private static final String REFERENCIA = "referencia";
    private static final String PRECO = "preco";
    private static final String STOCK_ATUAL = "stock_atual";
    private static final String IVA_ID = "iva_id";
    private static final String FORNECEDOR_ID = "fornecedor_id";
    private static final String CATEGORIA_ID = "categoria_id";
    private static final String PERFIL_ID = "perfil_id";

    private final SQLiteDatabase db;


    public ArtigoBDHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
        //Para definir permissões de leitura e escrita na base de dados, teremos de utilizar o método
        //getWritableDatabase(); ficha 08
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //oncreate quando nao existe na base de dados
        // permitir a atualização da base de dados; ficha 08 1.5
        String createArtigoTable =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOME + " TEXT NOT NULL, " +
                        DESCRICAO + " TEXT NOT NULL, " +
                        REFERENCIA + " TEXT NOT NULL, " +
                        PRECO + " INTEGER NOT NULL, " +
                        STOCK_ATUAL + " INTEGER NOT NULL," +
                        IVA_ID + " INTEGER NOT NULL, " +
                        FORNECEDOR_ID + " INTEGER NOT NULL, " +
                        CATEGORIA_ID + " INTEGER NOT NULL, " +
                        PERFIL_ID + " INTEGER NOT NULL " +
                        ");";
        db.execSQL(createArtigoTable);  // Executa o comando SQL para criar a tabela
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // permitir a atualização da base de dados; ficha 08 1.6
        //quando existe manda atualizar
        //manda abaixo a tabela para criar uma nova de raiz
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public ArrayList<Artigo>getAllArtigosBD(){
        ArrayList<Artigo> artigos = new ArrayList<>();
        //Cursor é uma interface que fornece um mecanismo para percorrer e consultar os
        //resultados de uma consulta à base de dados
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{
                        ID,
                        NOME,
                        DESCRICAO,
                        REFERENCIA,
                        PRECO,
                        STOCK_ATUAL,
                        IVA_ID,
                        FORNECEDOR_ID,
                        CATEGORIA_ID,
                        PERFIL_ID},
                null, null, null, null, null); // questionar o porquê destes 5 null?

        if (cursor.moveToFirst()) {
            do {
                Artigo auxArtigo = new Artigo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8));

                artigos.add(auxArtigo);
            } while (cursor.moveToNext());
        } // quando os campos estão todos preenchidos dá return da lista artigos
        return artigos;
    }



}
