package company;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/pdfimg")

public class PdfImg extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=users.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("User Details"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);

            table.addCell("UID");
            table.addCell("Username");

            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Password");

            table.addCell("Gender");

            table.addCell("Dob");

            table.addCell("Status");
            table.addCell("Image");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/r", "root", "root");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT uid,username,name,email,password,gender,dob,status FROM userDetails");
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("SELECT url FROM multipleDocs");

            int i = 1;
            while (rs.next()) {

//                table.addCell(String.valueOf(i++));
                table.addCell(rs.getString("uid"));

                table.addCell(rs.getString("username"));

                table.addCell(rs.getString("name"));
                table.addCell(rs.getString("email"));
                table.addCell(rs.getString("password"));
                table.addCell(rs.getString("gender"));
                table.addCell(rs.getString("dob"));

                table.addCell(rs.getString("status"));
                if (r.next()) {
                    String imgName = r.getString("url");
                    String imgPath = "/image/" + imgName;
                    Image img = Image.getInstance(
                            getServletContext().getRealPath("/") + imgPath);

                    img.scaleToFit(60, 60);
                    PdfPCell imgCell = new PdfPCell(img);
                    table.addCell(imgCell);
                }
            }

            document.add(table);
            document.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
