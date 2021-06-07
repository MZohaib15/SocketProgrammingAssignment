/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap_assign_4;

/**
 *
 * @author mzohaib
 */
public class User {
    private String username;
    private String password;
    
    public User(){}

    public User(String u, String p) {
        username=u;
        password=p;
    }
    String getUsername(){
        return username;
    }
    String getPassword(){
        return password;
    }
    void setUsername(String u){
        username=u;
    }
    void setPassword(String p){
        password=p;
    }
}
