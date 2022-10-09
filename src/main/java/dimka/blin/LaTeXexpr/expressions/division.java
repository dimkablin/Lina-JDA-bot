package dimka.blin.LaTeXexpr.expressions;

import dimka.blin.LaTeXexpr.Expressionable;

public class division extends Expressionable {
    private String name = "\\frac{x}{x}";
    private Integer level = 15;

    @Override
    public Integer getLevel() {
        return level;
    }
    @Override
    public String getExpression() {
        return "(x)/(x)";
    }

    @Override
    public String getLatex() {
        return name;
    }
}
