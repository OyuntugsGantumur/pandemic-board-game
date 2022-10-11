
/**
 * Represents the cities on the map
 *
 * @author Oyu Gantumur
 */
public class City 
{
    String name;
    Disease disease;
    int numCube;
    boolean outbreak;
    boolean hasResearchStation;    

    /**
     * Constructor for objects of class City
     */
    public City(String name)
    {
        this.name = name;
        numCube = 0;
        outbreak = false;
        hasResearchStation = false;
    }
    
    public City(String name, Disease disease) 
    {
        this.name = name;
        this.disease = disease;
        numCube = 0;
        outbreak = false;
        hasResearchStation = false;
    }
    
    public void setDisease(Disease disease) {
        this.disease = disease;
    }
    
    public void setOutbreak(boolean outbreak) {
        this.outbreak = outbreak;
    }
    
    /**
     * Increases the number of cubes by n
     * If the number of cubes is greater than 3, outbreak happens
     */
    public boolean addCube(int n, String type) {
        
        if ((numCube + n) <= 3) {
            numCube += n;
            disease.incrCount(n);
            outbreak = false;
        } 
        else if ((numCube + n) > 3) {
            n = 3 - numCube;
            numCube = 3;
            disease.incrCount(n);
            outbreak = true;
        }
        
        System.out.println(n + " cube is added to " + name + " by " + type);
        return outbreak;
    }
    
    /**
     * Removes a cube from the city
     */
    public void removeCube() {
        numCube--;
        disease.decrCount();
    }
    
    /**
     * Removes all cubes from the city
     */
    public void removeAllCube() {
        numCube = 0;
    }
    
    /**
     * Checks if the city has any cubes
     * 
     * @return true if it has any cubes
     */
    public boolean hasCube() {
        if(numCube > 0) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Builds a research station in the city
     */
    public void buildResStation() {
        hasResearchStation = true;
    }

    /**
     * Converts the city to a string
     */
    public String toString() {
        return name;
    }
}
