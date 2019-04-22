package ulacit.ed.appregistroincidencias;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import ulacit.ed.appregistroincidencias.utilidades.Utilidades;

import java.util.regex.Pattern;


public class RegistroUsuarioActivity extends AppCompatActivity {
    private static final Pattern PSWD_Pattern = Pattern.compile("^" +
            //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

    // Get the widgets reference from XML layout
    private EditText txtCedula, txtNombre, txtProvincia, txtEmail ;
    private EditText txtCedula, txtNombre, txtProvincia, txtEmail, txtPassword, txtPassword2;
    Button btn;
    Button btnRegistrar;
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
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword2 = (EditText) findViewById(R.id.txtPassword2);
        rdSex = (RadioGroup) findViewById(R.id.radioSex);
        Button btn= (Button) findViewById(R.id.reset);
        Button btnRegistrar =(Button)findViewById(R.id.registrar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }


    }


    });
}

    private boolean validarCorreo(){
        String emailInput = txtEmail.getEditableText().toString().trim();

        if(emailInput.isEmpty()){
            txtEmail.setError("Campo no puede estar vacio");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText()).matches()){
            txtEmail.setError("Ingrese un correo valido");
            return false;
        }
        else{
            txtEmail.setError(null);
            return true;
        }

    }

    private boolean validarContrasena(){
        String passwordInput = txtPassword.getEditableText().toString().trim();

        if(passwordInput.isEmpty()){
            txtPassword.setError("Campo no puede estar vacio");
            return false;
        }else if(!PSWD_Pattern.matcher(txtEmail.getText()).matches()){
            txtPassword.setError("La contraseña debe incluir por lo menos 1 digito, 1 leta mayuscula y por lo menos 4 caracteres");
            return false;
        }
        else{
            txtPassword.setError(null);
            return true;
        }

    }

    private boolean validarContrasenas(){
        String passwordInput = txtPassword.getEditableText().toString().trim();
        String passwordInput2 = txtPassword2.getEditableText().toString().trim();

        if(passwordInput.matches(passwordInput2)){
            txtPassword.setError(null);
            txtPassword2.setError(null);
            return true;
        }else{
            txtPassword.setError("Las contraseñas no coinciden");
            txtPassword2.setError("Las contraseñas no coinciden");
            return false;
        }
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
