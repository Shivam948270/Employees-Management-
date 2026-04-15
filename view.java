package company;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class view extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uid = request.getParameter("uid");
        String email = request.getParameter("email");
        String status = request.getParameter("status");

        UserDao dao = new UserDao();
        List<User> list;

        if ((uid == null || uid.trim().isEmpty())
                && (email == null || email.trim().isEmpty())
                && (status == null || status.trim().isEmpty())) {

            list = dao.getAllUsers();
            request.setAttribute("users", list);
            request.getRequestDispatcher("vieww.jsp").forward(request, response);

        } else {

            list = dao.getUsers(uid, email, status);
            request.setAttribute("users", list);
            request.getRequestDispatcher("vieww.jsp").forward(request, response);
        }
        

    }
}
