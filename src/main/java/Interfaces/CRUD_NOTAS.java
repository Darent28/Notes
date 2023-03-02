package Interfaces;

import Model.Notas;
import java.util.List;

public interface CRUD_NOTAS {

    public List Listar();
    
    public boolean Agregar(Notas nota);

    public boolean Editar(Notas nota);

    public boolean Eliminar(Notas nota);
}
