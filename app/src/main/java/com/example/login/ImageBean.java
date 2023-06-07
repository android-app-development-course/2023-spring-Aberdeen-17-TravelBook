package com.example.login;

public class ImageBean {
    private int id;
    private String user;
    private String Image;

    public ImageBean(int id, String user, String image) {
        this.id = id;
        this.user = user;
        Image = image;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getImage() {
        return Image;
    }
}
