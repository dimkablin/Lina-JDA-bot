package dimka.blin.Lina.enums;

public class Color {
    /**
     * Some class that helps with color for Lina
     *
     */
    public static java.awt.Color RIGHT_COLOR = new java.awt.Color(152, 255, 152, 255);
    public static java.awt.Color WRONG_COLOR = new java.awt.Color(255, 161, 161);
    public static java.awt.Color INFO_COLOR = new java.awt.Color(176, 124, 218, 255);

    /**
     * Get random color
     *
     * @return java.awt.Color
     */
    public java.awt.Color getRandomColor() {
        return new java.awt.Color((int) Math.random() * 255,
                (int) Math.random() * 255,
                (int) Math.random() * 255);
    }
}
