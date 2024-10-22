/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import model.Topic;
import java.util.ArrayList;
import java.util.Collections;
import utils.Inputter;
/**
 *
 * @author Lenovo
 */
public class TopicList extends ArrayList<Topic> implements ITopicList {

    @Override
    public void addTopic() {
        boolean confirm, isExist;
        String code, name, title, temp;
        boolean type = false;
        int duration;

        do {
            do {
                isExist = false;
                code = Inputter.inputNonBlankStr("Enter Topic's code: ");
                code = code.toUpperCase();
                for (Topic topic : this) { 
                    if (topic.getCode().equals(code)) {
                        isExist = true;
                        System.out.println("Code is duplicated, please try again.");
                        break;
                    }
                }
            } while (isExist);

            name = Inputter.inputNonBlankStr("Enter Topic's name: ");
            title = Inputter.inputNonBlankStr("Enter Topic's title: ");
            do {
                temp = Inputter.inputNonBlankStr("Enter new Topic's type (L for long term, S for short term): ");
                temp = temp.toUpperCase();
                switch (temp) {
                    case "L":
                        type = true;
                        break;
                    case "S":
                        type = false;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            } while (!temp.equalsIgnoreCase("L") && !temp.equalsIgnoreCase("S"));
            duration = Inputter.inputIntLimit("Enter Topic's duration (in minutes): ", 0, 10000);

            confirm = Inputter.confirmation("Do you want to add this Topic?");
            if (confirm) {
                Topic topic = new Topic(code, name, title, type, duration);
                this.add(topic); 
                System.out.println("Topic added successfully.");
            } else {
                System.out.println("Action discarded.");
            }

            confirm = Inputter.confirmation("Do you want to add another Topic?");
            if (!confirm) {
                System.out.println("Returning to menu...");
            }
        } while (confirm);
    }

    @Override
    public void updateTopic() {
     
        if (this.isEmpty()) {
            System.out.println("Topic list is currently empty. Returning to main menu...");
            return;
        }

        boolean confirm, isFound;
        Topic selectedTopic = new Topic();
        String code, name, title, temp;
        boolean type = false;
        int duration;
        final String UPDATE_PREFIX = " (leave blank to retain old data): ";

        do {
            // Display the list of all topics
            printAllTopics();
            code = Inputter.inputNonBlankStr("Enter Topic code to update: ");
            isFound = false;

            // Search for the topic with the entered code
            for (Topic topic : this) {
                if (topic.getCode().equalsIgnoreCase(code)) {
                    selectedTopic = topic;
                    isFound = true;
                    break;
                }
            }

            // If the topic is found, proceed with updates
            if (!isFound) {
                System.out.println("Topic code does not exist.");
            } else {
                // Update fields
                name = Inputter.inputStr("Enter new Topic's name: " + UPDATE_PREFIX);
                if (name.isEmpty()) {
                    name = selectedTopic.getName();
                }

                title = Inputter.inputStr("Enter new Topic's title: " + UPDATE_PREFIX);
                if (title.isEmpty()) {
                    title = selectedTopic.getTitle();
                }
                do {
                    temp = Inputter.inputStr("Enter new Topic's type (L for long term, S for short term) " + UPDATE_PREFIX);
                    temp = temp.toUpperCase();
                    switch (temp) {
                        case "L":
                            type = true;
                            break;
                        case "S":
                            type = false;
                            break;
                        case "":
                            type = selectedTopic.isType();
                            break;
                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                } while (!temp.equalsIgnoreCase("L") && !temp.equalsIgnoreCase("S") && !temp.isEmpty());
                duration = Inputter.inputIntOrBlank("Enter new Topic's duration (in minutes): " + UPDATE_PREFIX, 0, 10000);
                if (duration == -1) {
                    duration = selectedTopic.getDuration();
                }

                // Ask for confirmation before updating
                confirm = Inputter.confirmation("Do you want to update this topic?");
                if (confirm) {
                    selectedTopic.setName(name);
                    selectedTopic.setTitle(title);
                    selectedTopic.setType(type);
                    selectedTopic.setDuration(duration);
                    System.out.println("Topic updated successfully.");
                } else {
                    System.out.println("Action discarded.");
                }
            }

            // Check if the user wants to update another topic
            confirm = Inputter.confirmation("Do you want to update another topic?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }

    @Override
    public void deleteTopic() {
        if (this.isEmpty()) {
            System.out.println("Topic list is currently empty.");
            return;
        }

        String code;
        boolean confirm, isFound;
        do {
            isFound = false;
            code = Inputter.inputNonBlankStr("Enter topic code to delete: ");
            for (Topic topic : this) {
                if (topic.getCode().equalsIgnoreCase(code)) {
                    isFound = true;
                    confirm = Inputter.confirmation("Do you want to delete this topic: ");
                    if (confirm) {
                        this.remove(topic);
                        System.out.println("Topic deleted successfully.");
                        break;
                    }
                }
            }

            if (!isFound) {
                System.out.println("This topic does not exist.");
            }

            confirm = Inputter.confirmation("Do you want to delete another topic?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);
    }

    @Override
    public void printAllTopics() {
        if (this.isEmpty()) {
            System.out.println("Topic list is currently empty.");
            return;
        }
        Collections.sort(this);
        for (Topic topic : this) {
            System.out.println(topic);
        }
        System.out.println("Total: " + this.size() + " topic(s).");
    }

    @Override
    public void searchTopic() {
        if (this.isEmpty()) {
            System.out.println("Product list is currently empty.");
            return;
        }
        String keyword;
        int searchListCount;
        boolean confirm;
        do {
            searchListCount = 0;
            keyword = Inputter.inputNonBlankStr("Enter topic name to search: ");
            for (Topic topic : this) {
                if (topic.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    searchListCount++;
                    System.out.println(topic);
                }
            }
            if (searchListCount == 0) {
                System.out.println("No topic with such name exist.");
            } else {
                System.out.println("Total: " + searchListCount + " topic(s).");
            }
            confirm = Inputter.confirmation("Do you want to search for another topic?");
            if (!confirm) {
                System.out.println("Returning to main menu...");
            }
        } while (confirm);

    }

    String getTopicFromId(String id) {
        for (Topic topic : this) {
            if (topic.getCode().equalsIgnoreCase(id)) {
                return topic.getName();
            }
        }
        return null;
    }
}
