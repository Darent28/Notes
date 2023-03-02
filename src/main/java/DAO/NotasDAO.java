package DAO;

import Config.Conexion;
import Interfaces.CRUD_NOTAS;
import Model.Notas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotasDAO implements CRUD_NOTAS {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Notas nota;
    Notas notita = new Notas();
    
    public int CantidadTotalBusqueda(String Parametro, String Usuario) {
        //String sql = "select count(*) as Total from Notas where Titulo like '%" + Parametro + "%' and Usuario_Nombre ='" + Usuario + "'";
        String sql = "select count(*) as Total from Notas where Titulo like '%" + Parametro + "%' and Usuario_Nombre ='" + Usuario + "' and Estado=0";
        
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                return rs.getInt("Total");
            }
            
        } catch (Exception e) {
            System.out.println("este Error es sad" + e);
        }
        return 0;
    }
    
    public List ListarrBusqueda(int Id_Usuario, String Parametro) {
        
        ArrayList<Notas> list = new ArrayList();
        //String sql = "select * from Notas where Titulo like '%" + Parametro + "%'";
        //String sql = "select * from Notas where Estado=0 and Titulo like '%" + Parametro + "%' and Usuario_Nombre ='" + Usuario_Nombre + "' LIMIT ?,?;";

        // String sql = "select Titulo as Titulo, Nota as Nota, Id_Nota as Id_Nota from Notas where Titulo like concat('%'," + Parametro + ", '%');";
        String sql = "call Busqueda(?, ?)";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, Id_Usuario);
            cs.setString(2, Parametro);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                nota = new Notas(rs.getString("Titulo"), rs.getString("Nota"), rs.getInt("Id_Nota"));
                list.add(nota);
            }
            
        } catch (Exception e) {
            System.out.println("este Error es sad" + e);
        }
        return list;
    }
    
    public List ListarNotasDavid(int Id_Usuario) {
        
        ArrayList<Notas> list = new ArrayList();
        String sql = "call Get_Notas(?)";
        
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, Id_Usuario);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                nota = new Notas(rs.getInt("Id_Nota"), rs.getString("Titulo"), rs.getString("Nota"));
                list.add(nota);
            }
            
        } catch (Exception e) {
            System.out.println("este Error es sad" + e);
        }
        return list;
    }
    
    public boolean Agregar_Nota(String Nombre_Nota, String Contenido, int Id_Usuario) {
        
        String sql = "INSERT INTO Notas(Titulo,Nota,Id_Usuario) values('" + Nombre_Nota + "','" + Contenido + "','" + Id_Usuario + "')";
        
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
            System.out.println("Bob esponja" + e);
            return false;
        }
        
    }
    
    public boolean Editar_Nota(Notas nota) {
        
        String sql = "update Notas set Titulo ='" + nota.getTitulo() + "', Nota = '" + nota.getNota() + "' where Id_Nota= '" + nota.getId_Nota() + "'";
        
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
    
    public List Listarr(int indice, int cantidad, String Usuario_Nombre) {
        
        ArrayList<Notas> list = new ArrayList();
        String sql = "select * from Notas where Usuario_Nombre = '" + Usuario_Nombre + "' LIMIT ?,?;";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, indice);
            ps.setInt(2, cantidad);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                nota = new Notas(rs.getInt("Id_Nota"), rs.getString("Titulo"), rs.getString("Nota"), rs.getString("Usuario_Nombre"));
                list.add(nota);
            }
            
        } catch (Exception e) {
            System.out.println("este Error es sad" + e);
        }
        return list;
    }
    
    @Override
    public List Listar() {
        ArrayList<Notas> list = new ArrayList();
        String sql = "select * from Notas";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                nota = new Notas(rs.getInt("Id_Nota"), rs.getString("Titulo"), rs.getString("Nota"), rs.getString("Usuario_Nombre"));
                list.add(nota);
                
            }
            
        } catch (Exception e) {
            System.out.println("este Error es sad" + e);
        }
        return list;
    }
    
    public boolean Edit(int Id_Nota, String New_Titulo, String New_Note) {
        
        String sql = "update Notas set Titulo = '" + New_Titulo + "', Nota ='" + New_Note + "' where Id_Nota = '" + Id_Nota + "'";
        
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
    
    public boolean delete(int id) {
        
        String sql = "delete from Notas where Id_Nota = '" + id + "'";//elimina
        // String sql = "update Notas set Estado=1 where Id_Nota = " + id;//la bloquea

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
    public boolean Agregar(Notas nota) {
        
        String sql = "INSERT INTO Notas(Titulo,Nota,Usuario_Nombre) values('" + nota.getTitulo() + "','" + nota.getNota() + "','" + nota.getUsuario_Nombre() + "')";
        
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
    public boolean Editar(Notas nota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean Eliminar(Notas nota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
