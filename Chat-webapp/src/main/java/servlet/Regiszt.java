package servlet;

import Controller.DBController;
import Controller.Encrypt;
import Controller.UserController;
import Controller.UserControllerImpl;
import hu.alkfejl.dao.UserDAO;
import hu.alkfejl.dao.UserDAOImpl;
import hu.alkfejl.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/regiszt")
public class Regiszt extends HttpServlet {

    private UserController uController;
    private Encrypt encrypt;

    public Regiszt() {
        uController = new UserControllerImpl();
        encrypt = new Encrypt();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("pages/regiszt.html");
        resp.setStatus(200);
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (req.getParameter("reg") != null) {
            if (req.getParameter("pass").equals(req.getParameter("pass2"))
                    && uController.isExists(req.getParameter("uname")) ) {
                User user = new User();
                user.setUsername(req.getParameter("uname"));
                user.setPassword(encrypt.encryptIt(req.getParameter("pass")));
                user.setAge(Integer.parseInt(req.getParameter("age")));
                user.setGender(req.getParameter("gender"));
                user.setInterest(req.getParameter("interest"));
                user.setRole(0);
                this.uController.regiszt(user);
                resp.sendRedirect("login");
            } else if ( !uController.isExists(req.getParameter("uname"))) {
                //out.println("Felhasználónév foglalt");
                out.println("<script type =\"text/javascript\">\n" +
                        "            window.onload = function() {\n" +
                        "            what();\n" +
                        "            function what(){\n" +
                        "                document.getElementById('errorMsg').innerHTML = 'Felhasználónév foglalt';\n" +
                        "            };\n" +
                        "        }\n" +
                        "        </script>");
                RequestDispatcher rd = req.getRequestDispatcher("/pages/regiszt.html");
                rd.include(req,resp);
            } else {
                out.println("<script type =\"text/javascript\">\n" +
                        "            window.onload = function() {\n" +
                        "            what();\n" +
                        "            function what(){\n" +
                        "                document.getElementById('errorMsg').innerHTML = 'A jelszónak tartalmaznia kell legalább 1 kisbetűt, 1 nagybetűt és egy számot!';\n" +
                        "            };\n" +
                        "        }\n" +
                        "        </script>");
                RequestDispatcher rd = req.getRequestDispatcher("/pages/regiszt.html");
                rd.include(req,resp);
            }
        } else if (req.getParameter("log") != null) {
            resp.sendRedirect("login");
        } else {
            out.println("<p>A jelszavak nem egyeznek!<p>");
            RequestDispatcher rd = req.getRequestDispatcher("/pages/regiszt.html");
            rd.include(req,resp);
        }
    }
}
