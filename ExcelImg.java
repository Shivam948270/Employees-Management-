package company;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/excelimg")

public class ExcelImg extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=users.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("UID");
        header.createCell(1).setCellValue("UserName");
        header.createCell(2).setCellValue("Name");
        header.createCell(3).setCellValue("Email");
        header.createCell(4).setCellValue("Password");
        header.createCell(5).setCellValue("Gender");
        header.createCell(6).setCellValue("Dob");
        header.createCell(7).setCellValue("Status");
        header.createCell(8).setCellValue("Image");

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/r", "root", "root");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT uid,username,name,email,password,gender,dob,status FROM userDetails");
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(
                    "SELECT url FROM multipleDocs");

            int rowNum = 1;
            int count = 1;

            while (rs.next()) {
                Row row = sheet.createRow(rowNum);
                row.setHeightInPoints(80);

                row.createCell(0).setCellValue(rs.getString("uid"));
                row.createCell(1).setCellValue(rs.getString("username"));

                row.createCell(2).setCellValue(rs.getString("name"));
                row.createCell(3).setCellValue(rs.getString("email"));
                row.createCell(4).setCellValue(rs.getString("password"));
                row.createCell(5).setCellValue(rs.getString("gender"));
                row.createCell(6).setCellValue(rs.getString("dob"));

                row.createCell(7).setCellValue(rs.getString("status"));

                if (r.next()) {
                    String imgName = r.getString("url"); 
                    String imgPath = "/image/" + imgName;

                    InputStream is = getServletContext().getResourceAsStream(imgPath);

                    row.setHeightInPoints(90);
                    sheet.setColumnWidth(8, 25 * 256);


                    if (is != null) {
                        byte[] bytes = IOUtils.toByteArray(is);
                        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
                        is.close();

                        CreationHelper helper = workbook.getCreationHelper();
                        Drawing drawing = sheet.createDrawingPatriarch();

                        ClientAnchor anchor = helper.createClientAnchor();
                        anchor.setCol1(8);
                        anchor.setRow1(rowNum);
                        anchor.setCol2(9);
                        anchor.setRow2(rowNum + 1);
                        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);

                        Picture picture = drawing.createPicture(anchor, pictureIdx);
                        picture.resize(1.0);
                    }
                }

                rowNum++;
            }

            workbook.write(response.getOutputStream());
            workbook.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
