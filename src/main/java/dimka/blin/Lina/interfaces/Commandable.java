package dimka.blin.Lina.interfaces;

import net.dv8tion.jda.api.events.Event;

import java.util.LinkedList;

public abstract class Commandable {
    private String nameOfCommand;

    public abstract void execute(Event event);

    @Override
    public String toString() {
        return nameOfCommand;
    }
}
