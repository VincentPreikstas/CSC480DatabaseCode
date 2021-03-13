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
            }
            workbook.close();
            inputStream.close();
        }
        catch (Exception e){
            System.out.println("Something went wrong parsing the Path Nodes");
            e.printStackTrace();
        }
    }

    public void departmentsInstantiator(String departmentsDataFilePath, FileWriter fileWriter)throws IOException{
        try{
            FileInputStream inputStream = new FileInputStream(departmentsDataFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            int workAround = 0;
            int counter = 0;
            int departmentID = 0;
            String departmentName = "";
            for (Row nextRow : firstSheet){
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case STRING:
                            departmentName = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            departmentID = (int)cell.getNumericCellValue();
                            break;
                    }
                }
                if (workAround == 0){
                    workAround++;
                }
                else if (workAround == 1){
                    String oneStatement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Departments (departmentID,departmentName) VALUES (" + departmentID + ",'" + departmentName + "');\n";
                    fileWriter.write(oneStatement);
                }
            }
            workbook.close();
            inputStream.close();
        }
        catch (Exception e){
            System.out.println("Something went wrong parsing the Departments");
            e.printStackTrace();
        }


    }

    public void locationCategoriesAndRelationsInstantiator(String storeInfoFilePath, FileWriter fileWriter) throws IOException{
        int locationID = 0;
        int nodeID = 0;
        int departmentID = 0;
        int aisle = 0;
        int rack = 0;
        String shelf = "";
        String side = "";
        String statement = "";
        String[] categories = new String[0];

        int workAround = 0;
        int counter = 0;

        try{
            FileInputStream inputStream = new FileInputStream(storeInfoFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            for (Row nextRow : firstSheet){

                if (workAround == 0) {
                    workAround++;
                } else {
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    counter = 0;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (counter == 0){
                            //locationID
                            //System.out.print(cell.getNumericCellValue());
                            //measure++;
                            locationID = (int)cell.getNumericCellValue();
                            counter++;
                        } else if (counter == 1){
                            //nodeID
                            //System.out.print(cell.getNumericCellValue());
                            nodeID = (int)cell.getNumericCellValue();
                            counter++;
                        } else if (counter == 2){
                            //departmentID
                            //System.out.print(cell.getNumericCellValue());
                            departmentID = (int)cell.getNumericCellValue();
                            counter++;
                        } else if (counter == 3){
                            //departmentName
                            //System.out.print(cell.getStringCellValue());
                            counter++;
                        } else if (counter == 4){
                            //aisle
                            //System.out.print(cell.getNumericCellValue());
                            aisle = (int)cell.getNumericCellValue();
                            counter++;
                        } else if (counter == 5){
                            //rack
                            //System.out.print(cell.getNumericCellValue());
                            rack = (int)cell.getNumericCellValue();
                            counter++;
                        } else if (counter == 6){
                            //shelf
                            //System.out.print(cell.getStringCellValue());
                            shelf = cell.getStringCellValue();
                            counter++;
                        } else if (counter == 7) {
                            //side
                            //System.out.print(cell.getStringCellValue());
                            side = cell.getStringCellValue();
                            counter++;
                        } else {
                            //categories
                            //System.out.print(cell.getStringCellValue()+ "\n");
                            categories = cell.getStringCellValue().split(",");
                            counter++;
                        }
                    }

                    //Write Locations
                    statement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Locations (locationID,departmentID,aisle,rack,shelf,side) VALUES (" + locationID + "," + departmentID + "," + aisle + "," + rack + ",'" + shelf + "','" + side + "');\n";
                    fileWriter.write(statement);

                    //Write Location Node Associations
                    statement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.LocationPathNodeAssociation (locationID,pathNodeID) VALUES (" + locationID + "," + nodeID + ");\n";
                    fileWriter.write(statement);

                    //Write Categories And Categories Location Associations
                    for (String category : categories){
                        if (category.equals("none")){
                            //Do Nothing
                        } else {
                            statement = "INSERT IGNORE INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Categories (catName) VALUES ('" + category + "');\n";
                            fileWriter.write(statement);
                            statement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.CatLocationAssociations (locationID,catName) VALUES (" + locationID +",'" + category + "');\n";
                            fileWriter.write(statement);
                        }
                    }
                    fileWriter.write("\n");
                }
            }
            workbook.close();
            inputStream.close();
        }
        catch (Exception e){
            System.out.println("Something went wrong parsing the Store Information");
            e.printStackTrace();
        }
    }

    //Nazar
    public String catInsertSQLGenerator (String catName, String catDescription, int catStockNum, boolean saleBool, String picURI){
        return "INSERT INTO database (catName, catDescription, catStockNum, saleBool, picURI) VALUES " +
                "(" + catName + ", " + catDescription + ", " + catStockNum + ", " + saleBool + ", " + picURI + ");";
    }



    //Nazar
    public String locationInsertSQLGenerator (int locationID, int aisle, int rack, String shelf, String side){
        return "INSERT INTO database (locationID, aisle, rack, shelf, side) VALUES " +
                "(" + locationID + ", " + aisle + ", " + rack + ", " + shelf + ", " + side + ");";
    }

    /*
    //Rudy
    public String shoppingListInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

     */

    /*
    //Matt
    public String userInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

     */
    /*
    //Matt
    public String addItemToGroceryListInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

     */


}
