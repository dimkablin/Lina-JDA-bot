package dimka.blin.Lina.utilities;

import dimka.blin.Lina.Lina;
import dimka.blin.Lina.enums.TextColor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.Instant;

public class MessageLog extends ListenerAdapter {
    Lina bot;
    private Color color = new Color(158, 33, 215, 255);

    public MessageLog(Lina bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(final MessageReceivedEvent event){

        String channelId = event.getChannel().getId();
        String member = event.getAuthor().getAsTag();
        String message = event.getMessage().getContentRaw();
        String messageId = event.getMessageId();
        String jumpURL = event.getJumpUrl();

        StringBuilder logToConsole = new StringBuilder("");


        // not allow to log itself
        if (event.getAuthor().isBot()) return;

        // if category is null
        try {
            if (event.getMessage().getCategory().getId().compareTo(bot.getBP().getChannelID()) != 0) {
                //return;
            }
        } catch (NullPointerException e) {
            return;
        }


        // create empty message
        EmbedBuilder log = new EmbedBuilder();

        log.setColor(event.getMember().getColor()).
                setAuthor(event.getAuthor().getAsTag()).
                setTimestamp(Instant.now()).
                appendDescription("["+message+"]("+jumpURL+")");

        // Send log message to log's channel
        event.getJDA().getGuildById(this.bot.getBP().getServerID()).
                getTextChannelById(this.bot.getBP().getLogChannelID()).
                sendMessageEmbeds(log.build()).queue();

        // Print incoming message
        TextColor.BLUE.print(logToConsole
                .append("\n\tChannel: ").append(channelId)
                .append("\n\tMember: ").append(member)
                .append("\n\tMessage: ").append(message)
                .toString());
        log.clear();
    }

}
