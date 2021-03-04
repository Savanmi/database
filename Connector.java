import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class Connector {

    public static Connection connection = null;

    public static boolean connect(String login, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:oracle:thin:@84.237.50.81:1521:";
        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        try {
            connection = DriverManager.getConnection(url, props);
            System.out.print("Connected\n\n");
        } catch (SQLException throwables) {
            System.out.print("wrong login or password\n\n");
            return false;
        }
        return true;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException error) {
            System.out.print("error : connection closing");
            return;
        }
        System.out.print("Connection is closed");
    }


}
