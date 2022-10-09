package dimka.blin.LaTeXexpr;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class StringExpression {
    /**
     * List that used to store the expression in latex and normal style
     * @param latex
     * @param expression
     */
    String latex;
    String expression;

    public StringExpression(String latex, String expression) {
        this.latex = latex;
        this.expression = expression;
    }

    public Double getAnswer() {
        Expression expression = new ExpressionBuilder(this.getExpression()).build();
        return expression.evaluate();
    }

    public String getLatex() {
        return this.latex;
    }

    public String getExpression() {
        return this.expression;
    }
}
