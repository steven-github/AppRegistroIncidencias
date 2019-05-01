package ulacit.ed.appregistroincidencias;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

public class Login extends AppCompatActivity {

    ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this,"Usuarios",null,1);
    EditText username, password;
    String usernameToString, passwordToString;
    public static final String sessionPrefs = "sessionPrefs" ;
    public static final String id = "idKey";
    public static final String nombre = "nameKey";
    public static final String email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(sessionPrefs, Context.MODE_PRIVATE);

        SQLiteDatabase base = admin.getWritableDatabase();
        Cursor fila = base.rawQuery("SELECT COUNT(id) , date(fechaDeCreacion) FROM Usuarios GROUP BY date(fechaDeCreacion)",null);
        if(fila.moveToFirst()){
            //Toast.makeText(this,"Nada que insertar", Toast.LENGTH_SHORT).show();
        }
        else{
            //Insert de Usuarios
            admin.insertDataUsuarios(1,"Asdasda","Cartago","Asdasda@gmail.com","HolaMundo","Femenino","2018-04-21 08:14:17");
            //Toast.makeText(this,"Data Insertada",Toast.LENGTH_LONG).show();
        }

    }

    public void login(View view){

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        usernameToString = username.getText().toString();
        passwordToString = password.getText().toString();


        SQLiteDatabase base = admin.getWritableDatabase();
        Cursor fila = base.rawQuery("SELECT id,nombre,email FROM Usuarios WHERE email = ? AND password = ?",new String[] {usernameToString, passwordToString});

        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

        if(fila.moveToFirst()){
            Toast.makeText(this,"Credenciales Autorizadas", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt(id, fila.getInt(0));
            editor.putString(nombre,fila.getString(1));
            editor.putString(email,fila.getString(2));
            editor.commit();

            Intent mainRedirect = new Intent(this,MainActivity.class);
            startActivity(mainRedirect);
        }
        else {
            username.setError("REQUERIDO", icon);
            password.setError("REQUERIDO", icon);
            Toast.makeText(this,"USUARIO Y/O CONTRASEÑA INVÁLIDAS", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(View view){
        Intent registerRedirect = new Intent(this,RegistroUsuarioActivity.class);
        startActivity(registerRedirect);
    }

}
