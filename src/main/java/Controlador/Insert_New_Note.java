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

@WebServlet(name = "Insert_New_Note", urlPatterns = {"/Insert_New_Note"})
public class Insert_New_Note extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        HashMap resultado = new HashMap();
        NotasDAO OwO = new NotasDAO();

        String Titulo = request.getParameter("titulo_nota");
        String Nota = request.getParameter("nota_contenido");
        int Id_Usuario = Integer.parseInt(sesion.getAttribute("Id_Usuario").toString());

        boolean answer = OwO.Agregar_Nota(Titulo, Nota, Id_Usuario);

        resultado.put("respuesta", answer);

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

    }
}
