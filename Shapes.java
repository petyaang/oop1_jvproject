package shapes;

import exception.UnknownShapeException;
import test.TestProgram;

import java.util.*;

public class Shapes {
    private static final List<BasicShape> shapes = new ArrayList<>();

    public List<BasicShape> getShapes(){
        return shapes;
    }

    public void createShape(){
        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String shapeName = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                String fill = scanner.next();
                switch (shapeName) {
                    case "rectangle":
                        int width = scanner.nextInt();
                        int height = scanner.nextInt();
                        shapes.add(new Rectangle(shapeName, x, y, fill, width, height));
                        System.out.println("Successfully created the " + shapeName + "!\n");
                        TestProgram.commands();
                        break;
                    case "circle":
                        int r = scanner.nextInt();
                        shapes.add(new Circle(shapeName, x, y, fill, r));
                        System.out.println("Successfully created the " + shapeName + "!\n");
                        TestProgram.commands();
                        break;
                    case "line":
                        int x1 = scanner.nextInt();
                        int y1 = scanner.nextInt();
                        int strokeWidth = scanner.nextInt();
                        shapes.add(new Line(shapeName, x, y, fill, x1, y1, strokeWidth));
                        System.out.println("Successfully created the " + shapeName + "!\n");
                        TestProgram.commands();
                        break;
                    default:
                        throw new UnknownShapeException("The program does not support such shapes.");
                }
            }
            scanner.close();
        } catch(UnknownShapeException e){
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addShape(BasicShape toBeAdded){
        shapes.add(toBeAdded);
    }

    public String printShapes(){
        for(BasicShape current:shapes){
            System.out.println((shapes.indexOf(current) + 1) + ". " +current);
        }
        if(shapes.isEmpty()){
            return "The list is empty!";
        }
        else
            return "***********************************";
    }

    @Override
    public String toString() {
        return Arrays.toString(shapes.toArray());
    }


    public void eraseShape(){
        try {
            System.out.println("Enter a number of a shape: ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            String name = findShape(num);
            shapes.remove(num-1);
            System.out.println("Erased a " + name + " (" + num + ")");
            TestProgram.commands();
        } catch(IndexOutOfBoundsException e){
            System.out.println("There is no such shape");
        }

    }

    private String findShape(int num){
        String shapeName=null;
        for(BasicShape current:shapes){
            if(num == shapes.indexOf(current) + 1){
                shapeName = current.getShapeName();
            }
        }
        return shapeName;
    }

    public void translateShape(){
        System.out.println("Would you like to translate all shapes or one shape? Write 'all' or 'one':");
        Scanner sc=new Scanner(System.in);
        String ans=sc.nextLine();
        if(ans.equals("all")){
            System.out.println("Enter horizontal and vertical:");
            int h=sc.nextInt();
            int v=sc.nextInt();
            int x=0;
            int y=0;
            boolean flag = changeCoordinates(0, h, v);
            if(flag)
                System.out.println("Translated all shapes!");
            else
                System.out.println("Couldn't translate the shapes!");
        }
        else if(ans.equals("one")){
            System.out.println("Enter the number of the shape, horizontal and vertical:");
            int num=sc.nextInt();
            int h=sc.nextInt();
            int v=sc.nextInt();
            int x=0;
            int y=0;
            boolean flag = changeCoordinates(num, h, v);
            if(flag)
                System.out.println("Translated shape (" + num + ")");
            else
                System.out.println("Couldn't translate the shape!");
        }
        TestProgram.commands();
    }

    private boolean changeCoordinates(int num, int h, int v){
        boolean flag=false;
        int x=0;
        int y=0;
        if(num==0){
            for(BasicShape current:shapes){
                x = current.getX() + h;
                current.setX(x);
                y = current.getY() + v;
                current.setY(y);
                flag = true;
            }
        }
        else{
            for(BasicShape current:shapes){
                if(num==shapes.indexOf(current) + 1) {
                    x = current.getX() + h;
                    current.setX(x);
                    y = current.getY() + v;
                    current.setY(y);
                    flag = true;
                }
            }
        }
        return flag;
    }


    public void withinRegion() {
        try {
            System.out.println("Find shapes in rectangle or circle:");
            Scanner sc = new Scanner(System.in);
            String ans = sc.nextLine();
            if (ans.equals("rectangle")) {
                System.out.println("Write parameters:");
                int x = sc.nextInt();
                int y = sc.nextInt();
                int width = sc.nextInt();
                int height = sc.nextInt();
                List<BasicShape> foundShapes = findShapesInRectangle(x, y, width, height);
                if (!foundShapes.isEmpty())
                    System.out.println(Arrays.toString(foundShapes.toArray()));
                else
                    System.out.println("No shapes are located within rectangle " + x + " " + y + " " + width + " " + height);
            } else if (ans.equals("circle")) {
                System.out.println("Write parameters:");
                int x = sc.nextInt();
                int y = sc.nextInt();
                int r = sc.nextInt();
                List<BasicShape> foundShapes = findShapesInCircle(x, y, r);
                if (!foundShapes.isEmpty())
                    System.out.println(Arrays.toString(foundShapes.toArray()));
                else
                    System.out.println("No shapes are located within circle " + x + " " + y + " " + r);
            }
            else
                throw new UnknownShapeException("Unknown region!");

            TestProgram.commands();

        } catch(UnknownShapeException e){
            System.out.println(e);
        }
    }

    private List<BasicShape> findShapesInRectangle(int x, int y, int width, int height){
        List<BasicShape> foundShapes = new ArrayList<>();
        for (BasicShape current : shapes) {
            if (current instanceof Rectangle) {
                if (current.getX() > x && current.getX() < (x + width) && current.getY() > y && current.getY() < (y + height) && (((Rectangle) current).getWidth() + current.getX()) < (x + width) && (((Rectangle) current).getHeight() + current.getY()) < (y + height)) {
                    foundShapes.add(current);
                }
            } else if (current instanceof Circle) {
                if (current.getX() > x && current.getX() < (x + width) && current.getY() > y && current.getY() < (y + height) && (((Circle) current).getR() + current.getX()) > x && (((Circle) current).getR() + current.getX()) < (x + width) && (((Circle) current).getR() + current.getY()) > y && (((Circle) current).getR() + current.getY()) < (y + height)) {
                    foundShapes.add(current);
                }
            } else if (current instanceof Line) {
                if (current.getX() > x && current.getX() < (x + width) && current.getY() > y && current.getY() < (y + height) && ((Line) current).getX1() > x && ((Line) current).getX1() < (x + width) && ((Line) current).getY1() > y && ((Line) current).getY1() < (y + height)) {
                    foundShapes.add(current);
                }
            }
        }
        return foundShapes;
    }

    private List<BasicShape> findShapesInCircle(int x, int y, int r){
        List<BasicShape> foundShapes = new ArrayList<>();
        for (BasicShape current : shapes) {
            if (current instanceof Rectangle) {
                int d1=(int)Math.sqrt(((x-current.getX()) * (x-current.getX())) + ((y-current.getY()) * (y-current.getY())));
                int d2=(int)Math.sqrt(Math.pow((x-(current.getX() + ((Rectangle) current).getWidth())),2) + ((y-current.getY()) * (y-current.getY())));
                int d3=(int)Math.sqrt(((x-current.getX()) * (x-current.getX())) + Math.pow((y-(current.getY() + ((Rectangle) current).getHeight())),2));
                int d4=(int)Math.sqrt(Math.pow((x-(current.getX() + ((Rectangle) current).getWidth())),2) + Math.pow((y-(current.getY() + ((Rectangle) current).getHeight())),2));
                if (d1 <= r && d2 <= r && d3 <= r && d4 <= r && current.getX() >= x && current.getY() >= y) {
                    foundShapes.add(current);
                }
            } else if (current instanceof Circle) {
                int distSq = (int) Math.sqrt(((x - current.getX()) * (x - current.getX())) + ((y - current.getY()) * (y - current.getY())));
                if ((distSq + ((Circle) current).getR()) <= r) {
                    foundShapes.add(current);
                }
            } else if (current instanceof Line) {
                int dl1=(int)Math.sqrt(((x-current.getX()) * (x-current.getX())) + ((y-current.getY()) * (y-current.getY())));
                int dl2=(int)Math.sqrt(((x-((Line) current).getX1()) * (x-((Line) current).getX1())) + ((y-((Line) current).getY1()) * (y-((Line) current).getY1())));
                if (dl1 <= r && dl2 <= r && current.getX() >= x && current.getY() >= y) {
                    foundShapes.add(current);
                }
            }
        }
        return foundShapes;
    }

}

