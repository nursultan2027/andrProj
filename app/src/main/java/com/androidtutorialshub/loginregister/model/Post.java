package com.androidtutorialshub.loginregister.model;

/**
 * Created by nurs on 10.03.18.
 */

public class Post {

    private int id;
    private String postName;
    private String postDis;
    private String Owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return postName;
    }

    public void setName(String name) {
        this.postName = name;
    }

    public String getPostDis() {
        return postDis;
    }

    public void setPostDis(String postDis) {
        this.postDis = postDis;
    }

    public String getPostOwner() {
        return Owner;
    }

    public void setPostOwner(String Owner) {
        this.Owner = Owner;
    }
}
