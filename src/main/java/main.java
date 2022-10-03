import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.Lina;
import dimka.blin.Lina.utils.SQLConnector;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class main {
    private static String botProperties = "src\\main\\resources\\src\\token.properties";
    private static String gameName = "Ревизор";
    public static SQLConnector connector;

    public static void main(String[] args) throws LoginException, SQLException {
        connector = new SQLConnector(botProperties);
        if (!connector.connectionToDB()) {
            Color.YELLOW.print("Не удалось подключиться к базе данных!");
            System.exit(0);
        }
        Lina bot = new Lina(botProperties, gameName, OnlineStatus.ONLINE, connector);
    }
}
