import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//added line
public class Main {
    public static void main (String[] args)throws IOException{
        //FOLLOWING EXAMPLE TAKEN FROM https://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi
        //Example shows how to iterate over every box in an excel sheet row by row left to right

        String excelFilePath = "GroceryStoreData.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        System.out.println("THIS IS THE CHANGE :)");
        System.out.println("ANOTHER CHANGE!!!!!!!!!!!!");
        System.out.println("MORE CHANGES!!!!!!!!!!!!!!!");
        while (iterator.hasNext()){
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                switch (cell.getCellType()){
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.println(" - ");
            }
            System.out.println();
        }
        workbook.close();
        inputStream.close();
        //END EXAMPLE FROM https://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi
    }
}
