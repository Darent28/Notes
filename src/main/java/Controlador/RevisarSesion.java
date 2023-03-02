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

@WebServlet(name = "RevisarSesion", urlPatterns = {"/RevisarSesion"})
public class RevisarSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        HashMap resultado = new HashMap();
        UsuarioDAO userDao = new UsuarioDAO();

        if (sesion.getAttribute("Id_Usuario") != null) {
            int id = Integer.parseInt(sesion.getAttribute("Id_Usuario").toString());
            Usuario user = userDao.selectUsuarioId(id);
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
