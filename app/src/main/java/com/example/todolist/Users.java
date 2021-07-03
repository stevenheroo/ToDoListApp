package com.example.todolist;

public class Users {

    /**
     * Here we create a user object ...to be saved in firebase
     * we create two constructors .....1st constructor returns & accepts nothing, the 2nd constructor accepts arguments
     **/
    public String userName, emailAddress;

    //1st constructor,
    public Users() {

    }

    //2nd constructor,
    public Users(String userName, String emailAddress) {
        this.userName = userName;
        this.emailAddress = emailAddress;
    }
}
