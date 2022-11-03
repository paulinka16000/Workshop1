package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static String[][] dataFromTasks;

    public static void main(String[] args) {
        String FILE_NAME = "tasks.csv";
        printOptions();
        dataFromTasks = loadDataToTab(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            switch (input) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(dataFromTasks);
                    break;
                case "list":
                    listTask(dataFromTasks);
                    break;
                case "exit":
                    exitTask(FILE_NAME);
                    System.out.println(pl.coderslab.ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            printOptions();
        }
    }


    public static void printOptions() {
        System.out.println(pl.coderslab.ConsoleColors.BLUE);
        System.out.println("Please select an option: " + pl.coderslab.ConsoleColors.RESET);
        String[] option = {"add", "remove", "list", "exit"};
        for (String list : option) {
            System.out.println(list);
        }
    }

    public static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String isImportant= scanner.nextLine();
        dataFromTasks = Arrays.copyOf(dataFromTasks, dataFromTasks.length + 1);
        dataFromTasks[dataFromTasks.length - 1] = new String[3];
        dataFromTasks[dataFromTasks.length - 1][0] = description;
        dataFromTasks[dataFromTasks.length - 1][1] = dueDate;
        dataFromTasks[dataFromTasks.length - 1][2] = isImportant;
    }

    public static void removeTask(String[][] tasks) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select number to remove.");
        int number = Integer.parseInt(scanner.nextLine());
        try {
            if (number < tasks.length) {
                dataFromTasks = ArrayUtils.remove(tasks, number);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }
    }

    public static void listTask(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void exitTask(String fileName) {
        Path file = Paths.get(fileName);
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < dataFromTasks.length; i++) {
            String line = String.join(",", dataFromTasks[i]);
            lines.add(line);
        }

        try {
            if (Files.exists(file)) {

                Files.write(file, lines);
            }
        } catch (IOException e) {
            System.out.println("Nie można zapisać pliku.");
        }
    }

    public static String[][] loadDataToTab(String fileName) {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        String[][] tab = null;
        try {
            List<String> strings = Files.readAllLines(dir);
            tab = new String[strings.size()][strings.get(0).split(",").length];
            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;

    }
}
