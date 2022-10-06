package dimka.blin.Lina.interfaces;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.LinkedList;

public interface CommandDispatcherable<T> {
    /**
     * Adding command to SOME_LIST
     * @param commands
     */
    public void addCommands(Commandable... commands);

    /**
     * getting all command list
     * @return
     */
    public T getCommandList();

    /**
     * Gets 5 last names of command, in other words - history
     *
     * @return LinkedList<Commandable>
     */
    public LinkedList<Commandable> getHistory();

    /**
     * Adding command to the history
     * Max length of history is five
     *
     * @param command
     */
    public void addToHistory(Commandable command);
    /**
     * Interface of any adapters that working with commands
     *
     * @param event
     */
    public EmbedBuilder handle(MessageReceivedEvent event);
}
