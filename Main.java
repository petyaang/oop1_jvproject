package bg.tu_varna.sit;

import java.util.Scanner;

public class Main {

    public static Shapes shapeList=new Shapes();
    public static FileCommands fc=new FileCommands();
    public static boolean flagOpenedFile=false;
    public static String path="C:\\Users\\Petya\\IdeaProjects\\oop1_project\\shapes.svg";

    public static void main(String[] args) {

        menu();
    }

    public static void menu(){
            int ch;
            Scanner input = new Scanner(System.in);
            do{
                System.out.println("--------MENU-------- \n\n");
                System.out.println("Please select an option:\n" +
                        "1. Open shapes.svg\n" +
                        "2. Create shape\n" +
                        "3. Print shapes\n" +
                        "4. Erase shape\n" +
                        "5. Translate shape(s)\n" +
                        "6. Find shapes in a region\n" +
                        "7. Close file\n" +
                        "8. Save changes to file\n" +
                        "9. Save as\n" +
                        "10. Help\n" +
                        "11. Exit\n");
                System.out.println("Enter your choice:");
                ch = input.nextInt();
                switch (ch) {
                    case 1:
                        if(!flagOpenedFile) {
                            flagOpenedFile = fc.openFile(path);
                        }
                        else System.out.println("The file is already opened.");
                        break;

                    case 2:
                        System.out.println("create (press Enter to quit the input) ");
                        shapeList.createShape();
                        break;

                    case 3:
                        System.out.println(shapeList.printShapes());
                        break;

                    case 4:
                        shapeList.eraseShape();
                        break;

                    case 5:
                        shapeList.translateShape();
                        break;

                    case 6:
                        shapeList.withinRegion();
                        break;

                    case 7:
                        if(flagOpenedFile){
                            System.out.println("Successfully closed shapes.svg!");
                            flagOpenedFile=false;
                        }
                        else
                            System.out.println("The file is already closed.");
                        break;

                    case 8:
                        if(flagOpenedFile) {
                            fc.saveFile(path);
                        }
                        else
                            System.out.println("You can't save an unopened file.");
                        break;

                    case 9:
                        if(flagOpenedFile) {
                            Scanner scanner = new Scanner(System.in);
                            String newPath = scanner.next();
                            fc.saveFile(newPath);
                        }
                        else
                            System.out.println("No permission to save a new file.");
                        break;

                    case 10:
                        String help = "The following commands are supported:\n" +
                                "open <file> - opens <file>\n" +
                                "close - closes currently opened file\n" +
                                "save - saves the currently open file\n" +
                                "saveas <file> - saves the currently open file in <file>\n" +
                                "help - prints this information\n" +
                                "exit - exits the program \n";
                        System.out.println(help);
                        break;

                    case 11:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option in the menu!\n");
                        break;
                }
            } while(ch!=11);
    }

}
