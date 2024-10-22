/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manager;

/**
 *
 * @author Lenovo
 */
public interface ILearnerList {
    public void addLearner(CourseList courseList, TopicList topicList);
    public void updateScore(CourseList courseList);
    public void printAllLearners(CourseList courseList);
    }
