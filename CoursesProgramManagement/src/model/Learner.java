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
public class Learner implements Serializable {
    String id, name, courseId;
    int score;
    LocalDate dob;

    public Learner(String id, String name, String courseId, int score, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.score = score;
        this.dob = dob;
    }

    public Learner() {
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public boolean getStatus(){
        return score >= 50;
    }

    @Override
    public String toString() {
        return "Learner{" + "id=" + id + ", name=" + name + ", courseId=" + courseId + ", score=" + score + ", dob=" + dob + '}';
    }
    
    
}
