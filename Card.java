
/**
 * Represents a general card used in the game
 *
 * @author Oyu Gantumur
 */
public class Card
{
    Disease diseaseType;
    String cityName;
    boolean isEpidemic;

    /**
     * Constructor for objects of class Card
     */
    public Card(Disease diseaseType, String cityName)
    {
        this.diseaseType = diseaseType;
        this.cityName = cityName;
        this.isEpidemic = false;       
    }
    
    /**
     * Constructor for objects of class Card
     */
    public Card(boolean isEpidemic) {
        this.isEpidemic = isEpidemic;
    }
    
    /**
     * Converts the card to a string
     */
    public String toString() {
        
        if(isEpidemic == false) {
            return cityName + "(" + diseaseType.name + ")";   
        }
        else {
            return "Epidemic card";
        }
             
    }
    
    /**
     * Converts the card to a string - used for testing
     */
    public String toString1() {   
        return cityName;
    }

}
