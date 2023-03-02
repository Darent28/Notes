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

@WebServlet(name = "GetNotas", urlPatterns = {"/GetNotas"})
public class GetNotas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        int Id_Usuario = Integer.parseInt(sesion.getAttribute("Id_Usuario").toString());
        HashMap resultado = new HashMap();
        
        NotasDAO notadao = new NotasDAO();
        
        List lista = notadao.ListarNotasDavid(Id_Usuario);
        resultado.put("notas", lista);

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
