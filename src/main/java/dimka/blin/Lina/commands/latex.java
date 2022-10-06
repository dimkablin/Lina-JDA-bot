package dimka.blin.Lina.commands;

import dimka.blin.Lina.Lina;
import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.enums.TextColor;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.LaTeXexpr.LaTeXConverter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public class latex extends Commandable {
    private String name = "latex";
    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        String latex = event.getMessage().getContentRaw().replace("\\latex ", "");

        File imageFile = new File("src/main/resources/images/temp.png");
        try {
            LaTeXConverter.convertToImage(imageFile, latex);
            EmbedBuilder answer = new EmbedBuilder();

            // send some message
            answer.appendDescription("Your LaTeX expression.")
                            .setColor(Color.INFO_COLOR);

            event.getJDA().getGuildById(Lina.getBP().getServerID())
                    .getTextChannelById(event.getChannel().getId())
                            .sendMessageEmbeds(answer.build()).queue();
            // send LaTeX
            event.getJDA().getGuildById(Lina.getBP().getServerID())
                    .getTextChannelById(event.getChannel().getId())
                    .sendFile(imageFile)
                    .mentionRepliedUser(true).queue();
            return null;
            //answer.appendDescription(imageFile.getPath()).setColor(Color.INFO_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
            TextColor.BLUE.print("Program caught some exception from LateXConverter.convertToImage()\n" +
                    "but it is still working.");
            return new EmbedBuilder().appendDescription("Oops, something went wrong in LaTeXConverter.\nTry it again.").setColor(Color.WRONG_COLOR);
        }
    }

    @Override
    public String getNameOfCommand() {
        return name;
    }

    @Override
    public String toString() {
        return "returning a latex expression.";
    }
}
