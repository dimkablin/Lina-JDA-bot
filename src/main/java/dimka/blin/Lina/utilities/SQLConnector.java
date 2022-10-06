package dimka.blin.Lina.utilities;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.enums.TextColor;
import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.utilities.BotProperties;
import org.jetbrains.annotations.NotNull;

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

    public static boolean addNewUser(String user_name, String user_id){
        try{

            preparedStatement = connection.prepareStatement("insert into users_list values (?, ?, 0, 1, 0)");
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, user_name);
            preparedStatement.executeUpdate();

            TextColor.GREEN.print("User: \'" + user_name + "\' with user_id: \'" + user_id + "\' was inserted.");
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteUser(String id){
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM users_list WHERE user_id=" + "'" + id + "'");
            preparedStatement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            TextColor.BLUE.print("Program is still working.");
        }

    }

    public static user_list getUser(String user_id) {
        if (!checkUser(user_id)) return null;
        @NotNull
        user_list user = null;

        try {
            if (checkUser(user_id)) {
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM users_list WHERE user_id=" + "'" + user_id + "'");
                if (resultSet.next())
                    user = new user_list(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4),
                            resultSet.getDouble(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            TextColor.BLUE.print("Program is still working.");
        }
        return user;
    }

    public static boolean checkUser(String user_id){
        try{
            ResultSet rs;
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERS_LIST WHERE USER_ID= " + "'"+ user_id + "'");
            if(rs.next())
                return true;
            return false;
        } catch (Exception e){
            e.printStackTrace();
            TextColor.BLUE.print("Program is still working.");
            return false;
        }
    }

    public static boolean addRate(String user_id, Integer tempRate){
        try{
            preparedStatement = connection.prepareStatement("UPDATE USERS_LIST SET RATE = RATE + 1 WHERE USER_ID=" + user_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
