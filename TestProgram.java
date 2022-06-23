package test;

import fileoperations.FileCommands;
import shapes.Shapes;

import java.util.Scanner;

public class TestProgram {
    public static Shapes shapeList=new Shapes();
    public static FileCommands fc=new FileCommands();
    public static boolean flagOpenedFile=false;
    public static String path="C:\\Users\\Petya\\IdeaProjects\\oop1_jvproject\\src\\fileoperations\\shapes.svg";

    public static void main(String[] args) {
        commands();
    }

    public static void commands(){
        String command;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("\nEnter your command:");
            command = input.next();
            switch(command){
                case "open":
                    if(!flagOpenedFile) {
                        flagOpenedFile=fc.openFile(path);
                    }
                    else System.out.println("The file is already opened.");
                    break;

                case "create":
                    System.out.println("create (press Enter to quit the input) ");
                    shapeList.createShape();
                    break;

                case "print":
                    System.out.println(shapeList.printShapes());
                    break;

                case "erase":
                    shapeList.eraseShape();
                    break;

                case "translate":
                    shapeList.translateShape();
                    break;

                case "within":
                    shapeList.withinRegion();
                    break;

                case "close":
                    if(flagOpenedFile){
                        System.out.println("Successfully closed shapes.svg!");
                        flagOpenedFile = false;
                    }
                    else
                        System.out.println("The file is already closed.");
                    break;

                case "save":
                    if(flagOpenedFile) {
                        fc.saveFile(path);
                    }
                    else
                        System.out.println("You can't save an unopened file.");
                    break;

                case "saveas":
                    if(flagOpenedFile) {
                        Scanner scanner = new Scanner(System.in);
                        String newPath = scanner.next();
                        fc.saveFile(newPath);
                    }
                    else
                        System.out.println("No permission to save a new file.");
                    break;

                case "help":
                    String help = "The following commands are supported:\n" +
                            "open - opens a given file\n" +
                            "create - creates a new shape\n" +
                            "print - prints all the shapes\n" +
                            "erase - erases a shape\n" +
                            "translate - translates one or all shapes\n" +
                            "within - finds shapes in a region\n" +
                            "close - closes currently opened file\n" +
                            "save - saves the currently open file\n" +
                            "saveas <file> - saves the currently open file in <file>\n" +
                            "help - prints this information\n" +
                            "exit - exits the program \n";
                    System.out.println(help);
                    break;

                case "exit":
                    System.out.println("exiting the program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command!\n");
                    break;
            }
        } while(!command.equals("exit"));
    }

}

