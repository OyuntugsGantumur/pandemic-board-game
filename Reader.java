import java.util.*;
import java.util.Scanner;
import java.io.*;

/**
 * Reads the spcified file and returns the information there in a specific format
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reader
{
    String filename;
    LinkedList<String> cities;
    LinkedList<String> diseases;

    /**
     * Constructor for objects of class FileReader
     */
    public Reader(String filename)
    {
        this.filename = filename;
        cities = new LinkedList<String>();
        diseases = new LinkedList<String>();
        
        loadFile();
    }

    /**
     * Reads the specified file and adds the cities and diseases into separate lists
     */
    public void loadFile() {
        
        try {
            Scanner scanner = new Scanner(new FileReader(filename));
            Scanner lineScan = null;
            String line = null;
            String city = null;
            String disease = null;
            
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                lineScan = new Scanner(line);
                
                cities.add(lineScan.next());
                diseases.add(lineScan.next());
                
            }
            
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
}
