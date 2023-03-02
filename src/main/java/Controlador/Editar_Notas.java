package Controlador;

import DAO.NotasDAO;
import Model.Notas;
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

@WebServlet(name = "Editar_Notas", urlPatterns = {"/Editar_Notas"})
public class Editar_Notas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap resultado = new HashMap();
        NotasDAO ewe = new NotasDAO();

        int Id_Nota = Integer.parseInt(request.getParameter("Id_Nota"));
        String Nuevo_Titulo = request.getParameter("Nuevo_Titulo");
        String Nueva_Nota = request.getParameter("Nueva_Nota");

        Boolean respuesta = ewe.Edit(Id_Nota, Nuevo_Titulo, Nueva_Nota);

        resultado.put("respuesta", respuesta);

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
