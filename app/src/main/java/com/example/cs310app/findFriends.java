package com.example.cs310app;

public class findFriends {

    public  String profileImage, fullName;

    public findFriends(){

    }


    public String getProfileImage() {
        return profileImage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public findFriends(String profileImage, String fullName){



        this.profileImage = profileImage;
        this.fullName = fullName;


    }
}
