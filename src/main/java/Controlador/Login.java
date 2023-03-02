package Controlador;

import DAO.UsuarioDAO;
import Model.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    
        HashMap resultado = new HashMap();
        HttpSession sesion;
        String Usuario_Nombre = request.getParameter("Usuario_Nombre");
        String Contrasenia = request.getParameter("Contrasenia");

        Usuario user = new Usuario(Usuario_Nombre, Contrasenia);
        UsuarioDAO userDAO = new UsuarioDAO();
        user = userDAO.identificar(user);

        if (user != null) {
            sesion = request.getSession();
            
            //aqui creo la sesion
            //Solo paso el Id de mi cliente para la sesion
            
            //sesion.setAttribute("Nombre", user.getNombre());
            //sesion.setAttribute("Apellidos", user.getApellidos());
            //sesion.setAttribute("Fecha_Nacimiento", user.getFecha_Nacimiento());
            //sesion.setAttribute("Correo", user.getCorreo());
            sesion.setAttribute("Id_Usuario", user.getId_Usuario());
            int id = Integer.parseInt(sesion.getAttribute("Id_Usuario").toString());

            //sesion.setAttribute("Usuario_Nombre", user.getUsuario());
            //sesion.setAttribute("Contrasenia", user.getContrasenia());
            //sesion.setAttribute("foto", user.getFoto());

            resultado.put("user", user);
            resultado.put("respuesta", true);
        } else {
            resultado.put("respuesta", false);
        }

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
