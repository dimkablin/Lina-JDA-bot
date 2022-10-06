package dimka.blin.Lina.commands;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.Lina.utilities.CommandDispatcher;
import dimka.blin.Lina.utilities.SQLConnector;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class deleteMe extends Commandable {
    private String name = "deleteme";
    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        EmbedBuilder msg = new EmbedBuilder();
        String id = event.getAuthor().getId();

        if (SQLConnector.checkUser(id)) {
            SQLConnector.deleteUser(id);
            return msg.setColor(Color.RIGHT_COLOR)
                    .appendDescription("You have been deleted from database.");
        } else {
            return msg.appendDescription("Oops, seems like you aren't in database.")
                    .setColor(Color.WRONG_COLOR);
        }



    }

    @Override
    public String getNameOfCommand() {
        return name;
    }

    @Override
    public String toString() {
        return "Deleting user from DB.";
    }
}
