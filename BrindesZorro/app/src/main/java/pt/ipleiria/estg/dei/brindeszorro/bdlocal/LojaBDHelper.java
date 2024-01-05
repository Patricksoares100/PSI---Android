package pt.ipleiria.estg.dei.brindeszorro.bdlocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Artigo;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Favorito;

public class LojaBDHelper extends SQLiteOpenHelper {

    //region # DECLARADOS O NOME DA BASE DE DADOS E DAS TABELAS #
    private static final String DB_NAME = "bdbrindeszorro";
    private static final String TABLE_NAME_AVALIACAOS = "avaliacaos";
    private static final String TABLE_NAME_ARTIGOS = "artigos";
    private static final String TABLE_NAME_FATURAS = "faturas";
    private static final String TABLE_NAME_FAVORITOS = "favoritos";
    private static final int DB_VERSION = 1;
    //endregion

    // region # ATRIBUTOS DAS TABELAS #
    private static final String ID = "id";
    private static final String COMENTARIO = "comentario";
    private static final String CLASSIFICACAO = "classificacao";
    private static final String ARTIGO_ID = "artigo_id";
    private static final String PERFIL_ID = "perfil_id";
    private static final String NOME = "nome";
    private static final String DESCRICAO = "descricao";
    private static final String REFERENCIA = "referencia";
    private static final String PRECO = "preco";
    private static final String STOCK_ATUAL = "stock_atual";
    private static final String IVA_ID = "iva_id";
    private static final String FORNECEDOR_ID = "fornecedor_id";
    private static final String CATEGORIA_ID = "categoria_id";
    private static final String DATA = "data";
    private static final String VALOR_FATURA = "valorFatura";
    private static final String ESTADO = "estado";
    private final SQLiteDatabase db;    // Alinea 1.3 Ficha 8 Books - criação de uma instância da classe SQLiteDatabase
    // endregion

