package dimka.blin.Lina.enums;

public class user_list {
    public String user_id;
    public String user_name;
    public Integer rate;
    public Float last_answer;

    public user_list(String user_id, String user_name, Integer rate, Float last_answer){
        this.user_id = user_id;
        this.user_name = user_name;
        this.rate = rate;
        this.last_answer = last_answer;
    }

    @Override
    public String toString(){
        return  "User_id: " + user_id + "\n" +
                "User_name: " + user_name + "\n" +
                "Rate: " + rate + "\n" +
                "Last_answer: " + last_answer;
    }
}
