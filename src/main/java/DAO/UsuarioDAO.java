package DAO;

import Config.Conexion;
import Interfaces.CRUD;
import Model.Usuario;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements CRUD {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Usuario usu = null;
  
    public Usuario identificar(Usuario user) {

        String sql = "SELECT * FROM Usuario WHERE Usuario_Nombre='" + user.getUsuario() + "'AND Contrasenia='" + user.getContrasenia() + "'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); //solo consultas

            if (rs.next() == true) {
                usu = new Usuario();
                usu.setNombre(rs.getString("Nombre"));
                usu.setApellidos(rs.getString("Apellidos"));
                usu.setFecha_Nacimiento(rs.getString("Fecha_Nacimiento"));
                usu.setCorreo(rs.getString("Correo"));
                usu.setId_Usuario(rs.getInt("Id_Usuario"));//aqui traigo sus datos y los mapeo 
                usu.setUsuario_Nombre(rs.getString("Usuario_Nombre"));//aqui traigo sus datos y los mapeo 
                usu.setContrasenia(rs.getString("Contrasenia"));//aqui traigo sus datos y los mapeo 
                usu.setFoto(rs.getString("foto"));//aqui traigo sus datos y los mapeo 

            }
        } catch (Exception e) {
            System.out.println("SUPER ERRORRR" + e);
        } finally {
            cerrarConexiones();
        }
        return usu;
    }

    @Override
    public boolean Agregar(Usuario user) {
        
        
        //String sql = "INSERT INTO Usuario(Usuario_Nombre,Contrasenia,foto)values ('" + user.getUsuario() + "','" + user.getContrasenia() + "','" + user.getFoto() + "')";
        String sql = "INSERT INTO Usuario(Nombre,Apellidos,Fecha_Nacimiento,Correo,Usuario_Nombre,Contrasenia,foto)values "
                + "('" + user.getNombre() + "','" + user.getApellidos() + "','" + user.getFecha_Nacimiento() + "','" + user.getCorreo() + "','" + user.getUsuario() + "','" + user.getContrasenia() + "','" + user.getFoto() + "')";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            int resultado = ps.executeUpdate(); //insert ,delete, update

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("SUPER ERRORRR" + e);
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean Editar(Usuario user) {

        String sql = "update Usuario set Nombre='" + user.getNombre() + "', Apellidos='" + user.getApellidos() + "' ,Fecha_Nacimiento='" + user.getFecha_Nacimiento() + "' ,Correo='"
                + user.getCorreo() + "', Usuario_Nombre='" + user.getUsuario() + "' ,Contrasenia='" + user.getContrasenia() + "' ,foto='" + user.getFoto() + "' where Id_Usuario='" + user.getId_Usuario() + "'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            int resultado = ps.executeUpdate(); //insert ,delete, update

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("SUPER ERRORRR" + e);
            System.out.println(e);
            return false;
        }

    }

    private void cerrarConexiones() {
        try {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
            if (cs != null && cs.isClosed() == false) {
                cs.close();
            }
            cs = null;
            if (ps != null && ps.isClosed() == false) {
                ps.close();
            }
            ps = null;

            if (con != null && con.isClosed() == false) {
                con.close();
            }
            con = null;
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Usuario selectUsuarioId(int id) {

        String store = "{CALL selectUsuarioId(?)}";

        try {
            con = cn.getConnection();
            cs = con.prepareCall(store);
            cs.setInt(1, id);
            rs = cs.executeQuery();

            if (rs.next()) {

                usu = new Usuario();
                usu.setNombre(rs.getString("Nombre"));
                usu.setApellidos(rs.getString("Apellidos"));
                usu.setFecha_Nacimiento(rs.getString("Fecha_Nacimiento"));
                usu.setCorreo(rs.getString("Correo"));
                usu.setId_Usuario(rs.getInt("Id_Usuario"));
                usu.setUsuario_Nombre(rs.getString("Usuario_Nombre"));
                usu.setContrasenia(rs.getString("Contrasenia"));
                usu.setFoto(rs.getString("foto"));
            }

        } catch (Exception e) {
            System.out.println("SUPER ERRORRR" + e);
            System.out.println(e);
        } finally {
            cerrarConexiones();
        }
        return usu;
    }

    @Override
    public List Listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
