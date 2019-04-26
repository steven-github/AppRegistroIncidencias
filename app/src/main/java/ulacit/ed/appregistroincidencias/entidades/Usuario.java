package ulacit.ed.appregistroincidencias.entidades;

public class Usuario {

 private Integer id;
 private String nombre;

 private String provincia;

 private String correo;


    public Usuario(Integer id, String nombre, String provincia, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
