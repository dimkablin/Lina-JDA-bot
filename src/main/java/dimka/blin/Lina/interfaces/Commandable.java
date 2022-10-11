package dimka.blin.Lina.interfaces;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public abstract class Commandable {

    /**
     * Execute command and return message
     * @param event
     * @return
     */
    public abstract EmbedBuilder execute(SlashCommandInteractionEvent event);

    /**
     * Return name of command that used to keep command in the map
     * @return
     */
    public abstract String getNameOfCommand();

}
