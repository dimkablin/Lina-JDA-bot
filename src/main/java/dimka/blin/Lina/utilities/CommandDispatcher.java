package dimka.blin.Lina.utilities;

import dimka.blin.Lina.interfaces.CommandDispatcherable;
import dimka.blin.Lina.interfaces.Commandable;
import net.dv8tion.jda.api.EmbedBuilder;
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
    public EmbedBuilder handle(MessageReceivedEvent event) {
        try {
            // TODO: handling commands
            String message = event.getMessage().getContentRaw();
            return mapOfCommands.get(message.split(" ")[0].replace("\\", "")).execute(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCommands(Commandable... commands) {
        for(Commandable command : commands) {
            mapOfCommands.put(command.getNameOfCommand(), command);
        }
    }

    @Override
    public Map<String, Commandable> getCommandList() {
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
}
