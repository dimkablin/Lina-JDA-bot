package dimka.blin.Lina.utilities;

import dimka.blin.Lina.enums.TextColor;
import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.Lina;
import dimka.blin.Lina.interfaces.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.LinkedList;

public class Adapter extends ListenerAdapter{
    /**
     * Math for Babies
     */
    Lina bot;
    user_list user;
    private LinkedList<Commandable> history = new LinkedList<>();

    Color rightColor = new Color(152, 255, 152, 255);
    Color wrongColor = new Color(255, 161, 161);
    Color infoColor = new Color(176, 124, 218, 255);

    public Adapter(Lina bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(final MessageReceivedEvent event){
        //if (!event.getChannel().getId().equals(this.bot.getBP().getMathForBabiesID())) return;
        if (event.getAuthor().isBot()) return;

        // if message is not command then handle it out
        if (event.getMessage().getContentRaw().startsWith("\\")) {
            EmbedBuilder returningMessage = bot.getCommandDispatcher().handle(event);
            try {
                // send answer
                event.getJDA().getGuildById(this.bot.getBP().getServerID()).
                        getTextChannelById(event.getChannel().getId()).
                        sendMessageEmbeds(returningMessage.build()).queue();
            } catch (NullPointerException e) {
                TextColor.PURPLE.print("Commandable returned null.");
            }

        }
    }


    public LinkedList<Commandable> getHistory() {
        return this.history;
    }


}
