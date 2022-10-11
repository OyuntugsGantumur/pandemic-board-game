

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CardTest
{
    Card test1;
    Card test2;
    
    /**
     * Default constructor for test class CardTest
     */
    public CardTest()
    {
        test1 = new Card(new Disease("black"), "New York");
        test2 = new Card(true);
    }
   
    @Test
    public void toStringTest() {
        assertEquals("New York(black)", test1.toString());
        assertEquals("Epidemic card", test2.toString());
    }
}
