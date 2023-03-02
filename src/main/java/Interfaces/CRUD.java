package Interfaces;

import Model.Notas;
import Model.Usuario;
import java.util.List;

public interface CRUD {
    public List Listar();
    /////////////////////////////////////
    public boolean Agregar(Usuario user);
    public boolean Editar(Usuario user);
    public boolean Eliminar(Usuario user);
    ////////////////////////////////////

}
