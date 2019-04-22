package ulacit.ed.appregistroincidencias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ulacit.ed.appregistroincidencias.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Se crean los scripts
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(Utilidades.CREAR_TABLA_USUARI0); // Crea tabla
    }

    //Se refrescan los scripts
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
