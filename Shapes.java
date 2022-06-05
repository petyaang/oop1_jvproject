package bg.tu_varna.sit;

import java.util.*;

public class Shapes {
    private static List<BasicShape> shapes = new ArrayList<>();

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
                        Main.menu();
                        break;
                    case "circle":
                        int r = scanner.nextInt();
                        shapes.add(new Circle(shapeName, x, y, fill, r));
                        System.out.println("Successfully created the " + shapeName + "!\n");
                        Main.menu();
                        break;
                    case "line":
                        int x1 = scanner.nextInt();
                        int y1 = scanner.nextInt();
                        int strokeWidth = scanner.nextInt();
                        shapes.add(new Line(shapeName, x, y, fill, x1, y1, strokeWidth));
                        System.out.println("Successfully created the " + shapeName + "!\n");
                        Main.menu();
                        break;
                    default:
                        throw new UnknownShapeException("The program does not support such shapes.");
                }
            }
            scanner.close();
        } catch(UnknownShapeException e){
            System.out.println(e);
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
            String name = null;
            boolean flag = false;
            for (BasicShape current : shapes) {
                if (num == shapes.indexOf(current) + 1) {
                    name = current.getShapeName();
                    flag = true;
                }
            }
            shapes.remove(num-1);
            if (flag)
                System.out.println("Erased a " + name + " (" + num + ")");

            Main.menu();
        } catch(IndexOutOfBoundsException e){
            System.out.println("There is no such shape");
        }

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
            boolean flag=false;
            for(BasicShape current:shapes){
                x = current.getX() + h;
                current.setX(x);
                y = current.getY() + v;
                current.setY(y);
                flag = true;
            }
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
            boolean flag=false;
            for(BasicShape current:shapes){
                if(num==shapes.indexOf(current) + 1) {
                    x = current.getX() + h;
                    current.setX(x);
                    y = current.getY() + v;
                    current.setY(y);
                    flag = true;
                }
            }
            if(flag)
                System.out.println("Translated shape (" + num + ")");
            else
                System.out.println("Couldn't translate the shape!");
        }
        Main.menu();
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
                List<BasicShape> foundShapes = new ArrayList<>();
                boolean flag = false;
                for (BasicShape current : shapes) {
                    if (current instanceof Rectangle) {
                        if (current.getX() >= x && current.getY() >= y && ((Rectangle) current).getWidth() <= width && ((Rectangle) current).getHeight() <= height) {
                            foundShapes.add(current);
                            flag = true;
                        }
                    } else if (current instanceof Circle) {
                        if (current.getX() >= x && current.getY() >= y && ((Circle) current).getR() <= (width / 2) && ((Circle) current).getR() <= (height / 2)) {
                            foundShapes.add(current);
                            flag = true;
                        }
                    } else if (current instanceof Line) {
                        if (current.getX() >= x && current.getY() >= y && ((Line) current).getX1() < (width - x) && ((Line) current).getY1() < (height - y)) {
                            foundShapes.add(current);
                            flag = true;
                        }
                    }
                }
                if (flag)
                    System.out.println(Arrays.toString(foundShapes.toArray()));
                else
                    System.out.println("No shapes are located within rectangle " + x + " " + y + " " + width + " " + height);
            } else if (ans.equals("circle")) {
                System.out.println("Write parameters:");
                int x = sc.nextInt();
                int y = sc.nextInt();
                int r = sc.nextInt();
                List<BasicShape> foundShapes = new ArrayList<>();
                boolean flag = false;
                for (BasicShape current : shapes) {
                    if (current instanceof Rectangle) {
                        if (current.getX() >= (x - r) && current.getY() >= (y - r) && ((Rectangle) current).getWidth() <= (2 * r) && ((Rectangle) current).getHeight() <= (2 * r)) {
                            foundShapes.add(current);
                            flag = true;
                        }
                    } else if (current instanceof Circle) {
                        if (current.getX() > (x - r) && current.getY() > (y - r) && ((Circle) current).getR() < r) {
                            foundShapes.add(current);
                            flag = true;
                        }
                    } else if (current instanceof Line) {
                        if (current.getX() >= (x - r) && current.getY() >= (y - r) && ((Line) current).getX1() < (x - r) && ((Line) current).getY1() < (y - r)) {
                            foundShapes.add(current);
                            flag = true;
                        }
                    }
                }
                if (flag)
                    System.out.println(Arrays.toString(foundShapes.toArray()));
                else
                    System.out.println("No shapes are located within circle " + x + " " + y + " " + r);
            }
            else
                throw new UnknownShapeException("Unknown region!");

            Main.menu();

        } catch(UnknownShapeException e){
            System.out.println(e);
        }
    }

}
