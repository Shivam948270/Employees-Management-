package company;

import static java.awt.SystemColor.window;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        System.out.println(action);

        if ("login".equals(action)) {

            UserDao dao = new UserDao();
            User u = dao.checkLogin(email, password);

            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", u);
                session.setAttribute("role", u.getRole());
                out.print("success");

            } else {
                out.print("fail");
            }
        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();

            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
