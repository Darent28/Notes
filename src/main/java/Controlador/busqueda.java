package Controlador;

import DAO.NotasDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "busqueda", urlPatterns = {"/busqueda"})
public class busqueda extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap resultado = new HashMap();
        HttpSession sesion = request.getSession();
        NotasDAO uwu = new NotasDAO();

        String parametro = request.getParameter("parametro");
        int Id_Usuario = Integer.parseInt(sesion.getAttribute("Id_Usuario").toString());
        List lista = uwu.ListarrBusqueda(Id_Usuario, parametro);

        resultado.put("resultado", lista);

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
