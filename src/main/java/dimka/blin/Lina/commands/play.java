package dimka.blin.Lina.commands;

import dimka.blin.LaTeXexpr.StringExpression;
import dimka.blin.LaTeXexpr.GeneratorLatex;
import dimka.blin.LaTeXexpr.LaTeXConverter;
import dimka.blin.Lina.Lina;
import dimka.blin.Lina.enums.Color;
import dimka.blin.Lina.enums.TextColor;
import dimka.blin.Lina.enums.user_list;
import dimka.blin.Lina.interfaces.Commandable;
import dimka.blin.Lina.utilities.CommandDispatcher;
import dimka.blin.Lina.utilities.SQLConnector;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public class play extends Commandable {
    private String name = "play";
    @Override
    public EmbedBuilder execute(MessageReceivedEvent event) {
        EmbedBuilder answer = new EmbedBuilder();
        File imageFile = new File("src/main/resources/images/temp.png");
        user_list user = SQLConnector.getUser(event.getAuthor().getId());
        Double last_answer;

        try {
            Integer level = user.level;

            // Getting <StringExpression>
            StringExpression stringExpression = GeneratorLatex.get(level);
            LaTeXConverter.convertToImage(imageFile, stringExpression.getLatex());

            // send hello-message
            if (user.last_answer == Double.MIN_VALUE) {
                answer.setTitle("Nice to meet you in a little math game.")
                        .appendDescription(new StringBuilder()
                                .append("Hope you will have a lot of fun")
                                .append(user.user_name).append("!"))
                        .setColor(Color.RIGHT_COLOR);
                CommandDispatcher.sendMessage(event, answer);
            }
            answer.clear();

            // send some message
            answer.appendDescription("Your expression by level: " + level)
                    .appendDescription("\nAnswer: " + (last_answer =Math.round(stringExpression.getAnswer()*100)/100.))
                    .setColor(Color.INFO_COLOR);
            CommandDispatcher.sendMessage(event, answer);


            // send LaTeX
            event.getJDA().getGuildById(Lina.getBP().getServerID())
                    .getTextChannelById(event.getChannel().getId())
                    .sendFile(imageFile)
                    .mentionRepliedUser(true).queue();

            SQLConnector.setLastAnswer(user.user_id, last_answer);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            answer.appendDescription("Arguments are not valid.").setColor(Color.WRONG_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
            answer.appendDescription("To get expression you have to be in DB.").setColor(Color.WRONG_COLOR);
        }
        return null;

    }

    @Override
    public String getNameOfCommand() {
        return name;
    }

    @Override
    public String toString() {
        return "a little math game.";
    }
}
