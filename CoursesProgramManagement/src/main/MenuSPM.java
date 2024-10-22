/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import manager.CourseList;
import manager.FileManager;
import manager.LearnerList;
import manager.TopicList;
import utils.Inputter;
import utils.Menu;

/**
 *
 * @author Lenovo
 */
public class MenuSPM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        CourseList courseList = new CourseList();
        TopicList topicList = new TopicList();
        LearnerList learnerList = new LearnerList();

        topicList = FileManager.loadFile("topics.dat");
        courseList = FileManager.loadFile("courses.dat");    
        learnerList = FileManager.loadFile("learners.dat");
        String[] objectOptions = {"Manage Topics", "Manage courses", "Manage Learners", "Save all data to file", "Exit"};
        int choice, choice1, choice2;
        boolean confirm;
        do {
            System.out.println("COURSE PROGRAM MANAGEMENT SYSTEM\n");
            choice = Menu.getChoice(objectOptions);
            switch (choice) {
                case 1:
                    manageTopics(topicList);
                    break;
                case 2:
                    manageCourses(courseList, topicList, learnerList);
                    break;
                case 3:
                    manageLearners(learnerList, courseList, topicList);
                    break;
                case 4:
                    confirm = Inputter.confirmation("Do you want to save all data to file?");
                    if (confirm) {
                        FileManager.saveFile(topicList, "topics.dat");
                        FileManager.saveFile(courseList, "courses.dat");
                        FileManager.saveFile(learnerList, "learners.dat");
                    } else {
                        System.out.println("Action discarded.");
                    }
                    break;
                case 5:
                    confirm = Inputter.confirmation("Do you want to exit this program?");
                    if (!confirm) {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (choice != 5);
    }

    //-----------------------
    public static void manageTopics(TopicList topicList) {
        int choice;
        String[] options = {"Add topic", "Update topic", "Delete topic", "Display all topics", "Return"};
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    topicList.addTopic();
                    break;
                case 2:
                    topicList.updateTopic();
                    break;
                case 3:
                    topicList.deleteTopic();
                    break;
                case 4:
                    topicList.printAllTopics();
                    break;
                case 5:
                     break;
         
                   
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (choice != 5);
    }

    //--------------------------
    public static void manageCourses(CourseList courseList, TopicList topicList, LearnerList learnerList) {
        int choice;
        String[] options = {"Add course", "Update course", "Delete course", "Display all courses", "Return"};

        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    courseList.addCourse(topicList);
                    break;
                case 2:
                    courseList.updateCourse(topicList, learnerList);
                    break;
                case 3:
                    courseList.deleteCourse();
                    break;
                case 4:
                    courseList.printAllCourses(topicList, learnerList);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (choice != 5);
    }

    public static void manageLearners(LearnerList learnerList, CourseList courseList, TopicList topicList) {
        int choice;
        String[] options = {"Add learner to course", "Update learner's score", "Display all learners", "Return"};
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    learnerList.addLearner(courseList, topicList);
                    break;
                case 2:
                    learnerList.updateScore(courseList);
                    break;
                case 3:
                    learnerList.printAllLearners(courseList);
                    break;
                case 4:
                    return;

                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (choice != 5);
    }

}
