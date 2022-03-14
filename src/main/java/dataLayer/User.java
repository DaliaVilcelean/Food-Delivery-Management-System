package dataLayer;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private boolean isClient;

    public User(String username,String password,boolean isClient){
        this.username=username;
        this.password=password;
        this.isClient=isClient;

    }

    public String getPassword() {
        return password;
    }

    public boolean isClient() {
        return isClient;
    }

    public String getUsername() {
        return username;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
