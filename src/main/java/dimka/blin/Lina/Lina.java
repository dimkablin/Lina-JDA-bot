package dimka.blin.Lina;

import dimka.blin.LaTeXexpr.GeneratorLatex;
import dimka.blin.LaTeXexpr.expressions.*;
import dimka.blin.Lina.commands.*;
import dimka.blin.Lina.interfaces.CommandDispatcherable;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.Lina.utilities.Adapter;
import dimka.blin.Lina.utilities.BotProperties;
import dimka.blin.Lina.utilities.CommandDispatcher;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import dimka.blin.Lina.utilities.SQLConnector;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Map;

public class Lina {
    private JDA bot;
    private static BotProperties BP;
    public static SQLConnector connector;
    private CommandDispatcherable<Map<String, Commandable>> commandDispatcher = new CommandDispatcher();
    private static GeneratorLatex generatorLatex = new GeneratorLatex();


    public Lina(String botPropertiesURL, String gameName, OnlineStatus status, SQLConnector connector) throws LoginException, SQLException {
        BP = new BotProperties(botPropertiesURL);
        this.connector = connector;

        try{
            // ADDING COMMANDS HERE
            commandDispatcher.addCommands(new update(), new showMe(), new deleteMe(), new signin(),
                    new latex(), new help(), new randexpr());
            generatorLatex.addExpressions(new addtion(), new division(), new multiplication(), new power(),
                    new subtraction(), new root());

            this.bot = JDABuilder.createDefault(BP.getToken()).
                    setActivity(Activity.playing(gameName)).
                    setStatus(status).
                    setAutoReconnect(true).
                    addEventListeners( // new MessageLog(this), // Logging messages from the server
                            new Adapter(this)).             // handle messages
                    build();

        } catch (LoginException e){
            System.out.println("Incorrect token");
        }
    }

    public static BotProperties getBP() {
        return BP;
    }

    public CommandDispatcherable<Map<String, Commandable>> getCommandDispatcher() {
        return commandDispatcher;
    }

    public SQLConnector getConnector() {
        return connector;
    }

    public static GeneratorLatex getGeneratorLatex() {
        return generatorLatex;
    }

}
