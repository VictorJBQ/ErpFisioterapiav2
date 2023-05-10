package proy.MoisVictorv1.ErpFisioterapiav1.Utilidades;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

public class DescargaExcel {
	
	public  void descargarExcel(HttpServletResponse response, List<?> listaDatos, String nombre) throws Exception {
        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
       
        // Obtener los nombres de los campos de la clase del primer elemento de la lista
        Class<?> clase = listaDatos.get(0).getClass();
        Field[] campos = clase.getDeclaredFields();
        Sheet sheet = workbook.createSheet(nombre);
        Row headerRow = sheet.createRow(0);
        List<String> nombresCampos = new ArrayList<>();
        for (Field campo : campos) {
            nombresCampos.add(campo.getName());
        }

        // Agregar los nombres de los campos como encabezados de la hoja de cálculo
        for (int i = 0; i < nombresCampos.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(nombresCampos.get(i));
        }

        // Agregar los datos de la lista a la hoja de cálculo
        for (int i = 0; i < listaDatos.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            Object objeto = listaDatos.get(i);

            for (int j = 0; j < nombresCampos.size(); j++) {
                Field campo = clase.getDeclaredField(nombresCampos.get(j));
                campo.setAccessible(true);
                Object valorCampo = campo.get(objeto);

                Cell cell = dataRow.createCell(j);
                if (valorCampo != null) {
                    if (valorCampo instanceof String) {
                        cell.setCellValue((String) valorCampo);
                    } else if (valorCampo instanceof Integer) {
                        cell.setCellValue((Integer) valorCampo);
                    } else if (valorCampo instanceof Double) {
                        cell.setCellValue((Double) valorCampo);
                    } else {
                        cell.setCellValue(valorCampo.toString());
                    }
                }
            }
        }

        workbook.write(baos);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename="+nombre+".xls");
        
        response.setContentLength(baos.size());
   
        OutputStream out = response.getOutputStream();
        baos.writeTo(out);
        
        out.flush();
    }
}
