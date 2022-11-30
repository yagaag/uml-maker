package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class FileManager {

    private static String path = "";

    public static boolean validateFileExist(String file) {
        File myFile = new File(path+file);
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
                myWriter.write("n " + userClass.getTitle() + " " + userClass.xCoord() + " " + userClass.yCoord() + '\n');
            }
            for (int i=0; i<drawnClasses.getLength(); i++) {
                UserClass userClass = drawnClasses.getClassByID(i);
                for (Connection connection: userClass.getConnections()) {
                    myWriter.write("c " + connection.getType().name.charAt(0) + " " + i + " " + connection.getToID() + '\n');
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
                if (a[0].charAt(0) == 'n') {
                    drawnClasses.addUserClass(Integer.parseInt(a[2]), Integer.parseInt(a[3]), a[1]);
                } else if (a[0].charAt(0) == 'c') {
                    System.out.println(a[1]);
                    if (a[1].charAt(0) == 'A') {
                        drawnClasses.addConnection(Integer.parseInt(a[2]), Integer.parseInt(a[3]), ConnectionType.ASSOCIATION);
                    } else if (a[1].charAt(0) == 'I') {
                        drawnClasses.addConnection(Integer.parseInt(a[2]), Integer.parseInt(a[3]), ConnectionType.INHERITANCE);
                    } else {
                        drawnClasses.addConnection(Integer.parseInt(a[2]), Integer.parseInt(a[3]), ConnectionType.COMPOSITION);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}