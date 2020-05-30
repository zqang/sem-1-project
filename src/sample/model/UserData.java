package sample.model;

import sample.Global;
import sample.Munny;
import sample.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    public static final String DB_NAME = "UserData.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:/Users/alvin/IdeaProjects/MyPetCat/" + DB_NAME;

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_MUNNY = "munny";


    public static final int INDEX_USER_NAME = 1;
    public static final int INDEX_USER_EMAIL = 2;
    public static final int INDEX_USER_PASSWORD = 3;
    public static final int INDEX_USER_MUNNY = 4;


    public static final String UPDATE_USER_MUNNY = "UPDATE " + TABLE_USER + " SET " +
            COLUMN_USER_MUNNY + " = ? WHERE " + COLUMN_USER_NAME + " = ?";

    public static final String INSERT_USER = "INSERT INTO " + TABLE_USER + '(' +
            COLUMN_USER_NAME + ',' +  COLUMN_USER_EMAIL + ',' + COLUMN_USER_PASSWORD + ',' + COLUMN_USER_MUNNY+ ')' +
            "VALUES(?,?,?,?)";
    public static final String LOGIN_USER = "SELECT * FROM " + TABLE_USER + " WHERE "
            + COLUMN_USER_EMAIL + " = \"";
    public static final String SELECT_USERNAME = "SELECT " + COLUMN_USER_NAME + " FROM " + TABLE_USER + " WHERE "
            + COLUMN_USER_EMAIL + " = \"";

    private PreparedStatement insertUser;
    private PreparedStatement updateMunny;

    private Connection conn;
    private static UserData instance = new UserData();


    private UserData(){

    }
    public static UserData getInstance(){
        return instance;
        //Datasource.getInstance().methodName();
    }

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertUser = conn.prepareStatement(INSERT_USER);
            updateMunny = conn.prepareStatement(UPDATE_USER_MUNNY);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if(updateMunny != null){
                updateMunny.close();
            }

            if(insertUser != null){
                insertUser.close();
            }

            if(conn != null){
                conn.close();
            }
        }catch(SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }



    public int setInsertUser(String username, String email, String password, Munny munny){
        try{
            insertUser.setString(1,username);
            insertUser.setString(2,email);
            insertUser.setString(3,password);
            insertUser.setInt(4,munny.getMunnybalance());
            return insertUser.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<Users> getUserLogin(String email){
        StringBuilder sb = new StringBuilder(LOGIN_USER);
        sb.append(email);
        sb.append("\"");
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sb.toString()))
        {

            List<Users> users = new ArrayList<>();
            while (resultSet.next()){
                Users user = new Users();
                user.setUsername(resultSet.getString(INDEX_USER_NAME));
                user.setEmail(resultSet.getString(INDEX_USER_EMAIL));
                user.setPassword(resultSet.getString(INDEX_USER_PASSWORD));
                user.getMunny().setMunnybalance(resultSet.getInt(INDEX_USER_MUNNY));
                Global.setUsers(user);
                users.add(user);
            }
            return users;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

    }

    public Users getUsername(String email){
        StringBuilder sb = new StringBuilder(SELECT_USERNAME);
        sb.append(email);
        sb.append("\"");
        try(Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sb.toString())){
            Users users = new Users();
            users.setUsername(resultSet.getString(INDEX_USER_NAME));
            return users;
        }catch (SQLException e){
            System.out.println("Get name failed: " + e.getMessage());
            return null;
        }
    }

    public boolean updateMunny(int munny, String name){
        try {
            updateMunny.setDouble(1, munny);
            updateMunny.setString(2,name);
            int affectedRecords = updateMunny.executeUpdate();

            return affectedRecords == 1;
        }catch (SQLException e){
            System.out.println("Munny Update failed: " + e.getMessage());
            return false;
        }
    }

}
