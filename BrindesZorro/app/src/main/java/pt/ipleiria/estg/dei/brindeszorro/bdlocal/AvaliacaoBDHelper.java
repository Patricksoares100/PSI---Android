package pt.ipleiria.estg.dei.brindeszorro.bdlocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;

public class AvaliacaoBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbAvaliacaos";
    private static final String TABLE_NAME = "avaliacaos";
    private static final int DB_VERSION = 1;
    private static final String ID = "id";
    private static final String COMENTARIO = "comentario";
    private static final String CLASSIFICACAO = "classificacao";
    private static final String ARTIGO_ID = "artigo_id";
    private static final String PERFIL_ID = "perfil_id";
    private final SQLiteDatabase db;    // Alinea 1.3 Ficha 8 Books - criação de uma instância da classe SQLiteDatabase

    // Alinea 1.4 Ficha 8 Books - Construtor implementado com o método getWritableDatabase()
    public AvaliacaoBDHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
        inserirAvaliacaoExemplo();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //oncreate quando nao existe na base de dados
        // permitir a atualização da base de dados; ficha 08 1.5
        String createAvaliacaoTable =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COMENTARIO + " TEXT NOT NULL, " +
                        CLASSIFICACAO + " INTEGER NOT NULL, " +
                        ARTIGO_ID + " INTEGER NOT NULL, " +
                        PERFIL_ID + " INTEGER NOT NULL " +
                        ");";
        db.execSQL(createAvaliacaoTable);  // Executa o comando SQL para criar a tabela
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void adicionarAvaliacaoBD(Avaliacao a) {
        ContentValues values = new ContentValues();
        values.put(ID, a.getId());
        values.put(COMENTARIO, a.getComentario());
        values.put(CLASSIFICACAO, a.getClassificacao());
        values.put(ARTIGO_ID, a.getArtigoId());
        values.put(PERFIL_ID, a.getPerfilId());

        this.db.insert(TABLE_NAME, null, values);
    }

    public boolean editarAvaliacaoBD(Avaliacao a) {
        ContentValues values = new ContentValues();
        values.put(ID, a.getId());
        values.put(COMENTARIO, a.getComentario());
        values.put(CLASSIFICACAO, a.getClassificacao());
        values.put(ARTIGO_ID, a.getArtigoId());
        values.put(PERFIL_ID, a.getPerfilId());

        return this.db.update(TABLE_NAME, values, ID + "= ?", new String[]{"" + a.getId()}) > 0;
    }

    public boolean removerAvaliacaoBD(int id) {
        return this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id}) == 1;
    }

    public ArrayList<Avaliacao> getAllAvaliacaosBD() {
        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();

        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, COMENTARIO, CLASSIFICACAO, ARTIGO_ID, PERFIL_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // PRESUMO QUE A ORDEM DESTES columnIndex TEM A HAVER COM A ORDEM DOS ATRIBUTOS QUE ESTÁ NO CONSTRUTOR DO MODELO
                Avaliacao auxAvaliacao = new Avaliacao(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4));

                avaliacaos.add(auxAvaliacao);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return avaliacaos;
    }

    public void removerAllAvaliacaosBD(){
        db.delete(TABLE_NAME, null, null);
    }

    public void inserirAvaliacaoExemplo() {
        limparBaseDeDados(); //FAZ COM QUE AO CRIAR LIMPE PRIMEIRO
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COMENTARIO, "Avaliacao Exemplo");
        values.put(CLASSIFICACAO, 3);
        values.put(ARTIGO_ID, 1);
        values.put(PERFIL_ID, 1);

        db.insert(TABLE_NAME, null, values);
    }

    public void limparBaseDeDados() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
