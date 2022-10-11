import java.io.*;

/**
 * Write a description of class Output here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Output
{

    String full_file_name;
    public  File       file;
    public  FileWriter fw;
    public  FileWriter writer;
    
    /**
     * Constructor for objects of class Output
     */
    public Output(String fileName)
    {
        full_file_name = fileName + ".txt";
        open();
    }

    /**
     * Creates a new file with the 
     */
    public void open() {
        
        try {
            file = new File(full_file_name);
            //file.createNewFile();
            
            writer = new FileWriter(file);
        } catch (Exception e) {
            System.out.println("Error occured in opening the file " + full_file_name);
            System.out.println("Exception " + e);
        }
        
    }
    
    public void write(String str) {
        
        try {
            writer.write(str); 
            writer.flush();
        } catch (Exception e) {
            System.out.println("File " + full_file_name + " could not be written.");
            System.out.println("Exception " + e);
        }
        
    }

    public void close() {
        
        try {
            writer.close();
        } catch (Exception e) {
            System.out.println("File " + full_file_name + " could not be closed.");
            System.out.println("Exceptioin " + e);
        }
        
    }
}
