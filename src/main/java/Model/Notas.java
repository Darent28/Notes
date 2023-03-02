package Model;

public class Notas {

    private int Id_Nota;
    private String Titulo;
    private String Nota;
    private String Usuario_Nombre;
    private String AyudameDiositoxd;

    public String getAyudameDiositoxd() {
        return AyudameDiositoxd;
    }

    public void setAyudameDiositoxd(String AyudameDiositoxd) {
        this.AyudameDiositoxd = AyudameDiositoxd;
    }

    public Notas() {

    }

    public Notas(int Id_Nota, String Titulo, String Nota) {

        this.Id_Nota = Id_Nota;
        this.Titulo = Titulo;
        this.Nota = Nota;
    }

    public Notas(String Titulo, String Nota, int Id_Nota) {
        this.Titulo = Titulo;
        this.Nota = Nota;
        this.Id_Nota = Id_Nota;
    }

    public Notas(int Id_Nota, String Titulo, String Nota, String Usuario_Nombre) {
        this.Id_Nota = Id_Nota;
        this.Titulo = Titulo;
        this.Nota = Nota;
        this.Usuario_Nombre = Usuario_Nombre;
    }

    public int getId_Nota() {
        return Id_Nota;
    }

    public void setId_Nota(int Id_Nota) {
        this.Id_Nota = Id_Nota;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String Nota) {
        this.Nota = Nota;
    }

    public String getUsuario_Nombre() {
        return Usuario_Nombre;
    }

    public void setUsuario_Nombre(String Usuario_Nombre) {
        this.Usuario_Nombre = Usuario_Nombre;
    }
}
