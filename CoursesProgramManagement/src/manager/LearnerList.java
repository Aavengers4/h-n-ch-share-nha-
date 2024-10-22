/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import model.Learner;
import java.time.LocalDate;
import java.util.ArrayList;
import utils.Inputter;

/**
 *
 * @author Lenovo
 */
public class LearnerList extends ArrayList<Learner> implements ILearnerList {

    @Override
    public void addLearner(CourseList courseList, TopicList topicList) {
        if(topicList.isEmpty()) {
            System.out.println("Cannot add learner when topic list is empty. Fill topic list and try again.");
            return;
        }
        if(courseList.isEmpty()) {
            System.out.println("Cannot add learner when course list is empty. Fill course list and try again.");
            return;
        }
        boolean confirm, isExist;
        String id, name, courseId;
        int score;
        LocalDate dob;

        do {
            do {
                isExist = false;
                id = Inputter.inputNonBlankStr("Enter Learner ID: ");
                id = id.toUpperCase();
                for (Learner learner : this) {
                    if (learner.getId().equalsIgnoreCase(id)) {
                        isExist = true;
                        System.out.println("Learner ID is duplicated, please try again.");
                        break;
                    }
                }
            } while (isExist);

            name = Inputter.inputNonBlankStr("Enter Learner name: ");
            do {
                courseList.printAllCourses(topicList, this);
                courseId = Inputter.inputNonBlankStr("Enter Course ID: ");
            } while (courseList.getCourseNameFromId(courseId) == null);
            score = Inputter.inputIntLimit("Enter Learner's score: ", 0, 100); // Assuming score range is 0-100
            dob = Inputter.inputDate("Enter Learner's date of birth (YYYY-MM-DD): ");

            confirm = Inputter.confirmation("Do you want to add this Learner?");
            if (confirm) {
                Learner learner = new Learner(id, name, courseId, score, dob);
                this.add(learner);
                System.out.println("Learner added successfully.");
            } else {
                System.out.println("Action discarded.");
            }

            confirm = Inputter.confirmation("Do you want to add another Learner?");
            if (!confirm) {
                System.out.println("Returning to menu...");
            }
        } while (confirm);
    }

    @Override
    public void updateScore(CourseList courseList) {
        if (this.isEmpty()) {
            System.out.println("Learner list is currently empty. Returning to main menu...");
            return;
        }
        if(courseList.isEmpty()) {
            System.out.println("Cannot update learner when course list is empty. Fill course list and try again.");
            return;
        }
        
        boolean confirm, isFound;
        Learner selectedLearner = new Learner();
        String id;
        int newScore;

        do {
            printAllLearners(courseList); 
            id = Inputter.inputNonBlankStr("Enter Learner ID to update score: ");
            isFound = false;

            // Search for the learner with the given ID
            for (Learner learner : this) {
                if (learner.getId().equalsIgnoreCase(id)) {
                    selectedLearner = learner;
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Learner ID does not exist.");
            } else {
                // Update the learner's score
                newScore = Inputter.inputIntLimit("Enter new score for the learner: ", 0, 100);

                confirm = Inputter.confirmation("Do you want to update the learner's score?");
                if (confirm) {
                    selectedLearner.setScore(newScore); 
                    System.out.println("Learner's score updated successfully.");
                } else {
                    System.out.println("Action discarded.");
                }
            }

            confirm = Inputter.confirmation("Do you want to update another learner's score?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }

    @Override
    public void printAllLearners(CourseList courseList) {

        if(courseList.isEmpty()) {
            System.out.println("Cannot print learner when course list is empty. Fill course list and try again.");
            return;
        }
        
        if (this.isEmpty()) {
            System.out.println("Learner list is currently empty.");
            return;
        }
        for (Learner learner : this) {

            System.out.println("Id: " + learner.getId() + " | Name: " + learner.getName() + "| Date of birth: " + learner.getDob()
                    + " | Course: " + courseList.getCourseNameFromId(learner.getCourseId())
                    + " | Score: " + learner.getScore() + " | Status: " + learner.getStatus());
        }
        System.out.println("Total: " + this.size() + " learner(s).");
    }
    
    public int getPassedQuantityInCourse(String id){
        int count = 0;
        for (Learner learner : this) {
            if(learner.getCourseId().equalsIgnoreCase(id) && learner.getStatus())
                count++;
        }
        return count;
    }
    public int getLearnerQuantityInCourse(String id){
        int count = 0;
        for (Learner learner : this) {
            if(learner.getCourseId().equalsIgnoreCase(id))
                count++;
        }
        return count;
    }
}
