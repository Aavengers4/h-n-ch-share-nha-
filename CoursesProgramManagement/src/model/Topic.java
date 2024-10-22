/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public class Topic implements Serializable, Comparable<Topic> {
    
    private String code, name, title;
    private boolean type; //longterm/shortterm
    private int duration;

    public Topic(String code, String name, String title, boolean type, int duration) {
        this.code = code;
        this.name = name;
        this.title = title;
        this.type = type;
        this.duration = duration;
    }

    public Topic() {
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    private String typeToString(){
        String result = this.type ? "Long term" : "Short term";
        return result;
    }

    @Override
    public String toString() {
        return "Code: " + code + " | name: " + name + " | title: " + title + " | type: " + typeToString() + " | duration (in minutes): " + duration;
    }

    @Override
    public int compareTo(Topic topic) {
        return this.name.compareTo(topic.getName());
    }
    
    
}
