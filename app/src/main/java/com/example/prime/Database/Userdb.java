package com.example.prime.Database;

/*
 * Created by NatiSt on 1/3/2022.
 */
public class Userdb {
    String UserName, PhoneNO, Password;

    public Userdb() {

    }

    public Userdb(String userName, String phoneNO, String password) {
        UserName = userName;
        PhoneNO = phoneNO;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNO() {
        return PhoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        PhoneNO = phoneNO;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
