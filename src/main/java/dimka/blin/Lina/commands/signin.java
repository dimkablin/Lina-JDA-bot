package dimka.blin.Lina.commands;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.Lina.utilities.SQLConnector;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class signin extends Commandable {
    private String name = "signin";

    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        EmbedBuilder answer = new EmbedBuilder();
        String[] message = event.getMessage().getContentRaw().replace("\n", "").split(" ");
        if (message.length > 1) {
            SQLConnector.addNewUser(event.getMessage().getContentRaw().replace("\\signin ", ""),
                    event.getAuthor().getId());
        } else {
            SQLConnector.addNewUser(event.getAuthor().getName(), event.getAuthor().getId());
        }

        return answer.setColor(Color.RIGHT_COLOR)
                .appendDescription("You have been added to the database.");
    }

    @Override
    public String getNameOfCommand() {
        return name;
    }
}
