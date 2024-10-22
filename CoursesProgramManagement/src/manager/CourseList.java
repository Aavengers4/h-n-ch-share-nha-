/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import model.Course;
import java.time.LocalDate;
import java.util.ArrayList;
import utils.Inputter;
import utils.Menu;

/**
 *
 * @author Lenovo
 */
public class CourseList extends ArrayList<Course> implements ICourseList {

    @Override
    public void addCourse(TopicList topicList) {
        
        if (topicList.isEmpty()) {
            System.out.println("Cannot add course when topic list is empty. Fill topic list and try again.");
            return;
        }
        boolean confirm, isExist;
        String id, name, title, topicId;
        boolean type;
        int fee;
        LocalDate startDate, endDate;

        do {
            do {
                isExist = false;
                id = Inputter.inputNonBlankStr("Enter Course ID: ");
                id = id.toUpperCase();
                for (Course course : this) {
                    if (course.getId().equalsIgnoreCase(id)) {
                        isExist = true;
                        System.out.println("Course ID is duplicated, please try again.");
                        break;
                    }
                }
            } while (isExist);

            name = Inputter.inputNonBlankStr("Enter Course name: ");
            title = Inputter.inputNonBlankStr("Enter Course title: ");
            type = Inputter.confirmation("Enter Course type (Y for online and N for offline): ");
            fee = Inputter.inputIntLimit("Enter Course fee: ", 0, Integer.MAX_VALUE);
            startDate = Inputter.inputDate("Enter Course start date (YYYY-MM-DD): ");
            endDate = Inputter.inputDate("Enter Course end date (YYYY-MM-DD): ");
            do {
                topicList.printAllTopics();
                topicId = Inputter.inputNonBlankStr("Enter Topic ID: ");
                if (topicList.getTopicFromId(topicId) == null) {
                    System.out.println("Topic id not found, please try again.");
                }
            } while (topicList.getTopicFromId(topicId) == null);
            confirm = Inputter.confirmation("Do you want to add this Course?");
            if (confirm) {
                Course course = new Course(id, name, title, type, fee, startDate, endDate, topicId);
                this.add(course);
                System.out.println("Course added successfully.");
            } else {
                System.out.println("Action discarded.");
            }

            confirm = Inputter.confirmation("Do you want to add another Course?");
            if (!confirm) {
                System.out.println("Returning to menu...");
            }
        } while (confirm);
    }

