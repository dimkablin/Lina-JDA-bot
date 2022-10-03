package dimka.blin.Lina.enums;

public enum Color {
    GREEN("\u001B[32m"),
    YELLOW("\u001b[33m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    STOP("\u001B[0m");

    private String color;


    Color(String color) {
        this.color = color;
    }

    public String toString(){
        return this.color;
    }

    public String print(String msg){
        System.out.println(this.color + msg + STOP);
        return msg;
    }
}
