package dimka.blin.LaTeXexpr.expressions;

import dimka.blin.LaTeXexpr.Expressionable;

public class root extends Expressionable {
    private String name = "\\sqrt{x}";
    private Integer level = 70;

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getExpression() {
        return "(x)^(1/2)";
    }

    @Override
    public String getLatex() {
        return name;
    }
}
