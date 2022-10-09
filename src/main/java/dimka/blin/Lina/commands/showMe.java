package dimka.blin.Lina.commands;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.interfaces.Commandable;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static dimka.blin.Lina.utilities.SQLConnector.*;

public class showMe extends Commandable {
    private String nameOfCommand = "showme";
    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        EmbedBuilder msg = new EmbedBuilder();

        user_list user = getUser(event.getAuthor().getId());
        if (user == null) {
            return msg.appendDescription("Oops, seems like you aren't in the database.")
                    .setColor(Color.WRONG_COLOR);
        }

        msg.appendDescription("User: "+ user.user_name +
                //"\nID: " + user.user_id +
                "\nRate: " + user.rate +
                "\nYour level is: " + user.level)
                .setColor(Color.INFO_COLOR);
        return msg;
    }

    @Override
    public String getNameOfCommand() {
        return nameOfCommand;
    }

    @Override
    public String toString(){
        return "returning user's information by ID.";
    }
}
