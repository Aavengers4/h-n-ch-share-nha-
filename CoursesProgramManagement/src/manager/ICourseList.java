/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manager;

/**
 *
 * @author Lenovo
 */
public interface ICourseList {

    public void addCourse(TopicList topicList);

    public void updateCourse(TopicList topicList, LearnerList learnerList);

    public void deleteCourse();

    public void printAllCourses(TopicList topicList, LearnerList learnerList);
}