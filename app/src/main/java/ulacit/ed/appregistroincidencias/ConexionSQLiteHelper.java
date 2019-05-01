package ulacit.ed.appregistroincidencias;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import ulacit.ed.appregistroincidencias.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    private Context con;

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        con=context;
    }

    //Se crean los scripts
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Usuarios(id int primary key, nombre STRING, provincia STRING, email STRING, password STRING, sexo STRING, fechaDeCreacion TEXT) ");

        db.execSQL("Create table Reportes(id int primary key, id_usuario int, area STRING, fechaDeCreacion TEXT) ");

        db.execSQL("Create table LugarIncidencia(lugar STRING)");

        Toast.makeText(con,"Tabla Creada",Toast.LENGTH_LONG).show();
    }

    //Se refrescan los scripts
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);
    }

    public void insertDataUsuarios(int id, String nombre,String provincia, String email, String password, String sexo, String fechaDeCreacion){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("nombre",nombre);
        contentValues.put("provincia",provincia);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("sexo",sexo);
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

    public void insertarDataLugar(String lugar){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lugar",lugar);
        database.insert("LugarIncidencia",null,contentValues);
        database.close();
    }

    public void ActualizarDataLugar(String lugar){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lugar",lugar);
        database.insert("LugarIncidencia",null,contentValues);
        database.close();
    }
}
