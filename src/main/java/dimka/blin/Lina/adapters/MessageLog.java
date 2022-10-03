package dimka.blin.Lina.adapters;

import dimka.blin.Lina.Lina;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.Instant;

public class MessageLog extends ListenerAdapter {
    Lina bot;
    private Color color = new Color(149, 192, 255, 255);

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
        String categoryID = event.getMessage().getCategory().getId();

        StringBuilder logToConsole = new StringBuilder("");


        // not allow to log itself
        if (event.getChannel().getId().equals(this.bot.getBP().getLogChannelID())) return;
        if (categoryID.compareTo(bot.getBP().getChannelID()) != 0) return;

        // create empty message
        EmbedBuilder log = new EmbedBuilder();

        log.setTimestamp(Instant.now());
        log.setColor(color);
        log.setTitle("Message log");
        log.addField("\tMember", member, false);
        log.addField("\tMessage", "["+message+"]("+jumpURL+")", false);

        // Send log message to log's channel
        event.getJDA().getGuildById(this.bot.getBP().getServerID()).
                getTextChannelById(this.bot.getBP().getLogChannelID()).
                sendMessageEmbeds(log.build()).queue();

        // Print incoming message
        dimka.blin.Lina.enums.Color.BLUE.print(logToConsole
                .append("\n\tChannel: ").append(channelId)
                .append("\n\tMember: ").append(member)
                .append("\n\tMessage: ").append(message)
                .toString());
        log.clear();
    }

}
