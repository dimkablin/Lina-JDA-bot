package dimka.blin.Lina.utils;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.adapters.BotProperties;

import java.sql.*;

public class SQLConnector {
    private static final String url = "jdbc:postgresql://localhost:5555/postgres";
    private static String user = null;
    private static String password = null;
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private BotProperties botProperties;
    private static ResultSet resultSet;
    private static Statement stmt;

    public SQLConnector(String botPropertiesURL) {
        botProperties = new BotProperties(botPropertiesURL);
        this.user = botProperties.getUser();
        this.password = botProperties.getPassword();
    }

    public static boolean connectionToDB() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addNewUser(String user_name, String user_id){
        try{

            preparedStatement = connection.prepareStatement("insert into users_list values (?, ?, 0, 0)");
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, user_name);
            preparedStatement.executeUpdate();

            Color.GREEN.print("User: \'" + user_name + "\' with user_id: \'" + user_id + "\' was inserted.");
            return true;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public user_list updateUser(String user_name, String user_id){
        try{
            if (checkUser(user_id)) {
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM users_list WHERE user_id=" + user_id);
                if (resultSet.next())
                    return new user_list(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getFloat(4));
                return null;
            }
                //
            else
                addNewUser(user_name, user_id);
            return new user_list(user_id, user_name, null, null);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean checkUser(String user_id){
        try{
            stmt = connection.createStatement();
            stmt.execute("SELECT * FROM USERS_LIST WHERE USER_ID= " + user_id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean rateUser(String user_id, Integer tempRate){
        try{
            preparedStatement = connection.prepareStatement("UPDATE USERS_LIST SET RATE = RATE + 1 WHERE USER_ID=" + user_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
