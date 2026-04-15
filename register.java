package company;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@MultipartConfig
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String status = request.getParameter("status");
        String role=request.getParameter("role");
        System.out.println("role is "+ role);


        String[] accno = request.getParameterValues("accountNo");
        String[] bank = request.getParameterValues("bankName");
        String[] ifsc = request.getParameterValues("ifscCode");

        UserDao d = new UserDao();

        Part tablePart = request.getPart("tableimage");
        String tableImageName = null;

        String tablePath = getServletContext().getRealPath("") + File.separator + "tableimage";
        File tableDir = new File(tablePath);
        if (!tableDir.exists()) tableDir.mkdirs();

        if (tablePart != null && tablePart.getSize() > 0) {
            tableImageName = Paths.get(tablePart.getSubmittedFileName()).getFileName().toString();
            tablePart.write(tablePath + File.separator + tableImageName);
        }

        if ("add".equals(action)) {
                    System.out.println("role inside add is "+ role);



            String uid = "USR" + System.currentTimeMillis();
            List<String> fileNameList = new ArrayList<>();
            int count = 0;

            String uploadPath = getServletContext().getRealPath("") + File.separator + "image";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            for (Part p : request.getParts()) {
                if ("image".equals(p.getName()) && p.getSize() > 0) {

                    String orig = Paths.get(p.getSubmittedFileName()).getFileName().toString();
                    String ext = orig.contains(".") ? orig.substring(orig.lastIndexOf(".")) : "";

                    String fname = uid + "_" + count++ + ext;
                    p.write(uploadPath + File.separator + fname);
                    fileNameList.add(fname);
                }
            }

            User u = new User(uid, username, name, email, password, gender, dob, status, role);
            u.setFileName(fileNameList.toArray(new String[0]));
            u.setAccno(accno);
            u.setBank(bank);
            u.setIfsc(ifsc);
            u.setUrl(tableImageName);

            out.println(d.saveDetail(u) ? "added" : "error");
            System.out.println(u.getRole());
        }

        else if ("edit".equals(action)) {
                                System.out.println("role inside edit is "+ role);

            String uid = request.getParameter("uid");
            String[] oldFiles = request.getParameterValues("oldFiles");

            String uploadPath = getServletContext().getRealPath("") + File.separator + "image";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            if (oldFiles != null) {
                for (String img : oldFiles) {
                    File f = new File(uploadPath + File.separator + img);
                    if (f.exists()) f.delete();
                }
            }

            List<String> fileNameList = new ArrayList<>();
            int count = 0;

            for (Part p : request.getParts()) {
                if ("image".equals(p.getName()) && p.getSize() > 0) {
                    String orig = Paths.get(p.getSubmittedFileName()).getFileName().toString();
                    String ext = orig.contains(".") ? orig.substring(orig.lastIndexOf(".")) : "";
                    String fname = uid + "_" + count++ + ext;
                    p.write(uploadPath + File.separator + fname);
                    fileNameList.add(fname);
                }
            }

            User u = new User(uid, username, name, email, password, gender, dob, status,role);
            u.setFileName(fileNameList.toArray(new String[0]));
            u.setAccno(accno);
            u.setBank(bank);
            u.setIfsc(ifsc);
            u.setUrl(tableImageName);

            out.println(d.updateUser(u) ? "updated" : "error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String uid = request.getParameter("uid");
        String status = request.getParameter("status");

        UserDao d = new UserDao();

        if ("change".equals(action)) {
            User u = new User(uid, status);
            d.changeStatus(u);
            return;
        }

        User u = d.getbyId(uid);
        if (u != null && d.deleteUser(u)) {

            String uploadPath = getServletContext().getRealPath("") + File.separator + "image";
            if (u.getFileName() != null) {
                for (String img : u.getFileName()) {
                    File f = new File(uploadPath + File.separator + img);
                    if (f.exists()) f.delete();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
