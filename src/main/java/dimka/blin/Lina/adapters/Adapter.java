package dimka.blin.Lina.adapters;

import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.Lina;
import dimka.blin.Lina.interfaces.Adapterable;
import dimka.blin.Lina.interfaces.Commandable;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.LinkedList;

public class Adapter extends ListenerAdapter implements Adapterable {
    /**
     * Math for Babies
     */
    Lina bot;
    user_list userList;
    private LinkedList<Commandable> history = new LinkedList<>();

    Color rightColor = new Color(152, 255, 152, 255);
    Color wrongColor = new Color(255, 161, 161);
    Color infoColor = new Color(176, 124, 218, 255);

    public Adapter(Lina bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(final MessageReceivedEvent event){
        /**
         * main code for listener
         */
        if (!event.getChannel().getId().equals(this.bot.getBP().getMathForBabiesID())) return;
        if (event.getAuthor().isBot()) return;

        String member = event.getAuthor().getAsTag();
        String message = event.getMessage().getContentRaw();
        String user_id = event.getAuthor().getId();
        String user_name = event.getAuthor().getName();

        EmbedBuilder msg = new EmbedBuilder();
        msg.setColor(infoColor);

        userList = bot.getConnector().updateUser(user_name, "'" + user_id + "'");
        if (userList == null) {
            msg.setColor(wrongColor);
            msg.setTitle("Что-то пошло не так!");
        }
        else if (userList.last_answer == null )
            msg.setTitle("Привет, " + user_name + "! Твой персонаж был добавлен в игру Math For Babies.");
        else
            msg.setTitle(dimka.blin.Lina.enums.Color.BLUE.print(userList.toString()));

        event.getJDA().getGuildById(this.bot.getBP().getServerID()).
                getTextChannelById(this.bot.getBP().getMathForBabiesID()).
                sendMessageEmbeds(msg.build()).queue();

    }

    /**
     * Gets 5 last names of command, in other words - history
     *
     * @return LinkedList<Commandable>
     */
    public LinkedList<Commandable> getHistory() {
        return this.history;
    }

    /**
     * Adding command to the history
     * Max length of history is five
     *
     * @param command
     */
    private void addToHistory(Commandable command) {
        this.getHistory().addFirst(command);
        if (this.getHistory().size() > 5)
            this.getHistory().removeLast();
    }

    /**
     * Handle the current command out
     */
    @Override
    public void handle(Commandable cmd) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
