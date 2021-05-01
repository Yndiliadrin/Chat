package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/act")
public class Actualizator extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("childRoom") != null) {
            session.setAttribute("child","rooms");
            session.setAttribute("query", 1);
            resp.sendRedirect("home");
        } else {
            session.setAttribute("child", "users");
            session.setAttribute("query", 1);
            resp.sendRedirect("home");
        }
    }
}
