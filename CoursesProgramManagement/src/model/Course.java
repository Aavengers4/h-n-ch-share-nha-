/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class Course implements Serializable, Comparable<Course>{
    String id, name, title;
    boolean type, status; //onl/off; pass/fail
    int fee;
    LocalDate startDate, endDate;
    String topicId;
    

    public Course(String id, String name, String title, boolean type, int fee, LocalDate startDate, LocalDate endDate, String topicId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.type = type;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.topicId = topicId;
    }

    public Course() {
    }
    

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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    
    public  String typeToString(){
        String result = this.type ? "Online" : "Offline";
        return result;
    }
    

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", title=" + title + ", type=" + typeToString() + ", fee=" + fee + ", startDate=" + startDate + ", endDate=" + endDate + ", topicId=" + topicId + '}';
    }

    @Override
    public int compareTo(Course course) {
        return this.startDate.compareTo(course.getStartDate());
    }
    
}
