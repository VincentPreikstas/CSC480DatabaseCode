public class DataInputFunctions {

    public DataInputFunctions (){

    }



    public String catInsertSQLGenerator (String catName, String catDescription, int catStockNum, boolean saleBool, String picURI){
        return "INSERT INTO database (catName, catDescription, catStockNum, saleBool, picURI) VALUES " +
                "(" + catName + ", " + catDescription + ", " + catStockNum + ", " + saleBool + ", " + picURI + ");";
    }
    /*
    public void catInstantiator (){
        // Calls catInsertSQLGenerator a lot to generate then execute sql queries
        // may require connection to db with a db object as an argument
        // may require file object to read and parse excel sheet if not done with another function and either passed in here or called here
    }



    public String locationInsertSQLGenerator (int locationID, int aisle, int rack, String shelf, String side){
        String SQLStatement;
        return SQLStatement;
    }

    public void locationInstantiator(){
        // Calls Location InterSQL Generator a lot to generate and then execute sql queries
        // Will Likely require a connection object to run queries to the DB
        // May require a file object to read and parse excel sheet (or this could be broken up into other functions)
    }



    public String travelNodeInsertGenerator (int nodeID, int northNodeID, int northNodeDistance, int etc){
        String SQLStatement;
        return SQLStatement;
    }


    public String shoppingListInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

    public String userInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }

    public String addItemToGroceryListInsertGenerator(){
        String SQLStatement;
        return SQLStatement;
    }


     */
}
