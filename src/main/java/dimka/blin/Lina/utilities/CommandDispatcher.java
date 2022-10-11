package dimka.blin.Lina.utilities;

import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.enums.TextColor;
import dimka.blin.Lina.interfaces.CommandDispatcherable;
import dimka.blin.Lina.interfaces.Commandable;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class CommandDispatcher implements CommandDispatcherable<Map<String, Commandable>> {
    private static Map <String, Commandable> mapOfCommands = new TreeMap<>();
    private static LinkedList<Commandable> history = new LinkedList<>();

    /**
     * Handle the current command out
     */
    public EmbedBuilder handle(SlashCommandInteractionEvent event) {
        try {
            Commandable command = mapOfCommands.get(event.getName());
            addToHistory(command);
            return command.execute(event);
        } catch (Exception e) {
            TextColor.RED.print(e.getMessage());
            EmbedBuilder answer = new EmbedBuilder();
            return answer.setColor(Color.WRONG_COLOR).appendDescription("Cannot find any such command.");
        }
    }

    @Override
    public void addCommands(Commandable... commands) {
        for(Commandable command : commands) {
            mapOfCommands.put(command.getNameOfCommand(), command);
        }
    }

    /**
     * getting all command list
     * @return
     */
    public static Map<String, Commandable> getMapOfCommands() {
        return mapOfCommands;
    }

    @Override
    public LinkedList<Commandable> getHistory() {
        return history;
    }

    @Override
    public void addToHistory(Commandable command) {
        this.getHistory().addFirst(command);
        if (this.getHistory().size() > 5)
            this.getHistory().removeLast();
    }

    public static void sendMessage( MessageReceivedEvent event, EmbedBuilder embedBuilder ) {
        event.getJDA().getGuildById(event.getGuild().getId())
                .getTextChannelById(event.getChannel().getId())
                .sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public void addCommandsToGuild(JDA jda) {
        getMapOfCommands().entrySet().stream().forEach(x ->
                jda.upsertCommand(x.getKey(), x.getValue().toString()).queue());
    }
}
