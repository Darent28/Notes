package Controlador;

import DAO.NotasDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteNote", urlPatterns = {"/DeleteNote"})
public class DeleteNote extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap resultado = new HashMap();

        int Id_Nota = Integer.parseInt(request.getParameter("Id_Nota"));

        NotasDAO note = new NotasDAO();

        if (note.delete(Id_Nota)) {
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
