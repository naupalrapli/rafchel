package com.example.crudrasanusantara.model;

public class Data {
    private String id, name, telp;

    public Data(){


    }

    public Data(String id, String name, String telp){
        this.id = id;
        this.name = name;
        this.telp = telp;
    }

    //membuat kodingan untuk mengawakili string/ ID NAME TELP

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
}
