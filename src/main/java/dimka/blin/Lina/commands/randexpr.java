package dimka.blin.Lina.commands;

import dimka.blin.LaTeXexpr.GeneratorLatex;
import dimka.blin.LaTeXexpr.LaTeXConverter;
import dimka.blin.Lina.Lina;
import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.Lina.utilities.SQLConnector;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public class randexpr extends Commandable {
    private String name = "randexpr";
    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        EmbedBuilder answer = new EmbedBuilder();
        File imageFile = new File("src/main/resources/images/temp.png");

        try {
            String[] args = event.getMessage().getContentRaw().split(" ");
            Integer level = (args.length > 1 && Integer.valueOf(args[1]) < 100) ?
                    Integer.valueOf(args[1]) :
                    SQLConnector.getUser(event.getAuthor().getId()).level;

            if (level < 0) throw new IllegalArgumentException();
            
            LaTeXConverter.convertToImage(imageFile, GeneratorLatex.get(level));

            // send some message
            answer.appendDescription("Your LaTeX expression by level: " + level)
                    .setColor(Color.INFO_COLOR);

            // send LaTeX
            event.getJDA().getGuildById(Lina.getBP().getServerID())
                    .getTextChannelById(event.getChannel().getId())
                    .sendFile(imageFile)
                    .mentionRepliedUser(true).queue();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            answer.appendDescription("Arguments are not valid.").setColor(Color.WRONG_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
            answer.appendDescription("To get expression you have to be in DB.").setColor(Color.WRONG_COLOR);
        }
        return answer;

    }

    @Override
    public String getNameOfCommand() {
        return name;
    }
}
