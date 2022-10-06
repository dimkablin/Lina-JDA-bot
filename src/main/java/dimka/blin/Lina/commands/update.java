package dimka.blin.Lina.commands;

import dimka.blin.Lina.interfaces.Commandable;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class update extends Commandable {
    private String name = "update";

    public EmbedBuilder execute(MessageReceivedEvent event) {
        return null;
    }

    public String getNameOfCommand() {
        return name;
    }

    @Override
    public String toString() {
        return "update user in database.";
    }
}
