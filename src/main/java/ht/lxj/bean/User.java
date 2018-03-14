package ht.lxj.bean;

import java.io.Serializable;

public class User implements Serializable {
    private Long uid;
    private String uphone;
    private String uname;
    private String ugender;

    public User(){

    }

    public User(String uphone, String uname, String ugender) {
        this.uphone = uphone;
        this.uname = uname;
        this.ugender = ugender;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }
}
