package dimka.blin.Lina;

import dimka.blin.Lina.adapters.Adapter;
import dimka.blin.Lina.adapters.BotProperties;
import dimka.blin.Lina.adapters.MessageLog;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import dimka.blin.Lina.utils.SQLConnector;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class Lina {
    private JDA bot;
    private BotProperties BP;
    public static SQLConnector connector;

    public Lina(String botPropertiesURL, String gameName, OnlineStatus status, SQLConnector connector) throws LoginException, SQLException {
        BP = new BotProperties(botPropertiesURL);
        this.connector = connector;

        try{
            this.bot = JDABuilder.createDefault(BP.getToken()).
                    setActivity(Activity.playing(gameName)).
                    setStatus(status).
                    setAutoReconnect(true).
                    addEventListeners(new MessageLog(this),
                            new Adapter(this)).
                    build();

        } catch (LoginException e){
            System.out.println("Incorrect token");
        }


    }

    public BotProperties getBP() {
        return BP;
    }

    public SQLConnector getConnector() { return connector; }

}
