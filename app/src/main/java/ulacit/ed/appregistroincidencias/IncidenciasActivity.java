package ulacit.ed.appregistroincidencias;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IncidenciasActivity extends AppCompatActivity {
    private EditText agenda;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        agenda =(EditText)findViewById(R.id.txt_agenda);
        guardar = (Button)findViewById(R.id.btn_guardar);

        String documentos[] = fileList();
        if(ExisteDocumento(documentos,"agenda.txt")){
            try {

                InputStreamReader documento = new InputStreamReader(openFileInput("agenda.txt"));
                BufferedReader br = new BufferedReader(documento);
                String texto = br.readLine();
                String agendacompleta ="";

                while (texto != null){
                    agendacompleta = agendacompleta + texto + "\n";
                    texto = br.readLine();
                }
                br.close();
                documento.close();;
                agenda.setText(agendacompleta);
            }catch (IOException e){}

        }
    }

    private boolean ExisteDocumento(String[] documentos, String nombreDocumento) {
        for(int i =0; i < documentos.length; i++ )
            if(nombreDocumento.equals(documentos[i]))
                return true;

        return false;
    }

    public void Guardar(View v){
        try{
            OutputStreamWriter documento = new OutputStreamWriter(openFileOutput("agenda.txt", Activity.MODE_PRIVATE));
            documento.write(agenda.getText().toString());
            documento.flush();
            documento.close();
        }catch (IOException e){

        }
        Toast msj = Toast.makeText(this,"Registrado exitosamente", Toast.LENGTH_SHORT);
        msj.show();


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
}