    // CONSTRUTOR
    public LojaBDHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // Para definir permissões de leitura e escrita na base de dados, teremos de utilizar o método getWritableDatabase() - ficha 08
        this.db = this.getWritableDatabase();
        inserirAvaliacaoExemplo(); //APAGAR NO FIM
        inserirArtigoExemplo();
        inserirFaturaExemplo();
        inserirFavoritoExemplo();
    }

    // region # CREATE TABLES BD #
    @Override
    public void onCreate(SQLiteDatabase db) {
        // CRIAR TABELA NA BASE DE DADOS
        String createAvaliacaoTable =
                "CREATE TABLE " + TABLE_NAME_AVALIACAOS +
                        "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COMENTARIO + " TEXT NOT NULL, " +
                        CLASSIFICACAO + " INTEGER NOT NULL, " +
                        ARTIGO_ID + " INTEGER NOT NULL, " +
                        PERFIL_ID + " INTEGER NOT NULL " +
                        ");";
        db.execSQL(createAvaliacaoTable);  // EXECUTA O COMANDO SQL PARA CRIAR A TABELA

        String createArtigoTable =
                "CREATE TABLE " + TABLE_NAME_ARTIGOS +
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
        db.execSQL(createArtigoTable);

        String createFaturaTable =
                "CREATE TABLE " + TABLE_NAME_FATURAS +
                        "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DATA + " TEXT NOT NULL, " +
                        VALOR_FATURA + " DOUBLE NOT NULL, " +
                        ESTADO + " TEXT NOT NULL, " +
                        PERFIL_ID + " INTEGER NOT NULL " +
                        ");";
        db.execSQL(createFaturaTable);

        String createFavoritoTable =
                "CREATE TABLE " + TABLE_NAME_FAVORITOS +
                        "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ARTIGO_ID + " INTEGER NOT NULL, " +
                        PERFIL_ID + " INTEGER NOT NULL " +
                        ");";
        db.execSQL(createFavoritoTable);

    }
    // endregion

    //region # UPDATE TABLES BD #
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // PERMITE A ATUALIZAÇAO DA TABELA NA BASE DE DADOS
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_AVALIACAOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ARTIGOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FATURAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAVORITOS);
        this.onCreate(db);
    }
    //endregion

    //region # MÉTODOS CRUD DAS AVALIAÇÕES #
    public void adicionarAvaliacaoBD(Avaliacao a) {
        ContentValues values = new ContentValues();
        values.put(ID, a.getId());
        values.put(COMENTARIO, a.getComentario());
        values.put(CLASSIFICACAO, a.getClassificacao());
        values.put(ARTIGO_ID, a.getArtigoId());
        values.put(PERFIL_ID, a.getPerfilId());

        this.db.insert(TABLE_NAME_AVALIACAOS, null, values);
    }

    public boolean editarAvaliacaoBD(Avaliacao a) {
        ContentValues values = new ContentValues();
        values.put(ID, a.getId());
        values.put(COMENTARIO, a.getComentario());
        values.put(CLASSIFICACAO, a.getClassificacao());
        values.put(ARTIGO_ID, a.getArtigoId());
        values.put(PERFIL_ID, a.getPerfilId());

        return this.db.update(TABLE_NAME_AVALIACAOS, values, ID + "= ?", new String[]{"" + a.getId()}) > 0;
    }

    public boolean removerAvaliacaoBD(int id) {
        return this.db.delete(TABLE_NAME_AVALIACAOS, ID + " = ?", new String[]{"" + id}) == 1;
    }

    public ArrayList<Avaliacao> getAllAvaliacaosBD() {
        ArrayList<Avaliacao> avaliacaos = new ArrayList<>();

        Cursor cursor = this.db.query(TABLE_NAME_AVALIACAOS, new String[]{ID, COMENTARIO, CLASSIFICACAO, ARTIGO_ID, PERFIL_ID},
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
        db.delete(TABLE_NAME_AVALIACAOS, null, null);
    }
    //endregion

    //region # MÉTODOS CRUD DOS ARTIGOS #

    public void adicionarArtigoBD(Artigo a) {
        ContentValues values = new ContentValues();
        values.put(ID, a.getId());
        values.put(NOME, a.getNome());
        values.put(DESCRICAO, a.getDescricao());
        values.put(REFERENCIA, a.getReferencia());
        values.put(PRECO, a.getReferencia());
        values.put(IVA_ID,a.getIva_id());
        values.put(FORNECEDOR_ID, a.getFornecedor_id());
        values.put(CATEGORIA_ID, a.getCategoria_id());
        values.put(PERFIL_ID,a.getPerfil_id());

        this.db.insert(TABLE_NAME_ARTIGOS, null, values);
    }

    public boolean editarArtigoBD(Artigo a) {
        ContentValues values = new ContentValues();
        values.put(ID, a.getId());
        values.put(NOME, a.getNome());
        values.put(DESCRICAO, a.getDescricao());
        values.put(REFERENCIA, a.getReferencia());
        values.put(PRECO, a.getReferencia());
        values.put(IVA_ID,a.getIva_id());
        values.put(FORNECEDOR_ID, a.getFornecedor_id());
        values.put(CATEGORIA_ID, a.getCategoria_id());
        values.put(PERFIL_ID,a.getPerfil_id());

        return this.db.update(TABLE_NAME_ARTIGOS, values, ID + "= ?", new String[]{"" + a.getId()}) > 0;
    }

    public boolean removerArtigoBD(int id) {
        return this.db.delete(TABLE_NAME_ARTIGOS, ID + " = ?", new String[]{"" + id}) == 1;
    }

    public ArrayList<Artigo>getAllArtigosBD(){
        ArrayList<Artigo> artigos = new ArrayList<>();
        //Cursor é uma interface que fornece um mecanismo para percorrer e consultar os
        //resultados de uma consulta à base de dados
        Cursor cursor = this.db.query(TABLE_NAME_ARTIGOS, new String[]{
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
                        cursor.getDouble(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getInt(9));

                artigos.add(auxArtigo);
            } while (cursor.moveToNext());
        } // quando os campos estão todos preenchidos dá return da lista artigos
        return artigos;
    }

    public void removerAllArtigosBD(){
        db.delete(TABLE_NAME_AVALIACAOS, null, null);
    }

    //endregion

    //region # MÉTODOS CRUD DAS FATURAS #
    public void adicionarFaturaBD(Fatura f) {
        ContentValues values = new ContentValues();
        values.put(ID, f.getId());
        values.put(DATA, f.getData());
        values.put(VALOR_FATURA, f.getValorFatura());
        values.put(ESTADO, f.getEstado().toString());
        values.put(PERFIL_ID, f.getPerfil_id());

        this.db.insert(TABLE_NAME_FATURAS, null, values);
    }

    public boolean editarFaturaBD(Fatura f) {
        ContentValues values = new ContentValues();
        values.put(ID, f.getId());
        values.put(DATA, f.getData());
        values.put(VALOR_FATURA, f.getValorFatura());
        values.put(ESTADO, f.getEstado().toString());
        values.put(PERFIL_ID, f.getPerfil_id());

        return this.db.update(TABLE_NAME_FATURAS, values, ID + "= ?", new String[]{"" + f.getId()}) > 0;
    }

    public boolean removerFaturaBD(int id) {
        return this.db.delete(TABLE_NAME_FATURAS, ID + " = ?", new String[]{"" + id}) == 1;
    }

    public ArrayList<Fatura> getAllFaturasBD() {
        ArrayList<Fatura> faturas = new ArrayList<>();

        Cursor cursor = this.db.query(TABLE_NAME_FATURAS, new String[]{ID, DATA, VALOR_FATURA, ESTADO, PERFIL_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // PRESUMO QUE A ORDEM DESTES columnIndex TEM A HAVER COM A ORDEM DOS ATRIBUTOS QUE ESTÁ NO CONSTRUTOR DO MODELO
                Fatura auxFatura = new Fatura(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getInt(4));

                faturas.add(auxFatura);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return faturas;
    }

    public void removerAllFaturasBD(){
        db.delete(TABLE_NAME_FATURAS, null, null);
    }
    //endregion

    //region # MÉTODOS CRUD DOS FAVORITOS #

    public void adicionarFavoritoBD(Favorito f) {

        ContentValues values = new ContentValues();
        values.put(ID, f.getId());
        values.put(ARTIGO_ID, f.getArtigoId());
        values.put(PERFIL_ID, f.getPerfilId());

        this.db.insert(TABLE_NAME_FATURAS, null, values);
    }

    public boolean removerFavoritoBD(int id) {
        return this.db.delete(TABLE_NAME_FAVORITOS, ID + " = ?", new String[]{"" + id}) == 1;
    }

    public ArrayList<Favorito>getAllFavoritosBD(){
        ArrayList<Favorito> favoritos = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME_FAVORITOS, new String[]{
                        ID,
                        ARTIGO_ID,
                        PERFIL_ID},
                null, null, null, null, null); // questionar o porquê destes 5 null?

        if (cursor.moveToFirst()) {
            do {
                Favorito auxFavorito = new Favorito(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2));

                favoritos.add(auxFavorito);
            } while (cursor.moveToNext());
        } // quando os campos estão todos preenchidos dá return da lista artigos
        return favoritos;
    }

    public void removerAllFavoritos(){
        db.delete(TABLE_NAME_FAVORITOS, null, null);
    }
    //endregion

    //region # MÉTODOS DE TESTE (NO FIM APAGAR)#
    public void inserirAvaliacaoExemplo() {
        limparBaseDeDadosAvaliacaos(); //FAZ COM QUE AO CRIAR LIMPE PRIMEIRO
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COMENTARIO, "Avaliacao Exemplo");
        values.put(CLASSIFICACAO, 3);
        values.put(ARTIGO_ID, 1);
        values.put(PERFIL_ID, 1);

        db.insert(TABLE_NAME_AVALIACAOS, null, values);
    }

    public void inserirArtigoExemplo() {
        limparBaseDeDadosArtigos(); //FAZ COM QUE AO CRIAR LIMPE PRIMEIRO
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, "Artigo Exemplo");
        values.put(DESCRICAO, "Descrição do Artigo Exemplo");
        values.put(REFERENCIA, "#ART001");
        values.put(PRECO, 25);
        values.put(STOCK_ATUAL, 50);
        values.put(IVA_ID, 1);
        values.put(FORNECEDOR_ID, 1);
        values.put(CATEGORIA_ID, 1);
        values.put(PERFIL_ID, 1);

        db.insert(TABLE_NAME_ARTIGOS, null, values);
    }

    public void inserirFaturaExemplo() {
        limparBaseDeDadosFaturas(); //FAZ COM QUE AO CRIAR LIMPE PRIMEIRO
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATA, "2024-01-01 00:00:00");
        values.put(VALOR_FATURA, 10.50);
        values.put(ESTADO, "Emitida");
        values.put(PERFIL_ID, 1);

        db.insert(TABLE_NAME_FATURAS, null, values);
    }

    public void inserirFavoritoExemplo() { // a remover depois de estar
        limparBaseDeDadosFavoritos();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, 1);
        values.put(ARTIGO_ID, 2);
        values.put(PERFIL_ID, 3);

        db.insert(TABLE_NAME_FAVORITOS, null, values);
    }
    public void limparBaseDeDadosAvaliacaos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_AVALIACAOS);
    }

    public void limparBaseDeDadosArtigos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_ARTIGOS);
    }

    public void limparBaseDeDadosFaturas() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_FATURAS);
    }

    public void limparBaseDeDadosFavoritos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_FAVORITOS);
    }
    //endregion
}
