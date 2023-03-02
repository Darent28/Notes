package Controlador;

import DAO.UsuarioDAO;
import Model.Usuario;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RegistroUsuario", urlPatterns = {"/RegistroUsuario"})
@MultipartConfig(maxFileSize = 16177216) //1.5mb
public class RegistroUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap resultado = new HashMap();
        UsuarioDAO userDAO = new UsuarioDAO();

        String uploadPath = getServletContext().getRealPath("/usuarioImg/");
        File fdir = new File(uploadPath);

        if (!fdir.exists()) {
            fdir.mkdir();
        }

        Part filePart = request.getPart("foto");
        String contentTypeImg = filePart.getContentType();
        String nombreArchivo = String.valueOf(System.currentTimeMillis());
        String path = "usuarioImg/" + nombreArchivo + extractExtension(filePart);
        filePart.write(uploadPath + "/" + nombreArchivo + extractExtension(filePart));

        String Nombre = request.getParameter("Nombre");
        String Apellidos = request.getParameter("Apellidos");
        String Usuario_Nombre = request.getParameter("Usuario_Nombre");
        String Contrasenia = request.getParameter("Contrasenia");
        String picker = request.getParameter("picker");
        String Correo = request.getParameter("Correo");

   


            Usuario user = new Usuario(Nombre, Apellidos, picker, Correo, Usuario_Nombre, Contrasenia, path);
            if (userDAO.Agregar(user)) {
                resultado.put("respuesta", true);
            } else {
                resultado.put("respuesta", false);
            }

    

        String json = new Gson().toJson(resultado);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    private String extractExtension(Part part) {

        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String filename = items[2].trim().substring(items[2].trim().indexOf("=") + 2, items[2].trim().length() - 1);
        return filename.substring(filename.indexOf("."), filename.length());
    }
}
