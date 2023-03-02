package Model;

public class Usuario {

    private int Id_Usuario;
    private String Nombre;
    private String Apellidos;
    private String Fecha_Nacimiento;
    private String Correo;
    private String Usuario_Nombre;
    private String Contrasenia;
    private String foto;

    public Usuario() {
    }

    //para el login
    public Usuario(String Usuario_Nombre, String Contrasenia) {
        this.Usuario_Nombre = Usuario_Nombre;
        this.Contrasenia = Contrasenia;
    }

    //para editar
    public Usuario(int Id_Usuario, String Nombre, String Apellidos, String Fecha_Nacimiento,
            String Correo, String Usuario_Nombre, String Contrasenia,
            String foto) {
        this.Id_Usuario = Id_Usuario;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Correo = Correo;
        this.Usuario_Nombre = Usuario_Nombre;
        this.Contrasenia = Contrasenia;
        this.foto = foto;
    }

    //para registrar
    public Usuario(String Nombre, String Apellidos, String Fecha_Nacimiento,
            String Correo, String Usuario_Nombre, String Contrasenia,
            String foto) {
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Correo = Correo;
        this.Usuario_Nombre = Usuario_Nombre;
        this.Contrasenia = Contrasenia;
        this.foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getUsuario() {
        return Usuario_Nombre;
    }

    public void setUsuario_Nombre(String Usuario_Nombre) {
        this.Usuario_Nombre = Usuario_Nombre;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }
}
