package dimka.blin.Lina.enums;

public class user_list {
    public String user_id;
    public String user_name;
    public Integer rate;
    public Integer level;
    public Double last_answer;

    public user_list(String user_id, String user_name, Integer rate, Integer level, Double last_answer){
        this.user_id = user_id;
        this.user_name = user_name;
        this.rate = rate;
        this.level = level;
        this.last_answer = last_answer;
    }

    @Override
    public String toString(){
        return new StringBuilder()
                .append("User_id: ").append(user_id).append("\n")
                .append("User_name: ").append(user_name).append("\n")
                .append("Rate: ").append(rate).append("\n")
                .append("Level: ").append(level).append("\n")
                .append("Last_answer: ").append(last_answer).toString();
    }
}
