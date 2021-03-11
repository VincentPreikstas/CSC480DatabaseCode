import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

//added line
public class Main {

    public static void main (String[] args)throws IOException {
        String pathNodeFilePath = "PathNodeMap.xlsx";
        String storeDataFilePath = "GriceryStoreData.xlsx";


        //FOLLOWING EXAMPLE TAKEN FROM https://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi
        //Example shows how to iterate over every box in an excel sheet row by row left to right

        /*
        String excelFilePath = "PathNodeMap.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        for (Row nextRow : firstSheet) {
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
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
                System.out.print(" ");
            }
            System.out.println();
        }
        workbook.close();
        inputStream.close();
         */
        //END EXAMPLE FROM https://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi

        //START REAL PARSING
        //---------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------

        DataInputFunctions myDataInputTool = new DataInputFunctions();

        try {

            //Flushing the SQL output each time for convenience
            File myObj = new File("formattedSQL.txt");
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }


            //Create file writing object to be passed to functions
            FileWriter fileWriter = new FileWriter("formattedSQL.txt");

            //PATH NODE INSTANTIATION CALL
            fileWriter.write("-- Path Node Instantiation --\n");
            myDataInputTool.pathNodeInstantiator(pathNodeFilePath, fileWriter);
            fileWriter.write("\n");


            //Closing writing object (file writing is done)
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
