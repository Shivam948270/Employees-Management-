package company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = request.getParameter("json");

        Gson gson = new Gson();
        List<User> list = gson.fromJson(json, new TypeToken<List<User>>(){}.getType());

        UserDao dao = new UserDao();
        dao.saveAll(list);

        response.getWriter().write("Data Saved Successfully");
    }
}
