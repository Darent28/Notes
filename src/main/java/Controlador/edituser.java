package Controlador;

import DAO.UsuarioDAO;
import Model.Usuario;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "edituser", urlPatterns = {"/edituser"})
@MultipartConfig(maxFileSize = 16177216) //1.5mb

public class edituser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap resultado = new HashMap();
        UsuarioDAO userDAO = new UsuarioDAO();

        String uploadPath = getServletContext().getRealPath("/usuarioImg/");
        File fdir = new File(uploadPath);
//
        if (!fdir.exists()) {
            fdir.mkdir();
        }

        Part filePart = request.getPart("foto");
        String contentTypeImg = filePart.getContentType();
        String nombreArchivo = String.valueOf(System.currentTimeMillis());
        String path = "usuarioImg/" + nombreArchivo + extractExtension(filePart);
        filePart.write(uploadPath + "/" + nombreArchivo + extractExtension(filePart));

        int id = Integer.parseInt(request.getParameter("milagro"));
        String Nombre = request.getParameter("Nombre");
        String Apellidos = request.getParameter("Apellidos");
        String Usuario_Nombre = request.getParameter("Usuario_Nombre");
        String Contrasenia = request.getParameter("Contrasenia");
        String picker = request.getParameter("picker");
        String Correo = request.getParameter("Correo");

//        String regx1 = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
//        Pattern pattern1 = Pattern.compile(regx1);
//        Matcher matcher1 = pattern1.matcher(Correo);
//
//        String regx2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
//        Pattern pattern2 = Pattern.compile(regx2);
//        Matcher matcher2 = pattern2.matcher(Contrasenia);

//        if (matcher1.find() == true && matcher2.find() == true) {
//          System.out.println("Correo y contraseña ingresado es valido c:");

            Usuario user = new Usuario(id, Nombre, Apellidos, picker, Correo, Usuario_Nombre, Contrasenia, path);
            if (userDAO.Editar(user)) {
                resultado.put("respuesta", true);
            } else {
                resultado.put("respuesta", false);
            }
//
//        } else {
//            System.out.println("Correo o contraseña ingresado Invalido :C");
//            resultado.put("respuesta", false);
//        }

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
