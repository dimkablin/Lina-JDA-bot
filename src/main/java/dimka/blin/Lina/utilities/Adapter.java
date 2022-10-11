package dimka.blin.Lina.utilities;

import dimka.blin.Lina.enums.TextColor;
import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.Lina;
import dimka.blin.Lina.interfaces.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
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
        // TODO: nothing
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Double answer;

        // if message is not command then handle it out
        EmbedBuilder returningMessage = bot.getCommandDispatcher().handle(event);
        try {
            // send answer
            event.getChannel().sendMessageEmbeds(returningMessage.build()).queue();
        } catch (NullPointerException e) {
            TextColor.PURPLE.print("Commandable returned null.");
        }
    }


    public LinkedList<Commandable> getHistory() {
        return this.history;
    }

    public static Double isDouble(String line) {
        try {
            return Double.valueOf(line);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


}
