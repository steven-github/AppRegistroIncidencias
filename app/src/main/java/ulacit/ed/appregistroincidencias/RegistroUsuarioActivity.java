package ulacit.ed.appregistroincidencias;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
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

    //private static final Pattern Ced_Pattern = Pattern.compile("/^[1-9]-d{4}-d{4}$/");
    private static final Pattern Ced_Pattern =
            Pattern.compile("^"+
                    "(?=.*[1-9])" +
                    "(?=\\S+$)" +           //no white spaces
                    ".{9,}" +               //at least 4 characters
                    "$");

    private static final Pattern Tel_Pattern =
            Pattern.compile("^[+]?[0-9]{8,13}$");



    ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this,"Usuarios",null,1);




    // Get the widgets reference from XML layout


    private EditText txtCedula, txtNombre,txtApellido1,txtApellido2, txtProvincia, txtEmail,txtTelefono, txtPassword, txtPassword2;




    private String cedulaToString, nombreToString,apellido1ToString, apellido2ToString, provinciaToString, emailToString, passwordToString, rdSexToString;

    Button btn;
    Button btnRegistrar;
    RadioGroup rdSex;
    RadioButton rdSexBtn,rdMaleBtn;
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
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword2 = (EditText) findViewById(R.id.txtPassword2);
        rdSex = (RadioGroup) findViewById(R.id.radioSex);
        rdMaleBtn = (RadioButton) findViewById(R.id.radioMale);
        Button btn= (Button) findViewById(R.id.reset);
        Button btnRegistrar =(Button)findViewById(R.id.registrar);

    }


    public void registrarUsuario(View view) {

            if(!validarCorreo() | !validarContrasenas() | !validarContrasena() | !validarRequeridos()  | !validarCedula() | !validarTelefono() ){

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


        Toast.makeText(this,"USUARIO REGISTRADO EXITOSAMENTE!",Toast.LENGTH_LONG).show();

    }

    public void resetUusuario(View view){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtProvincia.setText("");
        txtEmail.setText("");
        rdSex.clearCheck();
        txtTelefono.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");

        txtCedula.setError(null);
        txtNombre.setError(null);
        txtApellido1.setError(null);
        txtApellido2.setError(null);
        txtProvincia.setError(null);
        txtEmail.setError(null);
        rdMaleBtn.setError(null);
        txtTelefono.setError(null);
        txtPassword.setError(null);
        txtPassword2.setError(null);
    }


    private boolean validarCorreo(){
        String emailInput = txtEmail.getEditableText().toString().trim();

        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

        if(emailInput.isEmpty()){
            txtEmail.setError("REQUERIDO", icon);
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText()).matches()){
            txtEmail.setError("EMAIL INVÁLIDO");
            return false;
        }
        else{
            txtEmail.setError(null);
            return true;
        }

    }

    private boolean validarCedula(){
        String CedulaInput = txtCedula.getEditableText().toString().trim();
        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

        if(CedulaInput.isEmpty()){
            txtCedula.setError("REQUERIDO", icon);
            return false;
        }else if(!Ced_Pattern.matcher(CedulaInput).matches()){
            txtCedula.setError("CÉDULA INVÁLIDA", icon);
            return false;
        }
        else{
            txtCedula.setError(null);
            return true;
        }
    }

    private boolean validarTelefono(){
        String TelefonoInput = txtTelefono.getEditableText().toString().trim();
        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

        if(TelefonoInput.isEmpty()){
            txtTelefono.setError("REQUERIDO", icon);
            return false;
        }else if(!Tel_Pattern.matcher(TelefonoInput).matches()){
            txtTelefono.setError("TELÉFONO INVÁLIDO", icon);
            return false;
        }
        else{
            txtTelefono.setError(null);
            return true;
        }
    }

    private boolean validarContrasena(){
        String passwordInput = txtPassword.getEditableText().toString().trim();
        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

        if(passwordInput.isEmpty()){
            txtPassword.setError("REQUERIDO", icon);
            return false;
        }else if(!PSWD_Pattern.matcher(passwordInput).matches()){
            txtPassword.setError("CONTRASEÑA DEBE INCLUIR AL MENOS 1 NÚMERO, 1 LETRA MAYÚSCULA Y 4 CARACTERES", icon);
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
        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

        if(passwordInput.isEmpty() && passwordInput2.isEmpty()){
            txtPassword.setError("REQUERIDO", icon);
            txtPassword2.setError("REQUERIDO", icon);
            return false;
        }

        if(passwordInput.matches(passwordInput2)){
            txtPassword.setError(null);
            txtPassword2.setError(null);
            return true;
        }else{
            txtPassword.setError("CONTRASEÑAS NO COINCIDEN", icon);
            txtPassword2.setError("CONTRASEÑAS NO COINCIDEN", icon);
            return false;
        }
    }

    private boolean validarRequeridos(){

        String nombreInput = txtNombre.getEditableText().toString().trim();
        String apellido1Input = txtApellido1.getEditableText().toString().trim();
        String apellido2Input = txtApellido2.getEditableText().toString().trim();
        String provinciaInput = txtProvincia.getEditableText().toString().trim();
        //String telefonoInput = txtTelefono.getEditableText().toString().trim();
        RadioButton sexBtn = rdMaleBtn;
        RadioGroup sexoInput = rdSex;

        Drawable icon = getResources().getDrawable(R.drawable.error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());



        if(nombreInput.isEmpty() || apellido1Input.isEmpty() || apellido2Input.isEmpty() || provinciaInput.isEmpty() ||  sexoInput.getCheckedRadioButtonId()<=0)
        {
            if(nombreInput.isEmpty()) {
                txtNombre.setError("REQUERIDO", icon);
            }
            if(apellido1Input.isEmpty()) {
                txtApellido1.setError("REQUERIDO", icon);
            }
            if(apellido2Input.isEmpty()) {
                txtApellido2.setError("REQUERIDO", icon);
            }
            if(provinciaInput.isEmpty()) {
                txtProvincia.setError("REQUERIDO", icon);
            }
          /*  if(telefonoInput.isEmpty()){
                txtTelefono.setError("REQUERIDO");
            }*/
            if(sexoInput.getCheckedRadioButtonId()<=0) {
                sexBtn.setError("SELECCIONE UNA OPCIÓN", icon);
            }
            return false;


        }else{

            txtNombre.setError(null);
            txtApellido1.setError(null);
            txtApellido2.setError(null);
            txtProvincia.setError(null);
            txtTelefono.setError(null);
            sexBtn.setError(null);
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
