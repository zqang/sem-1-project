package sample.model;

import java.sql.*;

public class CatDailyData {

    public static final String DB_NAME = "CatDailyData.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:/Users/alvin/IdeaProjects/MyPetCat/" + DB_NAME;

    public static final String TABLE_CAT_DAILY = "catDaily";
    public static final String COLUMN_CATDAILY_TIME = "time";
    public static final String COLUMN_CATDAILY_FEED = "feed";
    private static final String COLUMN_CATDAILY_HAPPINESS ="happiness";

    public static final String INSERT_CATDAILY_HAPPINESS = "INSERT INTO " + TABLE_CAT_DAILY + '(' +
            COLUMN_CATDAILY_TIME + ',' + COLUMN_CATDAILY_HAPPINESS + ')' + "VALUES(?,?)";
    public static final String INSERT_CATDAILY_FEED = "INSERT INTO " + TABLE_CAT_DAILY + '(' +
            COLUMN_CATDAILY_TIME + ',' + COLUMN_CATDAILY_FEED + ')' + "VALUES(?,?)";

    private PreparedStatement insertCatDailyHappiness;
    private PreparedStatement insertCatDailyFeed;
    private Connection conn ;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertCatDailyFeed = conn.prepareStatement(INSERT_CATDAILY_FEED);
            insertCatDailyHappiness = conn.prepareStatement(INSERT_CATDAILY_HAPPINESS);
            return true;

        }catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if(insertCatDailyHappiness != null){
                insertCatDailyHappiness.close();
            }

            if(insertCatDailyFeed != null){
                insertCatDailyFeed.close();
            }

            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public int setInsertCatDailyHappiness(double happiness, Time time){
        try{
            insertCatDailyHappiness.setTime(1,time);
            insertCatDailyHappiness.setDouble(2,happiness);
            int status = insertCatDailyHappiness.executeUpdate();
            return status;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public int setInsertCatDailyFeed(int feed, Time time){
        try{
            insertCatDailyFeed.setTime(1,time);
            insertCatDailyFeed.setInt(2,feed);
            int status = insertCatDailyFeed.executeUpdate();
            return status;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
}
