package application.gamedemo2;

import java.util.Random;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private boolean status;

    public User(){
        this.id=createID();
        this.status=true;
    }
    public User(String username, String password) {
        this.id = createID();
        this.username = username;
        this.password = password;
        this.status = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String createID(){
        StringBuilder sb=new StringBuilder("yuanshen");
        Random r=new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }
}
