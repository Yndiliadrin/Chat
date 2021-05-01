package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/search")
public class Search extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("search-btn") != null) {
            //csoport keresés - BEGIN
            HttpSession session = req.getSession();
            if (req.getParameter("kategori").equals("") && req.getParameter("condition").equals("")) {
                session.setAttribute("query", 1);
            } else if (!req.getParameter("kategori").equals("") && req.getParameter("condition").equals("")){
                session.setAttribute("query",2);
                session.setAttribute("condition", String.valueOf(req.getParameter("kategori")));
            } else if (req.getParameter("kategori").equals("") && !req.getParameter("condition").equals("")){
                session.setAttribute("query",3);
                session.setAttribute("condition", String.valueOf(req.getParameter("condition")));
            } else if (!req.getParameter("kategori").equals("") && !req.getParameter("condition").equals("")){
                session.setAttribute("query",4);
                session.setAttribute("condition1", String.valueOf(req.getParameter("kategori")));
                session.setAttribute("condition2", String.valueOf(req.getParameter("condition")));
            }
            //csoport keresés - END
            resp.sendRedirect("home");
        } else if (req.getParameter("user-search-btn") != null ) {
            HttpSession session = req.getSession();
            this.setSessionVariables(session, req);
            resp.sendRedirect("home");
        }
    }

    private void setSessionVariables(HttpSession session, HttpServletRequest req) {
        if (
                req.getParameter("interest").equals("")
                && req.getParameter("age").equals("")
                && req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 1);
        } else if (
                !req.getParameter("interest").equals("")
                && req.getParameter("age").equals("")
                && req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 2);
            session.setAttribute("condition1", String.valueOf(req.getParameter("interest")));
        }  else if (
                req.getParameter("interest").equals("")
                && !req.getParameter("age").equals("")
                && req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 3);
            session.setAttribute("condition1", Integer.parseInt(String.valueOf(req.getParameter("age"))));
        } else if (
                req.getParameter("interest").equals("")
                && req.getParameter("age").equals("")
                && !req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 4);
            session.setAttribute("condition1", String.valueOf(req.getParameter("gender")));
        } else if (
                !req.getParameter("interest").equals("")
                && !req.getParameter("age").equals("")
                && req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 5);
            session.setAttribute("condition1", String.valueOf(req.getParameter("interest")));
            session.setAttribute("condition2", Integer.parseInt(String.valueOf(req.getParameter("age"))));
        } else if (
                !req.getParameter("interest").equals("")
                && req.getParameter("age").equals("")
                && !req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 6);
            session.setAttribute("condition1", String.valueOf(req.getParameter("interest")));
            session.setAttribute("condition2", String.valueOf(req.getParameter("gender")));
        } else if (
                req.getParameter("interest").equals("")
                && !req.getParameter("age").equals("")
                && !req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 7);
            session.setAttribute("condition1", String.valueOf(req.getParameter("gender")));
            session.setAttribute("condition2", Integer.parseInt(String.valueOf(req.getParameter("age"))));
        } else if (
                !req.getParameter("interest").equals("")
                && !req.getParameter("age").equals("")
                && !req.getParameter("gender").equals("")
                && req.getParameter("condition").equals("")
        ) {
            session.setAttribute("query", 8);
            session.setAttribute("condition1", String.valueOf(req.getParameter("interest")));
            session.setAttribute("condition2", Integer.parseInt(String.valueOf(req.getParameter("age"))));
            session.setAttribute("condition3", String.valueOf(req.getParameter("gender")));
        } else {
            session.setAttribute("query", 9);
            session.setAttribute("condition1", String.valueOf(req.getParameter("condition")));
        }
    }

}
