package Controlador;

import DAO.NotasDAO;
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

@WebServlet(name = "get_user_data", urlPatterns = {"/get_user_data"})
public class get_user_data extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        UsuarioDAO usu = new UsuarioDAO();
        HashMap resultado = new HashMap();
        Usuario xd = new Usuario();

        int id_usuario = Integer.parseInt(sesion.getAttribute("Id_Usuario").toString());

        xd = usu.selectUsuarioId(id_usuario);
        resultado.put("user", xd);

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

    }
}
