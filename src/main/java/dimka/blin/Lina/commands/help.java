package dimka.blin.Lina.commands;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.Lina.utilities.CommandDispatcher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Map;

public class help extends Commandable {
    private String name = "help";
    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        EmbedBuilder answer = new EmbedBuilder();
        StringBuilder line = new StringBuilder();
        for (Map.Entry<String, Commandable> entry : CommandDispatcher.getCommands().entrySet()) {
            String key = entry.getKey();
            Commandable value = entry.getValue();

            line.append("\\").append(key).append(": ").append(value.toString()).append("\n");
        }
        return answer.appendDescription(line.toString()).setColor(Color.INFO_COLOR);
    }

    @Override
    public String getNameOfCommand() {
        return name;
    }

    @Override
    public String toString() {
        return "showing all allowed commands";
    }
}
