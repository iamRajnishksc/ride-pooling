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

            // Header row
            Row header = sheet.createRow(0);
            for (int i = 0; i < fields.length; i++) 
                header.createCell(i).setCellValue(fields[i].getName());

            // Data rows
            int rowNum = 1;
            for (T obj : data) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    row.createCell(i).setCellValue(fields[i].get(obj).toString());
                }
            }

            wb.write(out);
            return out.toByteArray();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error processing Excel file", e);
        }
    }
}
import os
import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin

# Define the base URL and output directory
base_url = "https://archive.mariadb.org/mariadb-10.11.5/repo/debian/"
output_dir = "downloaded_files"

# Create the output directory if it doesn't exist
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

# Function to download files recursively
def download_files(url, output_dir):
    response = requests.get(url)
    response.raise_for_status()  # Ensure we notice bad responses
    soup = BeautifulSoup(response.text, 'html.parser')
    links = soup.find_all('a')

    for link in links:
        href = link.get('href')
        if href and href != '../':
            file_url = urljoin(url, href)
            local_path = os.path.join(output_dir, href)

            if href.endswith('/'):
                # Create subdirectory
                if not os.path.exists(local_path):
                    os.makedirs(local_path)
                # Recursively download files from subdirectory
                download_files(file_url, local_path)
            else:
                # Download the file
                with requests.get(file_url, stream=True) as r:
                    r.raise_for_status()
                    with open(local_path, 'wb') as f:
                        for chunk in r.iter_content(chunk_size=8192):
                            f.write(chunk)
                print(f"Downloaded: {local_path}")

# Start downloading files recursively
download_files(base_url, output_dir)

print(f"All files downloaded to {output_dir}.")


- module: jolokia
  metricsets: ["jmx"]
  enabled: true
  period: 10s
  hosts: ["http://localhost:8080/your-webapp/jolokia"]
  namespace: "metrics"
  jmx.mappings:
    - mbean: 'java.lang:type=GarbageCollector,name=*'
      attributes:
        - attr: CollectionTime
          field: gc.collection_time
        - attr: CollectionCount
          field: gc.collection_count
    - mbean: 'java.lang:type=Memory'
      attributes:
        - attr: HeapMemoryUsage
          field: memory.heap_usage
        - attr: NonHeapMemoryUsage
          field: memory.non_heap_usage
- module: http
  metricsets: ["json"]
  period: 10s
  hosts: ["http://localhost:8080/actuator/metrics/jvm.memory.used"]
  namespace: "springboot"
  processors:
    - decode_json_fields:
        fields: ['measurements']
        target: 'memory_usage'
