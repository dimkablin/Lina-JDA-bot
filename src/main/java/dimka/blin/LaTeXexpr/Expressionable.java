package dimka.blin.LaTeXexpr;

public abstract class Expressionable {
    /**
     * Interface for any expression that making
     */

    /**
     * Returning expression for exp4j
     * @return String
     */
    public abstract String getExpression();

    /**
     * Getting name of expression
     * @return String
     */
    public abstract String getLatex();
    public abstract Integer getLevel();
}
