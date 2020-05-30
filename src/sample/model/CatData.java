package sample.model;

import sample.Cat;
import sample.TTL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static sample.model.UserData.COLUMN_USER_NAME;

public class CatData {
    public static final String DB_NAME = "CatData.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:/Users/alvin/IdeaProjects/MyPetCat/" + DB_NAME;


    public static final String TABLE_CAT = "cat";
    public static final String COLUMN_CAT_NAME = "name";
    public static final String COLUMN_CAT_HAPPINESS = "happiness";
    public static final String COLUMN_CAT_HUNGER = "hunger";
    public static final String COLUMN_CAT_LEVEL = "level";
    public static final String COLUMN_CAT_TIMEOFBIRTH = "timeOfBirth";
    public static final String COLUMN_CAT_TIMETOLIVE = "timeToLive";
    public static final String COLUMN_CAT_USERNAME = "username";
    public static final String COLUMN_CAT_FURCOLOUR ="furColour";
    public static final String COLUMN_CAT_EYECOLOUR = "eyeColour";
    public static final String COLUMN_CAT_PERSONALITY = "personality";


    public static final String INSERT_CAT = "INSERT INTO " + TABLE_CAT + '('+
            COLUMN_CAT_NAME + ',' + COLUMN_CAT_HAPPINESS + ',' + COLUMN_CAT_HUNGER + ','
            + COLUMN_CAT_LEVEL + ',' + COLUMN_CAT_TIMEOFBIRTH +',' + COLUMN_CAT_TIMETOLIVE
            + ',' + COLUMN_CAT_USERNAME + ',' + COLUMN_CAT_FURCOLOUR + ',' + COLUMN_CAT_EYECOLOUR
        + ',' + COLUMN_CAT_PERSONALITY +')' + "VALUES(?,?,?,?,?,?,?,?,?,?)" ;
    public static final String INSERT_CAT_USERNAME ="INSERT INTO " + TABLE_CAT + '(' +
            COLUMN_CAT_USERNAME + ')' + "VALUES(?)";
    public static final String SELECT_CAT = "SELECT * FROM " + TABLE_CAT + " WHERE "
            + COLUMN_USER_NAME + " = \"";

//    public static final String UPDATE_CAT_HAPPINESS = "UPDATE " + TABLE_CAT + " SET " +
//            COLUMN_CAT_HAPPINESS + " = ? WHERE " + COLUMN_CAT_NAME + " = ?";
//    public static final String UPDATE_CAT_HUNGER = "UPDATE " + TABLE_CAT + " SET " +
//            COLUMN_CAT_HUNGER + " = ? WHERE " + COLUMN_CAT_NAME + " = ?";
//    public static final String UPDATE_CAT_LEVEL = "UPDATE " + TABLE_CAT + " SET " +
//            COLUMN_CAT_LEVEL + " = ? WHERE " + COLUMN_CAT_NAME + " = ?";
//    public static final String UPDATE_CAT_TIMETOLIVE = "UPDATE " + TABLE_CAT + " SET " +
//            COLUMN_CAT_TIMETOLIVE + " = ? WHERE " + COLUMN_CAT_NAME + " = ?";
    public static final String UPDATE_CAT = "UPDATE " + TABLE_CAT + " SET " + COLUMN_CAT_HAPPINESS + " = ? , "
        + COLUMN_CAT_HUNGER + " = ? ," + COLUMN_CAT_LEVEL + " = ? ,"
        + COLUMN_CAT_TIMETOLIVE + " = ? WHERE " + COLUMN_CAT_USERNAME
        + " = ?";

    //DELETE FROM Students WHERE StudentId = 11 OR StudentId = 12;
    public static final String DELETE_CAT = "DELETE FROM " + TABLE_CAT + " WHERE " + COLUMN_CAT_NAME +
            " = ? AND " + COLUMN_USER_NAME + " = ?";


    private PreparedStatement insertCat;
    private PreparedStatement insertCatUsername;
    private PreparedStatement deleteCat;
    private PreparedStatement updateCatHappiness;
    private PreparedStatement updateCatHunger;
    private PreparedStatement updateCatLevel;
    private PreparedStatement updateCatTimeToLive;
    private PreparedStatement updateCat;

    private Connection conn;
    private static CatData instance = new CatData();

    private CatData(){}

