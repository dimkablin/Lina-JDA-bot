package dimka.blin.Lina.adapters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotProperties {
    private String token;
    private String ID;
    private String logChannelID;
    private String mathForBabiesID;
    private String serverID;
    private String user;
    private String password;
    private String channelID;

    private Properties info = new Properties();

    public BotProperties(String botPropertiesURL){
        try{
            info.load(new FileInputStream(botPropertiesURL));
            this.token = info.getProperty("token");
            this.ID = info.getProperty("ID");
            this.logChannelID = info.getProperty("logChannelID");
            this.mathForBabiesID = info.getProperty("mathForBabiesID");
            this.serverID = info.getProperty("serverID");
            this.user = info.getProperty("user");
            this.password = info.getProperty("password");
            this.channelID = info.getProperty("channelID");

        } catch (IOException e) {
            System.out.println("Cannot find a token file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getID() {
        return ID;
    }

    public String getToken() {
        return token;
    }

    public String getLogChannelID(){
        return logChannelID;
    }

    public String getMathForBabiesID() { return mathForBabiesID; }

    public String getServerID(){
        return serverID;
    }

    public String getUser() { return user;  }

    public String getPassword() { return password; }

    public String getChannelID() { return channelID; }
}
