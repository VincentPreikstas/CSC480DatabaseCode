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

    public void dataBaseInstantiator(FileWriter fileWriter) throws IOException{
        fileWriter.write("-- Start DB Instantiation --\n\n");

        //LIST DB
        fileWriter.write("-- Start Lists DB Instantiation --\n");
        fileWriter.write("CREATE DATABASE Traveling_Groceries_Lists_DB;\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Lists_DB.ShoppingLists(\n" +
                "\tshoppingListID INT AUTO_INCREMENT,\n" +
                "userID VARCHAR(50) NOT NULL,\n" +
                "\tlistName VARCHAR(100) NOT NULL,\n" +
                "\tlistDateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "listPublicStatus VARCHAR(10) DEFAULT 'private', \n" +
                "listShoppedFlag TINYINT DEFAULT 0,\n" +
                "shopperUserID VARCHAR(50) DEFAULT 'none',\n" +
                "PRIMARY KEY (shoppingListID)\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Lists_DB.ShoppingListContents(\n" +
                "\tshoppingListID INT NOT NULL,\n" +
                "\titemName VARCHAR(100) NOT NULL,\n" +
                "\tquantityItem INT DEFAULT 1,\n" +
                "    itemNote VARCHAR(1000),\n" +
                "    shoppedTime DATETIME,\n" +
                "    itemMissedFlag TINYINT DEFAULT 0,\n" +
                "\tPRIMARY KEY (shoppingListID, itemName),\n" +
                "\tFOREIGN KEY (shoppingListID) \n" +
                "\t\tREFERENCES ShoppingLists (shoppingListID)\n" +
                "\t\tON DELETE CASCADE\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Lists_DB.SharedShoppingLists(\n" +
                "\tuserID VARCHAR(50) NOT NULL,\n" +
                "\tshoppingListID INT NOT NULL,\n" +
                "\tPRIMARY KEY (userID, shoppingListID),\n" +
                "\tFOREIGN KEY (shoppingListID)\n" +
                "\t\tREFERENCES ShoppingLists (shoppingListID)\n" +
                "\t\tON DELETE CASCADE\n" +
                ");\n\n");

        //ITEMS AND NODE DB
        fileWriter.write("-- Start Item and Nodes DB Instantiation --\n");
        fileWriter.write("CREATE DATABASE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB;\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Items (\n" +
                "  \titemName VARCHAR(100) NOT NULL,\n" +
                " \titemDescription VARCHAR(1000) DEFAULT \"None\",\n" +
                " \titemStockBool TINYINT DEFAULT 1,\n" +
                "  \tsaleBool TINYINT DEFAULT 0,\n" +
                "\tpicURI VARCHAR(500) DEFAULT \"None\",\n" +
                "  \tPRIMARY KEY (itemName)\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.PathFindingNodes(\n" +
                "\tpathNodeID INT NOT NULL,\n" +
                "\tnorthNodeID INT,\n" +
                "\tnorthNodeDistance INT,\n" +
                "\teastNodeID INT,\n" +
                "\teastNodeDistance INT,\n" +
                "\tsouthNodeID INT,\n" +
                "\tsouthNodeDistance INT,\n" +
                "\twestNodeID INT,\n" +
                "\twestNodeDistance INT,\n" +
                "PRIMARY KEY(pathNodeID)\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Departments (\n" +
                "    \tdepartmentID INT AUTO_INCREMENT,\n" +
                "    \tdepartmentName VARCHAR(100) NOT NULL,\n" +
                "\t\tPRIMARY KEY(departmentID)\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Stores (\n" +
                "\tstoreID INT AUTO_INCREMENT,\n" +
                "\tstoreName VARCHAR(100) NOT NULL,\n" +
                "\taddress VARCHAR(100) NOT NULL,\n" +
                "\tstoreLayoutPicLocationString VARCHAR (1000),\n" +
                "\tPRIMARY KEY(storeID)\n" +
                "\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Locations (\n" +
                " \tlocationID INT AUTO_INCREMENT,\n" +
                "departmentID INT NOT NULL,\n" +
                "  \taisle INT DEFAULT 0,\n" +
                " \track INT DEFAULT 0,\n" +
                "  \tshelf VARCHAR(10) DEFAULT \"None\",\n" +
                "  \tside VARCHAR(10) DEFAULT \"None\",\n" +
                "  \tPRIMARY KEY (locationID),\n" +
                "\tFOREIGN KEY (departmentID)\n" +
                "\t\tREFERENCES Departments (departmentID)\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.ItemLocationAssociations (\n" +
                "\tlocationID INT NOT NULL,\n" +
                "\titemName VARCHAR(100) NOT NULL,\n" +
                "\tPRIMARY KEY (locationID, itemName),\n" +
                "\tFOREIGN KEY (locationID)\n" +
                "\t\tREFERENCES Locations(locationID)\n" +
                "\t\tON DELETE CASCADE,\n" +
                "\tFOREIGN KEY (itemName)\n" +
                "\t\tREFERENCES Items(itemName)\n" +
                "\t\tON DELETE CASCADE\n" +
                ");\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.LocationPathNodeAssociation (\n" +
                "\tlocationID INT NOT NULL,\n" +
                "\tpathNodeID INT NOT NULL,\n" +
                "\tPRIMARY KEY (locationID, pathNodeID),\n" +
                "\tFOREIGN KEY (locationID)\n" +
                "\t\tREFERENCES Locations(locationID)\n" +
                "\t\tON DELETE CASCADE,\n" +
                "\tFOREIGN KEY (pathNodeID)\n" +
                "\t\tREFERENCES PathFindingNodes(pathNodeID)\n" +
                "\t\tON DELETE CASCADE\n" +
                "\n" +
                ");\n\n");

        //USER DB
        fileWriter.write("-- Start User and Login DB Instantiation --\n");
        fileWriter.write("CREATE DATABASE Traveling_Groceries_User_And_Login_DB;\n\n");

        fileWriter.write("CREATE TABLE Traveling_Groceries_User_And_Login_DB.Users (\n" +
                "\tuserID VARCHAR(50) NOT NULL,\n" +
                "\tuserType VARCHAR (30) NOT NULL,\n" +
                "\temail VARCHAR(100),\n" +
                "\tuserShoppingBool TINYINT DEFAULT 0,\n" +
                "\tshoppedItemsPerHour INT DEFAULT 0,\n" +
                "\tPRIMARY KEY(userID)\n" +
                ");\n\n");

        fileWriter.write("-- End DB Instantiation --\n");

    }

    public void storeInstantiator(FileWriter fileWriter) throws IOException{
        fileWriter.write("INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Stores (storeName, address) VALUES ('Definitely Not Price Chopper', 'Merica St. Oswego');\n");
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

    public void locationItemsAndRelationsInstantiator(String storeInfoFilePath, FileWriter fileWriter) throws IOException{
        int locationID = 0;
        int nodeID = 0;
        int departmentID = 0;
        int aisle = 0;
        int rack = 0;
        String shelf = "";
        String side = "";
        String statement = "";
        String[] items = new String[0];

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
                            //items
                            //System.out.print(cell.getStringCellValue()+ "\n");
                            items = cell.getStringCellValue().split(",");
                            counter++;
                        }
                    }

                    //Write Locations
                    statement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Locations (locationID,departmentID,aisle,rack,shelf,side) VALUES (" + locationID + "," + departmentID + "," + aisle + "," + rack + ",'" + shelf + "','" + side + "');\n";
                    fileWriter.write(statement);

                    //Write Location Node Associations
                    statement = "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.LocationPathNodeAssociation (locationID,pathNodeID) VALUES (" + locationID + "," + nodeID + ");\n";
                    fileWriter.write(statement);

                    //Write Items and Location Associations
                    for (String item : items){
                        item = item.trim();
                        if (item.equals("none")){
                            //Do Nothing
                        } else {
                            statement = "INSERT IGNORE INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Items (itemName) VALUES ('" + item + "');\n";
                            fileWriter.write(statement);
                            statement = "INSERT IGNORE INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.ItemLocationAssociations (locationID,itemName) VALUES (" + locationID +",'" + item + "');\n";
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
    public String itemInsertSQLGenerator (String itemName, String itemDescription, int itemStockNum, boolean saleBool, String picURI){
        return "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.Items (itemName, itemDescription, itemStockNum, saleBool, picURI) VALUES " +
                "(" + itemName + ", " + itemDescription + ", " + itemStockNum + ", " + saleBool + ", " + picURI + ");";
    }



    //Nazar
    public String locationInsertSQLGenerator (int locationID, int aisle, int rack, String shelf, String side){
        return "INSERT INTO Traveling_Groceries_Nodes_Store_Info_And_Categories_DB.PathFindingNodes (locationID, aisle, rack, shelf, side) VALUES " +
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
