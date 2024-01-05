package pt.ipleiria.estg.dei.brindeszorro.bdlocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.Avaliacao;
import pt.ipleiria.estg.dei.brindeszorro.modelo.Fatura;

public class FaturaBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbFaturas";
    private static final String TABLE_NAME = "faturas";
    private static final int DB_VERSION = 1;
    private static final String ID = "id";
    private static final String DATA = "data";
    private static final String VALOR_FATURA = "valorFatura";
    private static final String ESTADO = "estado";
    private static final String PERFIL_ID = "perfil_id";
    private final SQLiteDatabase db;

    public FaturaBDHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
        inserirFaturaExemplo();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFaturaTable =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DATA + " TEXT NOT NULL, " +
                        VALOR_FATURA + " DOUBLE NOT NULL, " +
                        ESTADO + " TEXT NOT NULL, " +
                        PERFIL_ID + " INTEGER NOT NULL " +
                        ");";
        db.execSQL(createFaturaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void adicionarFaturaBD(Fatura f) {
        ContentValues values = new ContentValues();
        values.put(ID, f.getId());
        values.put(DATA, f.getData());
        values.put(VALOR_FATURA, f.getValorFatura());
        values.put(ESTADO, f.getEstado().toString());
        values.put(PERFIL_ID, f.getPerfil_id());

        this.db.insert(TABLE_NAME, null, values);
    }

    public boolean editarFaturaBD(Fatura f) {
        ContentValues values = new ContentValues();
        values.put(ID, f.getId());
        values.put(DATA, f.getData());
        values.put(VALOR_FATURA, f.getValorFatura());
        values.put(ESTADO, f.getEstado().toString());
        values.put(PERFIL_ID, f.getPerfil_id());

        return this.db.update(TABLE_NAME, values, ID + "= ?", new String[]{"" + f.getId()}) > 0;
    }

    public boolean removerFaturaBD(int id) {
        return this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id}) == 1;
    }

    public ArrayList<Fatura> getAllFaturasBD() {
        ArrayList<Fatura> faturas = new ArrayList<>();

        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, DATA, VALOR_FATURA, ESTADO, PERFIL_ID},
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
        db.delete(TABLE_NAME, null, null);
    }

    public void inserirFaturaExemplo() {
        limparBaseDeDados(); //FAZ COM QUE AO CRIAR LIMPE PRIMEIRO
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATA, "2024-01-01 00:00:00");
        values.put(VALOR_FATURA, 10.50);
        values.put(ESTADO, "Emitida");
        values.put(PERFIL_ID, 1);

        db.insert(TABLE_NAME, null, values);
    }

    public void limparBaseDeDados() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