    public static CatData getInstance(){
        return instance;
    }

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            deleteCat = conn.prepareStatement(DELETE_CAT);
            insertCat = conn.prepareStatement(INSERT_CAT);
            insertCatUsername = conn.prepareStatement(INSERT_CAT_USERNAME);
//            updateCatHappiness = conn.prepareStatement(UPDATE_CAT_HAPPINESS);
//            updateCatHunger = conn.prepareStatement(UPDATE_CAT_HUNGER);
//            updateCatLevel = conn.prepareStatement(UPDATE_CAT_LEVEL);
//            updateCatTimeToLive = conn.prepareStatement(UPDATE_CAT_TIMETOLIVE);
            updateCat= conn.prepareStatement(UPDATE_CAT);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if(updateCat != null){
                updateCat.close();
            }

            if(insertCatUsername != null){
                insertCatUsername.close();
            }


            if(deleteCat != null){
                deleteCat.close();
            }

            if(updateCatHunger != null){
                updateCatHunger.close();
            }

            if(updateCatLevel != null){
                updateCatLevel.close();
            }

            if(updateCatTimeToLive != null){
                updateCatTimeToLive.close();
            }

            if(updateCatHappiness != null){
                updateCatHappiness.close();
            }

            if(insertCat != null){
                insertCat.close();
            }

            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }



    public int setInsertCatUsername(String username){
        try{
            insertCatUsername.setString(1, username);
            return insertCatUsername.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public int setInsertCat(String name, double happiness, double hunger, int level, long timeOfBirth, long timeToLive, String username, String furColour, String eyeColour, String personality){
        try{
            insertCat.setString(1,name);
            insertCat.setDouble(2,happiness);
            insertCat.setDouble(3,hunger);
            insertCat.setInt(4,level);
            insertCat.setLong(5,timeOfBirth);
            insertCat.setLong(6,timeToLive);//need to change to long
            insertCat.setString(7,username);
            insertCat.setString(8,furColour);
            insertCat.setString(9,eyeColour);
            insertCat.setString(10,personality);
            return insertCat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<Cat> getCat(String username){
        StringBuilder sb = new StringBuilder(SELECT_CAT);
        sb.append(username);
        sb.append("\"");
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sb.toString()))
        {

            List<Cat> cats = new ArrayList<>();
            while (resultSet.next()){
                Cat cat = new Cat();
                cat.setName(resultSet.getString(1));
                cat.setHappiness(resultSet.getDouble(2));
                cat.setHunger(resultSet.getDouble(3));
                cat.setLevel(resultSet.getInt(4));
                cat.setTimeOfBirth(resultSet.getLong(5));
                cat.setTtl(new TTL(resultSet.getLong(6)));
                cat.setTimeToLive(resultSet.getLong(6));
                cat.setFurColour(resultSet.getString(8));
                cat.setEyeColour(resultSet.getString(9));
                cat.setPersonality(resultSet.getString(10));
                cats.add(cat);
            }
            return cats;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public boolean updateCatHappiness(String name, double newHappiness){
        try {
            updateCatHappiness.setDouble(1, newHappiness);
            updateCatHappiness.setString(2,name);
            int affectedRecords = updateCatHappiness.executeUpdate();

            return affectedRecords == 1;
        }catch (SQLException e){
            System.out.println("Happiness Update failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCatHunger(String name, double newHunger){
        try {
            updateCatHunger.setDouble(1, newHunger);
            updateCatHunger.setString(2,name);
            int affectedRecords = updateCatHunger.executeUpdate();

            return affectedRecords == 1;
        }catch (SQLException e){
            System.out.println("Hunger Update failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCatLevel(String name, int newLevel){
        try {
            updateCatLevel.setInt(1, newLevel);
            updateCatLevel.setString(2,name);
            int affectedRecords = updateCatLevel.executeUpdate();

            return affectedRecords == 1;
        }catch (SQLException e){
            System.out.println("Level Update failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCatTimeToLive(String name, Long newTtl){
        try {
            updateCatTimeToLive.setLong(1, newTtl);
            updateCatTimeToLive.setString(2,name);
            int affectedRecords = updateCatTimeToLive.executeUpdate();

            return affectedRecords == 1;
        }catch (SQLException e){
            System.out.println("Time to live Update failed: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCat(double happiness, double hunger, int level, long timeToLive, String username){
        try{
            updateCat.setDouble(1,happiness);
            updateCat.setDouble(2,hunger);
            updateCat.setInt(3,level);
            updateCat.setLong(4,timeToLive);
            updateCat.setString(5,username);
            int affectedRecords = updateCat.executeUpdate();
            return  affectedRecords ==1;
        }catch (SQLException e){
            System.out.println("Cat Update failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCat(String name,String username){
        try{
            deleteCat.setString(1,name);
            deleteCat.setString(2,username);
            int affectedRecords = deleteCat.executeUpdate();
            return affectedRecords ==1;
        }catch (SQLException e){
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

}
