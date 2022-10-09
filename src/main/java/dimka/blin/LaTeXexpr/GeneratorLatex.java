package dimka.blin.LaTeXexpr;

import java.util.Map;
import java.util.TreeMap;

public class GeneratorLatex {
    private static Map<String, Expressionable> pool = new TreeMap<>();


    /**
     * Create math expression by level
     * @param level
     * @return
     */
    public static StringExpression get(Integer level) {
        Expressionable randomChoose = randomChoose(level);
        Boolean randomBoolean;
        String randomInteger;

        StringBuilder latex = new StringBuilder("\\displaystyle").append(randomChoose.getLatex());
        StringBuilder expression = new StringBuilder().append(randomChoose.getExpression());

        Integer indexLatex;
        Integer indexExpression;
        // Create formula
        for (int i = 0; i < changedNumber(level); i++) {
            randomChoose = randomChoose(level);
            randomBoolean = randomBoolean();

            indexLatex = randomBoolean ? latex.indexOf("x") : latex.lastIndexOf("x");
            indexExpression = randomBoolean ? expression.indexOf("x") : expression.lastIndexOf("x");

            latex.replace(indexLatex, indexLatex + 1, randomChoose.getLatex());
            expression.replace(indexExpression, indexExpression + 1, randomChoose.getExpression());


        }
        // Fill formula with numbers that dependent on level
        while ((indexLatex = latex.indexOf("x")) != -1 & (indexExpression = expression.indexOf("x")) != -1) {
            randomInteger = randomInteger(level).toString();
            latex.replace(indexLatex, indexLatex + 1,  randomInteger);
            expression.replace(indexExpression, indexExpression + 1, randomInteger);
        }

        return new StringExpression(latex.toString(), expression.toString());
    }

    public void addExpressions(Expressionable... expressions) {
        for (Expressionable expression : expressions) {
            pool.put(expression.getLatex(), expression);
        }
    }

    private static Expressionable randomChoose(Integer level) {
        /** random expression from keys of the pool
         * @return String - expression from the pool
         */
        Expressionable expression;
        do {
            expression = pool.get(
                    pool.keySet().toArray()[                              // take from the pool
                    (int) Math.round(Math.random()*(pool.size() - 1)) ]); // randomly object
        } while (level < expression.getLevel());

        return expression;
    }

    private static boolean randomBoolean() {
        /**
         * random boolean - heads or tails
         * @return boolean
         */
        return ((int) Math.random()*1000) % 2 == 0;
    }

    private static Integer randomInteger(Integer level) {
        /**
         * random number that used to final formula
         * @return Integer - a random number [from 1 to changedNumber() + 1]
         */
        return (int) (Math.random() * changedNumber(level) * 10) + 1;
    }

    private static Integer changedNumber(Integer level) {
        /**
         * @return Integer - a number from 0 to {level}^0.5
         */
        return (int) Math.pow(level, 0.5) ;
    }

    public static String toExpression(String latex) {
        String expr = latex;
        return null;
    }

}
