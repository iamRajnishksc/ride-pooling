import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ExcelService {
    public <T> byte[] toExcel(List<T> data) throws IOException {
        if (data.isEmpty()) return new byte[0];

        try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = wb.createSheet("Data");
            Field[] fields = data.get(0).getClass().getDeclaredFields();

            // Create header row
            Row header = sheet.createRow(0);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                String columnName = getJsonPropertyValue(fields[i]); // Get @JsonProperty value
                header.createCell(i).setCellValue(columnName);
            }

            // Populate data rows
            int rowNum = 1;
            for (T obj : data) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < fields.length; i++) {
                    try {
                        Object value = fields[i].get(obj);
                        row.createCell(i).setCellValue(value != null ? value.toString() : "");
                    } catch (IllegalAccessException e) {
                        row.createCell(i).setCellValue("ERROR");
                    }
                }
            }

            wb.write(out);
            return out.toByteArray();
        }
    }

    private String getJsonPropertyValue(Field field) {
        JsonProperty annotation = field.getAnnotation(JsonProperty.class);
        return (annotation != null) ? annotation.value() : field.getName();
    }
}
