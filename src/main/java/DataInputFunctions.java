import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputFunctions {

    public DataInputFunctions (){

    }

    public void pathNodeInstantiator(String nodeFilePath, FileWriter fileWriter) throws IOException{
        try{
            FileInputStream inputStream = new FileInputStream(nodeFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            int workAround = 0;
            for (Row nextRow : firstSheet){

                int[] aRow = new int[9];
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                int count = 0;
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            break;
                        case NUMERIC:
                            aRow[count] = (int) cell.getNumericCellValue();
                            count++;
                            break;
                    }
                }
                if (workAround == 0){
                    workAround ++;
                }
                else if (workAround == 1) {
                    String oneStatement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.PathFindingNodes (pathNodeID, northNodeID, northNodeDistance, eastNodeID, eastNodeDistance, southNodeID, southNodeDistance, westNodeID, westNodeDistance) VALUES (" + aRow[0] + ", " + aRow[1] + ", " + aRow[2] + ", " + aRow[3] + ", " + aRow[4] + ", " + aRow[5] + ", " + aRow[6] + ", " + aRow[7] + ", " + aRow[8] + ");\n";
                    fileWriter.write(oneStatement);
                }
                //String oneStatement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.PathFindingNodes (pathNodeID, northNodeID, northNodeDistance, eastNodeID, eastNodeDistance, southNodeID, southNodeDistance, westNodeID, westNodeDistance) VALUES (" + aRow[0] + ", " + aRow[1] + ", " + aRow[2] + ", " + aRow[3] + ", " + aRow[4] + ", " + aRow[5] + ", " + aRow[6] + ", " + aRow[7] + ", " + aRow[8] + ");\n";
                //fileWriter.write(oneStatement);
            }
            workbook.close();
            inputStream.close();
        }
        catch (Exception e){
            System.out.println("Something went wrong parsing the Path Nodes");
            e.printStackTrace();
        }
    }

    //Nazar
    public String catInsertSQLGenerator (String catName, String catDescription, int catStockNum, boolean saleBool, String picURI){
        return "INSERT INTO database (catName, catDescription, catStockNum, saleBool, picURI) VALUES " +
                "(" + catName + ", " + catDescription + ", " + catStockNum + ", " + saleBool + ", " + picURI + ");";
    }

    //DONT DO
    public void catInstantiator (){
        // Calls catInsertSQLGenerator a lot to generate then execute sql queries
        // may require connection to db with a db object as an argument
        // may require file object to read and parse excel sheet if not done with another function and either passed in here or called here
    }



    //Nazar
    public String locationInsertSQLGenerator (int locationID, int aisle, int rack, String shelf, String side){
        return "INSERT INTO database (locationID, aisle, rack, shelf, side) VALUES " +
                "(" + locationID + ", " + aisle + ", " + rack + ", " + shelf + ", " + side + ");";
    }
    /*
    //DONT DO
    public void locationInstantiator(){
        // Calls Location InterSQL Generator a lot to generate and then execute sql queries
        // Will Likely require a connection object to run queries to the DB
        // May require a file object to read and parse excel sheet (or this could be broken up into other functions)
    }


    //Rudy
    public String travelNodeInsertGenerator (int nodeID, int northNodeID, int northNodeDistance, int etc){
        String SQLStatement;
        return SQLStatement;
    }

    //Rudy
    public String shoppingListInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

    //Matt
    public String userInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

    //Matt
    public String addItemToGroceryListInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }


     */
}
