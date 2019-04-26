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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import ulacit.ed.appregistroincidencias.utilidades.Utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class RegistroUsuarioActivity extends AppCompatActivity {

    private static final Pattern PSWD_Pattern =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this,"Usuarios",null,1);




    // Get the widgets reference from XML layout
    private EditText txtCedula, txtNombre,txtApellido1, txtApellido2, txtProvincia, txtEmail, txtPassword, txtPassword2;



    private String cedulaToString, nombreToString,apellido1ToString, apellido2ToString, provinciaToString, emailToString, passwordToString, rdSexToString;

    Button btn;
    Button btnRegistrar;
    RadioGroup rdSex;
    RadioButton rdSexBtn;
    Date currentDate = new Date();
    String currentDateToString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido1 = (EditText) findViewById(R.id.txtApellido1);
        txtApellido2 = (EditText) findViewById(R.id.txtApellido2);
        txtProvincia = (EditText) findViewById(R.id.txtProvincia);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword2 = (EditText) findViewById(R.id.txtPassword2);
        rdSex = (RadioGroup) findViewById(R.id.radioSex);
        Button btn= (Button) findViewById(R.id.reset);
        Button btnRegistrar =(Button)findViewById(R.id.registrar);

    }


    public void registrarUsuario(View view) {
            if(!validarRequeridos() | !validarCorreo() | !validarContrasenas() | !validarContrasena()){
                return;
            }

        cedulaToString = txtCedula.getText().toString();
        nombreToString = txtNombre.getText().toString();
        apellido1ToString = txtApellido1.getText().toString();
        apellido2ToString = txtApellido1.getText().toString();
        provinciaToString = txtProvincia.getText().toString();
        emailToString = txtEmail.getText().toString();
        passwordToString = txtPassword.getText().toString();
        rdSexBtn = (RadioButton) findViewById(rdSex.getCheckedRadioButtonId());
        rdSexToString = rdSexBtn.getText().toString();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        currentDateToString = format.format(currentDate);




        admin.insertDataUsuarios(Integer.parseInt(cedulaToString),nombreToString,provinciaToString,emailToString,passwordToString,rdSexToString,currentDateToString);


        Toast.makeText(this,"Usuario Registrado Exitosamente!",Toast.LENGTH_LONG).show();

    }


    private boolean validarCorreo(){
        String emailInput = txtEmail.getEditableText().toString().trim();

        if(emailInput.isEmpty()){
            txtEmail.setError("Este campo no puede estar vacio");
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
            txtPassword.setError("Este campo no puede estar vacio");
            return false;
        }else if(!PSWD_Pattern.matcher(passwordInput).matches()){
            txtPassword.setError("La contraseña debe incluir por lo menos 1 numero, 1 letra mayuscula y por lo menos 4 caracteres");
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
        if(passwordInput.isEmpty() && passwordInput2.isEmpty()){
            txtPassword.setError("Este campo no puede estar vacio");
            txtPassword2.setError("Este campo no puede estar vacio");
            return false;
        }

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

    private boolean validarRequeridos(){
        String cedulaInput = txtCedula.getEditableText().toString().trim();
        String nombreInput = txtNombre.getEditableText().toString().trim();
        String provinciaInput = txtProvincia.getEditableText().toString().trim();
        String apellidoInput = txtApellido1.getEditableText().toString().trim();
        String apellido2Input = txtApellido2.getEditableText().toString().trim();

        if(cedulaInput.isEmpty() || nombreInput.isEmpty() || provinciaInput.isEmpty() || apellidoInput.isEmpty() || apellido2Input.isEmpty()){
            txtCedula.setError("Este campo no puede estar vacio");
            txtNombre.setError("Este campo no puede estar vacio");
            txtApellido1.setError("Este campo no puede estar vacio");
            txtApellido2.setError("Este campo no puede estar vacio");
            txtProvincia.setError("Este campo no puede estar vacio");
            return false;
        }else{
            txtCedula.setError(null);
            txtNombre.setError(null);
            txtApellido1.setError(null);
            txtApellido2.setError(null);
            txtProvincia.setError(null);
            return true;
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
