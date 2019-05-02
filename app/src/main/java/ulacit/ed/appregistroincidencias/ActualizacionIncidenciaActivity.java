package ulacit.ed.appregistroincidencias;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class ActualizacionIncidenciaActivity extends AppCompatActivity {

    private ImageView imagen1;
    private EditText et1;
    Spinner opciones;

    ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this,"LugarIncidencia",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagen1=(ImageView)findViewById(R.id.imageView);
        et1=(EditText)findViewById(R.id.editText);

        opciones = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.incidencias,android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

    }

    public void tomarFoto(View v) {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = new File(getExternalFilesDir(null), et1.getText().toString());
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
        startActivity(intento1);
    }

    public void recuperarFoto(View v) {
        Bitmap bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null)+"/"+et1.getText().toString());
        imagen1.setImageBitmap(bitmap1);
    }

    public void ver(View v) {
        Intent intento1=new Intent(this,ImagenesIncidencias.class);
        startActivity(intento1);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ActualizarIncidencia(View v){

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String incidencia = spinner.getSelectedItem().toString();


        admin.ActualizarDataLugar(incidencia);

        Toast.makeText(this,"Incidencia Actualizada!",Toast.LENGTH_LONG).show();

    }
}
