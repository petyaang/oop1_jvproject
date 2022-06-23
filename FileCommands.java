package fileoperations;
import java.io.File;
import java.util.List;

import shapes.*;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class FileCommands {
    private Shapes shapeList=new Shapes();
    private XMLParser parser =new XMLParser();

    public boolean openFile(String path){
        File newFile = new File(path);
        try {
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                parser.readDataFromFile(newFile, shapeList);
                System.out.println("Successfully opened shapes.svg");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return newFile.length() != 0;
    }


    public void saveFile(String path){
        try {
            Document doc = parser.writeDataToFile();
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

