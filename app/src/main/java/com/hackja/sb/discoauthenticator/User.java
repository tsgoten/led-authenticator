package com.hackja.sb.discoauthenticator;

public class User {
    private String email;
    private String password;
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEqual(User user){
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }
}
