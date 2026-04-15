package company;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

@MultipartConfig
@WebServlet("/UploadExcel")
public class UploadExcel extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("file");
        InputStream is = filePart.getInputStream();

        Workbook workbook = WorkbookFactory.create(is); 
        Sheet sheet = workbook.getSheetAt(0);

        List<List<String>> data = new ArrayList<>();

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                cell.setCellType(CellType.STRING);
                rowData.add(cell.getStringCellValue());
            }
            data.add(rowData);
        }

        workbook.close();

        response.setContentType("application/json");
        response.getWriter().print(new Gson().toJson(data));
    }
}
