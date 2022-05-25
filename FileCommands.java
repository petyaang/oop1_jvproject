package bg.tu_varna.sit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class FileCommands {
    public Shapes shapeList=new Shapes();

    public boolean openFile(String path){
        boolean result=false;
        try {
            File newFile = new File(path);
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(newFile);
                NodeList svgList = doc.getElementsByTagName("svg");
                for (int i = 0; i < svgList.getLength(); i++) {
                    Node s = svgList.item(i);
                    if (s.getNodeType() == Node.ELEMENT_NODE) {
                        Element svg = (Element) s;

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
                                    shapeList.addShape(new Rectangle("rectangle", x, y, fill, width, height));
                                    result = true;
                                } else if (coordinate.getTagName().equals("circle")) {
                                    int cx = Integer.parseInt(coordinate.getAttribute("cx"));
                                    int cy = Integer.parseInt(coordinate.getAttribute("cy"));
                                    int r = Integer.parseInt(coordinate.getAttribute("r"));
                                    String fill = coordinate.getAttribute("fill");
                                    shapeList.addShape(new Circle("circle", cx, cy, fill, r));
                                    result = true;
                                } else if (coordinate.getTagName().equals("line")) {
                                    int x1 = Integer.parseInt(coordinate.getAttribute("x1"));
                                    int y1 = Integer.parseInt(coordinate.getAttribute("y1"));
                                    int x2 = Integer.parseInt(coordinate.getAttribute("x2"));
                                    int y2 = Integer.parseInt(coordinate.getAttribute("y2"));
                                    String stroke = coordinate.getAttribute("stroke");
                                    int strokeWidth = Integer.parseInt(coordinate.getAttribute("stroke-width"));
                                    shapeList.addShape(new Line("line", x1, y1, stroke, x2, y2, strokeWidth));
                                    result = true;
                                }
                            }
                        }
                    }
                }
                System.out.println("Successfully opened shapes.svg");
            }
        }catch(Exception e){
                e.printStackTrace();
            }
        return result;
    }

    /*public void loadFile() {
        try {
            File newFile = new File("shapes.svg");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                //System.out.println("Successfully opened shapes.svg");
                //FileChannel channel = new RandomAccessFile(new File(newFile.getName()), "rw").getChannel();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                try {
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(newFile);
                    //Document doc = builder.parse("C:\\Users\\Petya\\IdeaProjects\\oop1_project\\shapes.svg");
                    NodeList svgList = doc.getElementsByTagName("svg");
                    for (int i = 0; i < svgList.getLength(); i++) {
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
                                        String x = coordinate.getAttribute("x");
                                        String y = coordinate.getAttribute("y");
                                        String width = coordinate.getAttribute("width");
                                        String height = coordinate.getAttribute("height");
                                        String fill = coordinate.getAttribute("fill");
                                    } else if (coordinate.getTagName().equals("circle")) {
                                        String cx = coordinate.getAttribute("cx");
                                        String cy = coordinate.getAttribute("cy");
                                        String r = coordinate.getAttribute("r");
                                        String fill = coordinate.getAttribute("fill");
                                    } else if (coordinate.getTagName().equals("line")) {
                                        String x1 = coordinate.getAttribute("x1");
                                        String y1 = coordinate.getAttribute("y1");
                                        String x2 = coordinate.getAttribute("x2");
                                        String y2 = coordinate.getAttribute("y2");
                                        String stroke = coordinate.getAttribute("stroke");
                                        String strokeWidth = coordinate.getAttribute("stroke_width");
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Successfully opened shapes.svg");
                    //channel.close();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }*/

    //??????
    public void closeFile() {
       /* try {
            File newFile = new File("shapes.svg");
            FileChannel channel = new RandomAccessFile(new File(newFile.getName()), "rw").getChannel();
            if (channel.isOpen()) {
                channel.close();
                System.out.println("Successfully closed shapes.svg!");
            } else
                System.out.println("Shapes.svg has not been opened yet.");
        } catch (IOException e){
            e.printStackTrace();
        }*/

        try {
            String fileName = "shapes.svg";
            File file = new File(fileName);

            // try to rename the file with the same name
            File sameFileName = new File(fileName);

            if (file.renameTo(sameFileName)) {
                // if the file is renamed
                System.out.println("Shapes.svg is already closed.");
            } else {
                // if the file didn't accept the renaming operation
                FileChannel channel = new RandomAccessFile(new File(fileName), "rw").getChannel();
                if (channel.isOpen()) {
                    channel.close();
                    System.out.println("Successfully closed shapes.svg!");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveChangesToFile(){
        if(shapeList.isShapeCreated()){

        }
        else if(shapeList.isShapeErased()){

        }
        else if(shapeList.isShapeTranslated()){

        }
        else if (shapeList.isWithinRegionCalled()){

        }
    }

}
