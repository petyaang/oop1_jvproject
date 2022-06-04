package bg.tu_varna.sit;
import java.io.File;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FileCommands {
    private Shapes shapeList=new Shapes();

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
                                        if(!shapeList.getShapes().contains(new Rectangle("rectangle", x, y, fill, width, height))){
                                        shapeList.addShape(new Rectangle("rectangle", x, y, fill, width, height));}
                                        result = true;
                                    } else if (coordinate.getTagName().equals("circle")) {
                                        int cx = Integer.parseInt(coordinate.getAttribute("cx"));
                                        int cy = Integer.parseInt(coordinate.getAttribute("cy"));
                                        int r = Integer.parseInt(coordinate.getAttribute("r"));
                                        String fill = coordinate.getAttribute("fill");
                                        if(!shapeList.getShapes().contains(new Circle("circle", cx, cy, fill, r))){
                                        shapeList.addShape(new Circle("circle", cx, cy, fill, r));}
                                        result = true;
                                    } else if (coordinate.getTagName().equals("line")) {
                                        int x1 = Integer.parseInt(coordinate.getAttribute("x1"));
                                        int y1 = Integer.parseInt(coordinate.getAttribute("y1"));
                                        int x2 = Integer.parseInt(coordinate.getAttribute("x2"));
                                        int y2 = Integer.parseInt(coordinate.getAttribute("y2"));
                                        String stroke = coordinate.getAttribute("stroke");
                                        int strokeWidth = Integer.parseInt(coordinate.getAttribute("stroke-width"));
                                        if(!shapeList.getShapes().contains(new Line("line", x1, y1, stroke, x2, y2, strokeWidth))){
                                        shapeList.addShape(new Line("line", x1, y1, stroke, x2, y2, strokeWidth));}
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


    public void saveFile(String path){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            Document doc = build.newDocument();
            Element root = doc.createElement("svg");
            doc.appendChild(root);
            List<BasicShape> shapes  = new Shapes().getShapes();
            for (BasicShape b : shapes) {
                if (b instanceof Rectangle) {
                    Element rectangle = doc.createElement("rect");
                    rectangle.setAttribute("x", Integer.toString(b.getX()));
                    rectangle.setAttribute("y", Integer.toString(b.getY()));
                    rectangle.setAttribute("width", Integer.toString(((Rectangle) b).getWidth()));
                    rectangle.setAttribute("height", Integer.toString(((Rectangle) b).getHeight()));
                    rectangle.setAttribute("fill", b.getFill());
                    root.appendChild(rectangle);
                }
                else if (b instanceof Line) {
                    Element line = doc.createElement("line");
                    line.setAttribute("x1", Integer.toString(b.getX()));
                    line.setAttribute("y1", Integer.toString(b.getY()));
                    line.setAttribute("x2", Integer.toString(((Line) b).getX1()));
                    line.setAttribute("y2", Integer.toString(((Line) b).getY1()));
                    line.setAttribute("stroke", b.getFill());
                    line.setAttribute("stroke-width", Integer.toString(((Line) b).getStrokeWidth()));
                    root.appendChild(line);
                }
                else if (b instanceof Circle) {
                    Element circle = doc.createElement("circle");
                    circle.setAttribute("cx", Integer.toString(b.getX()));
                    circle.setAttribute("cy", Integer.toString(b.getY()));
                    circle.setAttribute("r", Integer.toString(((Circle) b).getR()));
                    circle.setAttribute("fill", b.getFill());
                    root.appendChild(circle);
                }
            }
            DOMSource source = new DOMSource(doc);
            File file = new File(path);
            Result result = new StreamResult(file);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer tr = transformerFactory.newTransformer();
            tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(source, result);
            System.out.println("Successfully saved " + path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
