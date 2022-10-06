package dimka.blin.LaTeXexpr.expressions;

import dimka.blin.LaTeXexpr.Expressionable;

public class addtion extends Expressionable {
    private String name = "{x}+{x}";
    private Integer level = 1;

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getExpression() {
        return "(x)+(x)";
    }

    @Override
    public String getName() {
        return name;
    }




}
