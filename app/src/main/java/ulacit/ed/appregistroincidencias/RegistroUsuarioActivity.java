package ulacit.ed.appregistroincidencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RegistroUsuarioActivity extends AppCompatActivity {

    // Get the widgets reference from XML layout
    private EditText txtCedula, txtNombre, txtProvincia, txtEmail;
    Button btn;
    RadioGroup rdSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtProvincia = (EditText) findViewById(R.id.txtProvincia);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        rdSex = (RadioGroup) findViewById(R.id.radioSex);
        btn = (Button) findViewById(R.id.reset);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the second EditText
                txtCedula.getText().clear();
                txtNombre.getText().clear();
                txtProvincia.getText().clear();
                txtEmail.getText().clear();
                rdSex.clearCheck();
            }
        });

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
