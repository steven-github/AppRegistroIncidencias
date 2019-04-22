package ulacit.ed.appregistroincidencias;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class graphSQLite extends SQLiteOpenHelper {

    private Context con;

    public graphSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table Usuarios(id int primary key, username STRING, fechaDeCreacion TEXT) ");

        db.execSQL("Create table Reportes(id int primary key, id_usuario int, area STRING, fechaDeCreacion TEXT) ");

        Toast.makeText(con,"Tabla Creada",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDataUsuarios(int id, String username, String fechaDeCreacion){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("username",username);
        contentValues.put("fechaDeCreacion",fechaDeCreacion);
        database.insert("Usuarios",null,contentValues);
        database.close();
        //Toast.makeText(con,"Data Insertada",Toast.LENGTH_LONG).show();
    }

    public void insertDataReportes(int id, int id_usuario, String area, String fechaDeCreacion){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("id_usuario",id_usuario);
        contentValues.put("area",area);
        contentValues.put("fechaDeCreacion",fechaDeCreacion);
        database.insert("Reportes",null,contentValues);
        database.close();
        //Toast.makeText(con,"Data Insertada",Toast.LENGTH_LONG).show();
    }
}