    @Override
    public void updateCourse(TopicList topicList, LearnerList learnerList) {
        if (this.isEmpty()) {
            System.out.println("Course list is currently empty. Returning to main menu...");
            return;
        }
        if (topicList.isEmpty()) {
            System.out.println("Cannot update course when topic list is empty. Fill topic list and try again.");
            return;
        }

        boolean confirm, isFound;
        Course selectedCourse = new Course();
        String id, name, title, topicId, temp;
        boolean type = false;
        int fee;
        LocalDate startDate, endDate;
        final String UPDATE_PREFIX = " (leave blank to retain old data): ";

        do {
            printAllCourses(topicList, learnerList);
            id = Inputter.inputNonBlankStr("Enter Course ID to update: ");
            isFound = false;

            for (Course course : this) {
                if (course.getId().equalsIgnoreCase(id)) {
                    selectedCourse = course;
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Course ID does not exist.");
            } else {
                name = Inputter.inputStr("Enter new Course name" + UPDATE_PREFIX);
                if (name.isEmpty()) {
                    name = selectedCourse.getName();
                }

                title = Inputter.inputStr("Enter new Course title" + UPDATE_PREFIX);
                if (title.isEmpty()) {
                    title = selectedCourse.getTitle();
                }

                do {
                    temp = Inputter.inputStr("Enter new Course type (Y for online, N for offline)" + UPDATE_PREFIX);
                    temp = temp.toUpperCase();
                    switch (temp) {
                        case "Y":
                            type = true;
                            break;
                        case "N":
                            type = false;
                            break;
                        case "":
                            type = selectedCourse.isType();
                            break;
                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                } while (!temp.equalsIgnoreCase("Y") && !temp.equalsIgnoreCase("N") && !temp.isEmpty());

                fee = Inputter.inputIntOrBlank("Enter new Course fee" + UPDATE_PREFIX, 0, Integer.MAX_VALUE);
                if (fee == -1) {
                    fee = selectedCourse.getFee();
                }

                startDate = Inputter.inputDate("Enter new Course start date" + UPDATE_PREFIX);
                if (startDate == null) {
                    startDate = selectedCourse.getStartDate();
                }

                endDate = Inputter.inputDate("Enter new Course end date" + UPDATE_PREFIX);
                if (endDate == null) {
                    endDate = selectedCourse.getEndDate();
                }

                topicId = Inputter.inputStr("Enter new Topic ID" + UPDATE_PREFIX);
                if (topicId.isEmpty()) {
                    topicId = selectedCourse.getTopicId();
                }

                confirm = Inputter.confirmation("Do you want to update this Course?");
                if (confirm) {
                    selectedCourse.setName(name);
                    selectedCourse.setTitle(title);
                    selectedCourse.setType(type);
                    selectedCourse.setFee(fee);
                    selectedCourse.setStartDate(startDate);
                    selectedCourse.setEndDate(endDate);
                    selectedCourse.setTopicId(topicId);
                    System.out.println("Course updated successfully.");
                } else {
                    System.out.println("Action discarded.");
                }
            }

            confirm = Inputter.confirmation("Do you want to update another Course?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }

    @Override
    public void deleteCourse() {
        if (this.isEmpty()) {
            System.out.println("Course list is currently empty.");
            return;
        }

        String id;
        boolean confirm, isFound;
        do {
            isFound = false;
            id = Inputter.inputNonBlankStr("Enter Course ID to delete: ");
            for (Course course : this) {
                if (course.getId().equalsIgnoreCase(id)) {
                    isFound = true;
                    confirm = Inputter.confirmation("Do you want to delete this course: ");
                    if (confirm) {
                        this.remove(course);
                        System.out.println("Course deleted successfully.");
                        break;
                    }
                }
            }

            if (!isFound) {
                System.out.println("This course does not exist.");
            }

            confirm = Inputter.confirmation("Do you want to delete another course?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }

    @Override
    public void printAllCourses(TopicList topicList, LearnerList learnerList) {
        if (this.isEmpty()) {
            System.out.println("Course list is currently empty.");
            return;
        }
        for (Course course : this) {
            int income = learnerList.getLearnerQuantityInCourse(course.getId()) * course.getFee();
            int failed = learnerList.getLearnerQuantityInCourse(course.getId()) - learnerList.getPassedQuantityInCourse(course.getId());
            System.out.println("Id: " + course.getId()
                    + " | Name: " + course.getName()
                    + " | Title: " + course.getTitle()
                    + " | Type: " + course.typeToString()
                    + " | Number of learner passed: " + learnerList.getPassedQuantityInCourse(course.getId())
                    + " | Number of learner failed: " + failed
                    + " | Total income: " + income
                    + " | Start Date: " + course.getStartDate()
                    + " | End Date: " + course.getEndDate()
                    + " | Topic: " + topicList.getTopicFromId(course.getTopicId()));
        }
        System.out.println("Total: " + this.size() + " course(s).");
    }

    public void searchCourses() {
        if (this.isEmpty()) {
            System.out.println("Topic list is currently empty. Returning to main menu...");
            return;
        }
        String keyword;
        boolean confirm, isExist;
        do {
            isExist = false;

            String[] options = {"Search by Topic", "Search by Name", "Return"};
            int choice;
            do {
                //--------------------------------
                choice = Menu.getChoice(options);
                switch (choice) {
                    case 1:

                        isExist = false;
                        keyword = Inputter.inputNonBlankStr("Enter Course's topic: ");

                        for (Course course : this) {
                            if (course.getTopicId().equalsIgnoreCase(keyword)) {
                                System.out.println(course);
                                isExist = true;
                            }
                        }
                        if (!isExist) {
                            System.out.println("Course with such Topic id does not exist.");
                        }
                        break;
                    case 2:

                        isExist = false;
                        keyword = Inputter.inputNonBlankStr("Enter Course's name: ");

                        for (Course course : this) {
                            if (course.getName().toLowerCase().contains(keyword.toLowerCase())) {
                                System.out.println(course);
                                isExist = true;
                            }
                        }
                        if (!isExist) {
                            System.out.println("Course with such name does not exist.");
                        }
                        break;
                    case 3:

                        System.out.println("Returning...");
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (choice != 4);
            //------------------------------
            confirm = Inputter.confirmation("Do you want to perform another search?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);

    }

    String getCourseNameFromId(String id) {
        for (Course course : this) {
            if (course.getName().equalsIgnoreCase(id)) {
                return course.getName();
            }
        }
        return null;
    }

}
