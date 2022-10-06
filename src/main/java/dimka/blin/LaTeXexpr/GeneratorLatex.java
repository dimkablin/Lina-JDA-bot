package dimka.blin.LaTeXexpr;

import javax.crypto.MacSpi;
import java.util.Map;
import java.util.TreeMap;

public class GeneratorLatex {
    private static Map<String, Expressionable> pool = new TreeMap<>();


    /**
     * Create math expression by level
     * @param level
     * @return
     */
    public static String get(Integer level) {
        StringBuilder latex = new StringBuilder(randomChoose(level));
        Integer index;
        for (int i = 0; i < changedNumber(level); i++) {
                index = randomBoolean() ? latex.indexOf("x") : latex.lastIndexOf("x");
                latex.replace(index, index + 1, randomChoose(level));
        }

        while ((index = latex.indexOf("x")) != -1) {
            latex.replace(index, index + 1, randomInteger(level).toString());
        }
        // TODO: determine expression from the pool
        return latex.toString();
    }

    public void addExpressions(Expressionable... expressions) {
        for (Expressionable expression : expressions) {
            pool.put(expression.getName(), expression);
        }
    }

    private static String randomChoose(Integer level) {
        /** random expression from keys of the pool
         * @return String - expression from the pool
         */
        Expressionable expression;
        do {
            expression = pool.get(pool.keySet().toArray()[ (int) Math.round(Math.random()*(pool.size() - 1)) ]);
        } while (level < expression.getLevel());
        return expression.getName();
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
        return (int) (Math.random() * changedNumber(level)) + 1;
    }

    private static Integer changedNumber(Integer level) {
        /**
         * @return Integer - a number from 0 to {level}^0.8
         */
        return (int) Math.pow(level, 0.8) ;
    }

}
