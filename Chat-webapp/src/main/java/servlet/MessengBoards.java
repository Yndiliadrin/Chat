package servlet;

import hu.alkfejl.dao.MessengDAO;
import hu.alkfejl.dao.MessengeDAOImpl;
import hu.alkfejl.model.Messeng;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;


@WebServlet("/msg")
@MultipartConfig(maxFileSize = 16177215)
public class MessengBoards extends HttpServlet {

    MessengDAO dao = new MessengeDAOImpl();

    private byte[] toByteArray(InputStream in) throws IOException
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in the buffer
        while ((len = in.read(buffer)) != -1)
        {
            // write bytes from the buffer into the output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (req.getParameter("csop") != null) {
            session.setAttribute("msBID",req.getParameter("csop"));
            resp.sendRedirect("home");
        } else if (req.getParameter("felh") != null) {
            session.setAttribute("msBID", req.getParameter("felh"));
            resp.sendRedirect("home");
        } else if (req.getParameter("send") != null) {
            if (!req.getParameter("msg").equals("")) {
                Messeng messeng = new Messeng();
                messeng.setSender(Integer.parseInt(String.valueOf(session.getAttribute("uID"))));
                messeng.setReceiver(Integer.parseInt(String.valueOf(session.getAttribute("msBID"))));
                messeng.setType("text");
                messeng.setMesseng(req.getParameter("msg"));
                messeng.setImg(null);
                int toWhat = (session.getAttribute("child").equals("rooms") ? 1 : 0);
                messeng.setTo_what(toWhat);
                //System.out.println("'"+messeng.getMesseng()+"'"+" üzenet küldése a(z) " + req.getParameter("send") + "-es ID-ra | type: "+messeng.getType());
                dao.insertMSG(messeng);
            } else if (req.getPart("img") != null) {
                InputStream inputStream = null;
                Part filePart = req.getPart("img");
                if (filePart != null) {
                    inputStream = filePart.getInputStream();
                }
                Messeng messeng = new Messeng();
                messeng.setSender(Integer.parseInt(String.valueOf(session.getAttribute("uID"))));
                messeng.setReceiver(Integer.parseInt(String.valueOf(session.getAttribute("msBID"))));
                messeng.setType("img");
                messeng.setMesseng(null);
                messeng.setImg(this.toByteArray(inputStream));
                int toWhat = (session.getAttribute("child").equals("rooms") ? 1 : 0);
                messeng.setTo_what(toWhat);
                //System.out.println("Kép küldése a(z) " + req.getParameter("send") + "-es ID-ra | type: "+messeng.getType());
                dao.insertMSG(messeng);
            } else if (!req.getParameter("msg").equals("")) {
                System.out.println("Üzenet küldése a(z) " + req.getParameter("send") + "-es ID-ra");
                Messeng messeng = new Messeng();
                messeng.setSender(Integer.parseInt(String.valueOf(session.getAttribute("uID"))));
                messeng.setReceiver(Integer.parseInt(String.valueOf(session.getAttribute("msBID"))));
                messeng.setType("text");
                messeng.setMesseng(req.getParameter("msg"));
                messeng.setImg(null);
                int toWhat = (session.getAttribute("child").equals("rooms") ? 1 : 0);
                messeng.setTo_what(toWhat);
                dao.insertMSG(messeng);
            }
            resp.sendRedirect("home");
        } else {
            System.out.println("nincs paraméter");
        }
    }
}
