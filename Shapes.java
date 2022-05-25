package bg.tu_varna.sit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Shapes {
    private List<BasicShape> shapes = new ArrayList<>();
    public static boolean method1Called=false;
    public static boolean method2Called=false;
    public static boolean method3Called=false;
    public static boolean method4Called=false;

    public Shapes(){}

    public void addShapesFromFile(){
        String fileName="shapes.svg";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            //Document doc = builder.parse("C:\\Users\\Petya\\IdeaProjects\\oop1_project\\shapes.svg");
            NodeList svgList = doc.getElementsByTagName("svg");
            for(int i=0;i<svgList.getLength();i++) {
                Node s = svgList.item(i);
                if (s.getNodeType() == Node.ELEMENT_NODE) {
                    Element svg = (Element) s;
                    //String shapeName = svg.getAttribute("rect");

                    NodeList coordinates = svg.getChildNodes();
                    for (int j = 0; j < coordinates.getLength(); j++) {
                        Node c = coordinates.item(j);
                        if (c.getNodeType() == Node.ELEMENT_NODE) {
                            Element coordinate = (Element) c;
                            if (coordinate.getTagName().equals("rect")) {
                                int x = Integer.parseInt(coordinate.getAttribute("x"));
                                int y = Integer.parseInt(coordinate.getAttribute("y"));
                                int width = Integer.parseInt(coordinate.getAttribute("width"));
                                int height = Integer.parseInt(coordinate.getAttribute("height"));
                                String fill = coordinate.getAttribute("fill");
                                shapes.add(new Rectangle("rectangle", x, y, fill, width, height));
                                /*System.out.println(coordinate.getTagName() + " "
                                        + x + " " + y + " " + width + " " + height + " " + fill);*/
                            }

                            else if (coordinate.getTagName().equals("circle")) {
                                int cx = Integer.parseInt(coordinate.getAttribute("cx"));
                                int cy = Integer.parseInt(coordinate.getAttribute("cy"));
                                int r = Integer.parseInt(coordinate.getAttribute("r"));
                                String fill = coordinate.getAttribute("fill");
                                shapes.add(new Circle("circle", cx, cy, fill, r));
                                /*System.out.println(coordinate.getTagName() + " "
                                        + cx + " " + cy + " " + r + " " + fill);*/
                            }

                            else if (coordinate.getTagName().equals("line")) {
                                int x1 = Integer.parseInt(coordinate.getAttribute("x1"));
                                int y1 = Integer.parseInt(coordinate.getAttribute("y1"));
                                int x2 = Integer.parseInt(coordinate.getAttribute("x2"));
                                int y2 = Integer.parseInt(coordinate.getAttribute("y2"));
                                String stroke = coordinate.getAttribute("stroke");
                                int strokeWidth = Integer.parseInt(coordinate.getAttribute("stroke-width"));
                                shapes.add(new Line("line", x1, y1, stroke, x2, y2, strokeWidth));
                                /*System.out.println(coordinate.getTagName() + " "
                                        + x1 + " " + y1 + " " + x2 + " " + y2 + " " + stroke + " " + strokeWidth);*/
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createShape(){
        method1Called=true;

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String shapeName=scanner.next();
            int x= scanner.nextInt();
            int y=scanner.nextInt();
            String fill=scanner.next();
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
            }
        }
        scanner.close();
    }

    /*public List<BasicShape> getShapes() {
        return shapes;
    }

    public void setShapes(List<BasicShape> shapes) {
        this.shapes = shapes;
    }*/

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
        method2Called=true;

        try {
            System.out.println("Enter a number of a shape: ");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            String name = null;
            boolean flag = false;
            for (BasicShape current : shapes) {
                if (num == shapes.indexOf(current)) {
                    name = current.getShapeName();
                    flag = true;
                }
            }
            shapes.remove(num);
            if (flag)
                System.out.println("Erased a " + name + " (" + num + ")");
            else
                System.out.println("There is no shape (" + num + ")");

            Main.menu();
        } catch(IndexOutOfBoundsException e){
            System.out.println("There is no such shape");
        }

    }

    public void translateShape(){
        method3Called=true;

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
                if(num==shapes.indexOf(current)) {
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


    public void withinRegion(){
        method4Called=true;

        System.out.println("Find shapes in rectangle or circle:");
        Scanner sc=new Scanner(System.in);
        String ans=sc.nextLine();
        if(ans.equals("rectangle")) {
            System.out.println("Write parameters:");
            int x=sc.nextInt();
            int y=sc.nextInt();
            int width=sc.nextInt();
            int height=sc.nextInt();
            List<BasicShape> foundShapes=new ArrayList<>();
            boolean flag=false;
            for(BasicShape current:shapes){
                if(current instanceof Rectangle){
                    if(current.getX() >= x && current.getY() >= y && ((Rectangle) current).getWidth() <= width && ((Rectangle) current).getHeight() <= height){
                        foundShapes.add(current);
                        flag=true;
                    }
                }
                else if(current instanceof Circle) {
                    if (current.getX() >= x && current.getY() >= y && ((Circle) current).getR() <= (width / 2) && ((Circle) current).getR() <= (height / 2)) {
                        foundShapes.add(current);
                        flag=true;
                    }
                }
                else if (current instanceof Line){
                    if(current.getX() >= x && current.getY() >= y && ((Line) current).getX1() < (width-x) && ((Line) current).getY1() < (height-y)){
                        foundShapes.add(current);
                        flag=true;
                    }
                }
            }
            if(flag)
                System.out.println(Arrays.toString(foundShapes.toArray()));
            else
                System.out.println("No shapes are located within rectangle " + x + " " + y + " " + width + " " + height);
        }

        else if (ans.equals("circle")){
            System.out.println("Write parameters:");
            int x=sc.nextInt();
            int y=sc.nextInt();
            int r=sc.nextInt();
            List<BasicShape> foundShapes=new ArrayList<>();
            boolean flag=false;
            for(BasicShape current:shapes){
                if(current instanceof Rectangle){
                    if(current.getX() >= (x-r) && current.getY() >= (y-r) && ((Rectangle) current).getWidth() <= (2*r) && ((Rectangle) current).getHeight() <= (2*r)){
                        foundShapes.add(current);
                        flag=true;
                    }
                }
                else if(current instanceof Circle) {
                    if (current.getX() > (x-r) && current.getY() > (y-r) && ((Circle) current).getR() < r) {
                        foundShapes.add(current);
                        flag=true;
                    }
                }
                else if (current instanceof Line){
                    if(current.getX() >= (x-r) && current.getY() >= (y-r) && ((Line) current).getX1() < (x-r) && ((Line) current).getY1() < (y-r)){
                        foundShapes.add(current);
                        flag=true;
                    }
                }
            }
            if(flag)
                System.out.println(Arrays.toString(foundShapes.toArray()));
            else
                System.out.println("No shapes are located within circle " + x + " " + y + " " + r);
        }

        Main.menu();
    }

    public boolean isShapeCreated(){
        if(method1Called){
            method1Called=false;
            return true;
        }
        return false;
    }
    public boolean isShapeErased(){
        if(method2Called){
            method2Called=false;
            return true;
        }
        return false;
    }
    public boolean isShapeTranslated(){
        if(method3Called){
            method3Called=false;
            return true;
        }
        return false;
    }
    public boolean isWithinRegionCalled(){
        if(method4Called){
            method4Called=false;
            return true;
        }
        return false;
    }
}
