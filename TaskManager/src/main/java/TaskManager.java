package pl.coderslab;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TaskManager {

    public static void main(String[] args) {

        //wczytanie pliku
        File file=new File("/home/paulina/Dokumenty/Workshop1/TaskManager/target/tasks.csv");
        StringBuilder reading = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        System.out.println(reading.toString());


        //metoda wyświetlająca opcje dostępne w programie
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        System.out.println(pl.coderslab.ConsoleColors.RESET +"add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

        //metoda pobierająca dane z pliku
        }
        public static void option(String[] args) {

        }
    }




