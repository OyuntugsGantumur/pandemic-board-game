
/**
 * Represents the different diseases that will be implemented in the program
 *
 * @author Oyu Gantumur
 * @version (a version number or a date)
 */
public class Disease
{
    String name;
    boolean cured;
    boolean eradicated;
    int count;
    
    /**
     * Constructor for objects of class Disease
     */
    public Disease(String name)
    {
        this.name = name;
        cured = false;
        eradicated = false;
        count = 0;
    }
    
    /**
     * Increases the number of cubes used if the disease is not eradicated
     * 
     * @param number of cubes to add
     */
    public void incrCount(int n) {
        
        if(eradicated == false) {
            count = count + n;
        }
    }   
    
    /**
     * Decreases the number of cubes used in each disease
     */
    public void decrCount() {
        count--;
        
        if(cured == true && count <= 0) {
            eradicated = true;
        }
    }
    
    /**
     * Marks the disease as cured
     */
    public void cure() {
        this.cured = true;
    }

    /**
     * Marks the disease as eradicated
     */
    public void eradicateDisease() {
        this.eradicated = true;
    }
    
    public String toString() {
        String ret = "Disease " + name + ": " + this.count + " cubes, ";
        
        if(this.cured == true) {
            ret += "cured and";
        }
        else {
            ret += "not cured and";
        }
        
        if(this.eradicated == true) {
            ret += " eradicated.";
        }
        else {
            ret += " not eradicated.";
        }
        
        return ret;
    }
}
