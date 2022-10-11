

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ReaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ReaderTest
{
    Reader reader;
    
    /**
     * Default constructor for test class ReaderTest
     */
    public ReaderTest()
    {
        reader = new Reader("test.txt");
    }

    @Test
    public void testRead() {
        assertEquals("[Tokyo, Paris, NewYork, Washington, Milan, Stockholm, Beijing, Moscow, London]", 
            reader.cities.toString());
        
        assertEquals("[black, blue, red, yellow, black, yellow, red, black, blue]",
            reader.diseases.toString());
            
        assertEquals("NewYork", reader.cities.get(2));
        assertEquals("red", reader.diseases.get(2));
    } 
}
