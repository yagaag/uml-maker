package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class FileManager {

    static String path = "saved/";

    public static boolean validateFileExist(String file) {
        File myFile = new File(path+file);
        System.out.println(file);
        if (myFile.exists()) {
            return true;
        }
        return false;
    }

    public static void save(String file) {
        try {
            FileWriter myWriter = new FileWriter(path+file);
            DrawnClasses drawnClasses = DrawnClasses.getInstance();
            for (UserClass userClass: drawnClasses.getClasses()) {
                myWriter.write(userClass.getTitle() + " " + userClass.xCoord() + " " + userClass.yCoord() + '\n');
                myWriter.write("Connections:" + '\n');
                for (Connection connection: userClass.getConnections()) {
                    myWriter.write(connection.getType().name + " " + connection.getToID() + '\n');
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(String file) {
        try {
            File myFile = new File(path+file);
            Scanner myReader = new Scanner(myFile);
            DrawnClasses drawnClasses = DrawnClasses.getInstance();
            drawnClasses.reset();
            while (myReader.hasNextLine()) {
                String d = myReader.nextLine();
                String[] a = d.split(" ");
                System.out.println(a[0]);
//                drawnClasses.addUserClass((int) a[0], int(a[2]), a[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}