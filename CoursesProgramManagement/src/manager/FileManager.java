/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Lenovo
 */
public class FileManager {
    public static <T> void saveFile(T list, String filename){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
            System.out.println("Save file successfully");
        } catch (Exception e) {
            System.out.println("saveFile Error: "+e.toString());
        }
    }
    
    public static <T> T loadFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            T list = (T) ois.readObject();
            System.out.println("Load file successfully.");
            return list;
        } catch (Exception e) {
            System.out.println("loadFile Error: " + e.toString());
            return null;
        }
    }
}
