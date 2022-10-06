package dimka.blin.LaTeXexpr.expressions;

import dimka.blin.LaTeXexpr.Expressionable;

public class multiplication extends Expressionable {
    private String name = "{x}\\cdot{x}";
    private Integer level = 10;

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getExpression() {
        return "(x)*(x)";
    }

    @Override
    public String getName() {
        return name;
    }
}
