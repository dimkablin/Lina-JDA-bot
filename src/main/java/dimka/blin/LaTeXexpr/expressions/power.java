package dimka.blin.LaTeXexpr.expressions;

import dimka.blin.LaTeXexpr.Expressionable;

public class power extends Expressionable {
    private String name = "({x})^{2}";
    private Integer level = 25;

    @Override
    public Integer getLevel() {
        return level;
    }
    @Override
    public String getExpression() {
        return "(x)^2";
    }

    @Override
    public String getLatex() {
        return name;
    }
}
